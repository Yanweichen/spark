package com.ywc.spark

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/hello")
class HelloController {

    @Autowired
    private lateinit var test: Test

    @GetMapping("/kafka")
    fun hello() = test.say.apply { println("--------------------run") }
}