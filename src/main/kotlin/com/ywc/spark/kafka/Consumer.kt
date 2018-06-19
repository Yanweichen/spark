package com.ywc.spark.kafka

import com.ywc.spark.mgt.model.PersonOuterClass
import example.avro.User
import org.springframework.boot.CommandLineRunner
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.beans.factory.annotation.Autowired
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
class Consumer : CommandLineRunner {

    override fun run(vararg args: String?) {
    }

//    @KafkaListener(topics = ["test"])
//    fun listen(cr: ConsumerRecord<String, PersonOuterClass.Person>) {
//        println("kafka收到测试数据:${cr.value()}")
//    }
    @KafkaListener(topics = ["test"])
    fun listen(cr: ConsumerRecord<String, User>) {
        println("kafka收到测试数据:${cr.value()}")
    }
}