package com.ywc.spark.kafka

import com.google.protobuf.Message
import com.ywc.spark.mgt.model.PersonOuterClass
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

@Component
class Producer {

    @Autowired
    lateinit var template: KafkaTemplate<String, SpecificRecordBase>
//    lateinit var template: KafkaTemplate<String, Message>

    fun init() = runBlocking {
        launch(CommonPool) {
            while (true) {
//                PersonOuterClass.Person.newBuilder()
//                        .setName("kafka" + RandomStringUtils.randomNumeric(5))
//                        .setEmail("abc").build().run {
//                            template.send("test", this)
//                        }
                with(User()) {
                    name = "kafka" + RandomStringUtils.randomNumeric(5)
                    favoriteColor = "blue"
                    favoriteNumber = RandomUtils.nextInt(10)
                    template.send("test","test", this)
                }
                delay(2000L)
            }
        }
    }
}