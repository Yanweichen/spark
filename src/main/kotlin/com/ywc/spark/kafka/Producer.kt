package com.ywc.spark.kafka

import com.ywc.spark.mgt.model.PersonOuterClass
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.runBlocking
import org.apache.commons.lang3.RandomStringUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

@Component
class Producer {

    @Autowired
    lateinit var template: KafkaTemplate<String, PersonOuterClass.Person>

    fun init() = runBlocking {
        launch(CommonPool) {
            while (true) {
                PersonOuterClass.Person.newBuilder()
                        .setName("kafka" + RandomStringUtils.randomNumeric(100))
                        .setEmail("abc").build().run {
                            template.send("test", this)
                        }
                delay(2000L)
            }
        }
    }
}