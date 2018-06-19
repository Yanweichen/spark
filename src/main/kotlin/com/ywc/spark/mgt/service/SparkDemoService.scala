package com.ywc.spark.mgt.service

import com.ywc.spark.kafka.serialization.{ProtocolBuffersDeserializer, ProtocolBuffersSerializer}
import com.ywc.spark.mgt.model.PersonOuterClass.Person
import org.apache.spark.SparkConf
import org.apache.spark.storage.StorageLevel
import org.apache.spark.streaming.kafka010.{ConsumerStrategies, KafkaUtils, LocationStrategies}
import org.apache.spark.streaming.{Seconds, State, StateSpec, StreamingContext}
import org.springframework.stereotype.Service

@Service
class SparkDemoService() {

  def networkWordCount(): Unit = {
    val sparkConf = new SparkConf().setMaster("local[2]").setAppName("NetworkWordCount")
    val ssc = new StreamingContext(sparkConf, Seconds(5))
    val lines = ssc.socketTextStream("192.168.71.128"
      , 9999, StorageLevel.MEMORY_AND_DISK_SER)
    val words = lines.flatMap(_.split(" "))
    val wordCounts = words.map(x => (x, 1)).reduceByKey(_ + _)
    wordCounts.print()
    ssc.start()
    ssc.awaitTermination()
  }

  def statefulNetworkWordCount() = {
    val sparkConf = new SparkConf().setMaster("local[2]").setAppName("StatefulNetworkWordCount")
    val ssc = new StreamingContext(sparkConf, Seconds(5))
    // 如果使用了stateful的算子，必须要设置checkpoint
    // 在生产环境中，建议大家把checkpoint设置到HDFS的某个文件夹中
    ssc.checkpoint(".")

    val lines = ssc.socketTextStream("192.168.71.128", 9999, StorageLevel.MEMORY_AND_DISK_SER)

    val result = lines.flatMap(_.split(" ")).map((_, 1))
    val state = result.updateStateByKey[Int](
      (currentValues: Seq[Int], preValues: Option[Int]) => {
        val current = currentValues.sum
        val pre = preValues.getOrElse(0)
        Some(current + pre)
      })

    state.print()

    ssc.start()
    ssc.awaitTermination()
  }

  def kafkaStream(): Unit = {
    val sparkConf = new SparkConf().setMaster("local[2]").setAppName("StatefulNetworkWordCount")
      .set("spark.serializer", "org.apache.spark.serializer.KryoSerializer")
    val ssc = new StreamingContext(sparkConf, Seconds(5))

    val kafkaParams = Map[String, Object](
      "bootstrap.servers" -> "192.168.71.128:9092",
      "key.deserializer" -> classOf[ProtocolBuffersDeserializer],
      "value.deserializer" -> classOf[ProtocolBuffersDeserializer],
      "group.id" -> "use_a_separate_group_id_for_each_stream",
      "auto.offset.reset" -> "latest",
      "enable.auto.commit" -> (false: java.lang.Boolean)
    )

    val topics = Array("test")
    val stream = KafkaUtils.createDirectStream[String, Person](
      ssc,
      LocationStrategies.PreferConsistent,
      ConsumerStrategies.Subscribe[String, Person](topics, kafkaParams)
    )

    stream.foreachRDD(rdd => rdd.foreach(x => println("spark收到测试数据:"+x.value())))
    ssc.start()
    ssc.awaitTermination()
  }
}
