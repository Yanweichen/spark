package com.ywc

import com.ywc.base.serialization.ProtocolBuffersDeserializer
import com.ywc.spark.mgt.model.PersonOut.Person
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.spark.SparkConf
import org.apache.spark.streaming.kafka010.{ConsumerStrategies, KafkaUtils, LocationStrategies}
import org.apache.spark.streaming.{Seconds, StreamingContext}

object KafkaStream {

  def main(args: Array[String]): Unit = {
        val sparkConf = new SparkConf()
        val ssc = new StreamingContext(sparkConf, Seconds(5))

        val kafkaParams = Map[String, Object](
          "bootstrap.servers" -> "192.168.71.128:9092",
          "key.deserializer" -> classOf[StringDeserializer],
          "value.deserializer" -> classOf[ProtocolBuffersDeserializer],
          //      "value.deserializer" -> classOf[AvroDeserializer],
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
        stream.foreachRDD(rdd => rdd.foreach(x => println("spark收到测试数据:" + x.value())))
        ssc.start()
        ssc.awaitTermination()
  }
}
