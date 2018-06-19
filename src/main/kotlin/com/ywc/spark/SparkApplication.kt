package com.ywc.spark

import com.ywc.spark.kafka.Producer
import com.ywc.spark.util.BeanFacotryUtil
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
class SparkApplication

fun main(args: Array<String>) {
    runApplication<SparkApplication>(*args)
    BeanFacotryUtil.getBean(Producer::class.java).init()
}
