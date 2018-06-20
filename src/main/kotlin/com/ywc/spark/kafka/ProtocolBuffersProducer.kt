package com.ywc.spark.kafka

import com.google.protobuf.Message
import com.ywc.spark.mgt.model.PersonOut
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.runBlocking
import org.apache.commons.lang3.RandomStringUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

@Component
class ProtocolBuffersProducer {

    @Autowired
    lateinit var template: KafkaTemplate<String, Message>

    fun init() = runBlocking {
        launch(CommonPool) {
            while (true) {
                PersonOut.Person.newBuilder()
                        .setName("protobuf" + RandomStringUtils.randomNumeric(5))
                        .setEmail("abc").build().run {
                            template.send("test", this)
                        }
                delay(2000L)
            }
        }
    }
}