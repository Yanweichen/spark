package com.ywc.spark.kafka

import com.xy.ExchangeMessage
import com.ywc.spark.mgt.model.PersonOut
import example.avro.User
import org.springframework.boot.CommandLineRunner
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
class Consumer : CommandLineRunner {

    override fun run(vararg args: String?) {
    }

//    @KafkaListener(topics = ["WCDistance", "WCSpeed", "WCAngle"])
    @KafkaListener(topicPattern = "YC.*",errorHandler = "errorHandler")
    fun listen(cr: ConsumerRecord<String, Any>) {
        val value = cr.value()
//        when (value) {
//            is PersonOut.Person -> println("kafka收到Protobuf测试数据:${value.name}")
//            is User -> println("kafka收到Avor测试数据:${value.name}")
//            is ExchangeMessage.Frame ->
//                println("${cr.topic()}:kafka收到Protobuf测试数据:${value.payload
//                        .unpack(ExchangeMessage.TelemeteringParam::class.java).code}")
//        }
    }
}