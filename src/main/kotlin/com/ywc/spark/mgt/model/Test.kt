package com.ywc.spark.mgt.model

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties(prefix = "test")
class Test {

    var say: String? = null
}