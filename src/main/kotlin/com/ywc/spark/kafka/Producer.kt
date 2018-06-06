package com.ywc.spark.kafka

import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.runBlocking
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Component
class Producer {

    @Autowired
    lateinit var template: KafkaTemplate<String, String>

    fun init() = runBlocking {
        launch(CommonPool) {
            while (true) {
                template.send("test", "hello,kafka${LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)}")
                delay(2000L)
            }
        }
    }
}