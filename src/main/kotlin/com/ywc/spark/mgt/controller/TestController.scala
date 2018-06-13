package com.ywc.spark

import com.ywc.spark.mgt.service.SparkDemoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.{GetMapping, RequestMapping, RestController}

@RestController
@RequestMapping(Array("/hello"))
class TestController @Autowired()(val sparkDemoService: SparkDemoService) {

  @GetMapping(Array("/scala"))
  def hello(): String = {
    sparkDemoService.statefulNetworkWordCount()
    "hello scala"
  }
}
