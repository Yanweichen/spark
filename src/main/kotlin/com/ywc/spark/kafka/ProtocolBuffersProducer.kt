package com.ywc.spark.kafka

import com.google.protobuf.Any
import com.google.protobuf.Message
import com.xy.ExchangeMessage
import com.ywc.spark.mgt.model.PersonOut
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.runBlocking
import org.apache.commons.lang3.RandomStringUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component
import org.apache.kafka.clients.admin.NewTopic
import org.springframework.context.annotation.Bean


@Component
class ProtocolBuffersProducer {

    @Autowired
    lateinit var template: KafkaTemplate<String, Message>

    fun init() = runBlocking {
        launch(CommonPool) {
            while (true) {
//                PersonOut.Person.newBuilder()
//                        .setName("protobuf" + RandomStringUtils.randomNumeric(5))
//                        .setEmail("abc").build().run {
//                            template.send("YC_justtest", this)
//                        }
                val e = ExchangeMessage.Frame.newBuilder()
                e.payload = Any
                        .pack(ExchangeMessage.TelemeteringParam.newBuilder()
                                .setCode(RandomStringUtils.randomNumeric(5)).build())
                e.build().run {
                    template.send("YC.00008002", this)
                }
                delay(2000L)
            }
        }
    }
}