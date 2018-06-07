package com.ywc.spark.kafka

import org.springframework.boot.CommandLineRunner
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.beans.factory.annotation.Autowired
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

//@Component
class Consumer : CommandLineRunner {

    @Autowired
    lateinit var template: KafkaTemplate<String, String>

    override fun run(vararg args: String?) {
    }

    @KafkaListener(topics = ["test"])
    fun listen(cr: ConsumerRecord<*, *>) {
        println(cr.toString())
    }
}