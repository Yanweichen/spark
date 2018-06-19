package com.ywc.spark.kafka

import com.google.protobuf.Message
import com.ywc.spark.mgt.model.PersonOuterClass
import example.avro.User
import org.apache.avro.specific.SpecificRecordBase

enum class Topic(var topicName: String, var topicType: SpecificRecordBase, var pTopicType: Message) {

    CAT("test", User(), PersonOuterClass.Person.getDefaultInstance());

    companion object {
        fun matchFor(topicName: String): Topic = values()
                .first { topic -> topic.topicName == topicName }
    }
}