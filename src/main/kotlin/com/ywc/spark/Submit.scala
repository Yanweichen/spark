package com.ywc.spark

import org.apache.spark.deploy.SparkSubmit

object Submit {

  def main(args: Array[String]): Unit = {
    val arg0 = Array[String](
      "--master", "spark://192.168.71.128:7077",
      "--name", "kafkaStream",
      "--class", "com.ywc.KafkaStream",
      "C:\\Git_Project\\spark\\spark_app\\build\\libs\\spark_app-0.0.1-SNAPSHOT.jar"
    )
    SparkSubmit.main(arg0)
  }
}
