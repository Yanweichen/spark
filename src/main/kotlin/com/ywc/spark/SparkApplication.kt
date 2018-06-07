package com.ywc.spark

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
class SparkApplication

fun main(args: Array<String>) {
    runApplication<SparkApplication>(*args)
//    BeanFacotryUtil.getBean(Producer::class.java).init()
    println("finish")
}
