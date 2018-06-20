package com.ywc.spark;

import com.ywc.spark.mgt.service.SparkDemoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SparkApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@EnableAutoConfiguration
public class SparkDemoServiceTest {
    @Autowired
    private SparkDemoService sparkDemoService;

    @Test
    public void kafkaStream() {
        sparkDemoService.kafkaStream();
    }
}