package com.ywc.spark.kafka

import example.avro.User
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.runBlocking
import org.apache.avro.specific.SpecificRecordBase
import org.apache.commons.lang.math.RandomUtils
import org.apache.commons.lang3.RandomStringUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

//@Component
class AvroProducer {

    @Autowired
    lateinit var template: KafkaTemplate<String, SpecificRecordBase>

    fun init() = runBlocking {
        launch(CommonPool) {
            while (true) {
                with(User()) {
                    name = "avro" + RandomStringUtils.randomNumeric(5)
                    favoriteColor = "blue"
                    favoriteNumber = RandomUtils.nextInt(10)
                    template.send("test","test", this)
                }
                delay(2000L)
            }
        }
    }
}