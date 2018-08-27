package com.ywc.base;

import com.google.protobuf.Message;
import com.ywc.spark.mgt.model.PersonOut;

import java.util.stream.Stream;

/**
 * @author yanweichen
 * createTime 2018-06-2018/6/19 13:19
 */
public enum Topic {

    TEST("test", PersonOut.Person.getDefaultInstance());


    private String topicName;

    private Message topicType;

    Topic(String topicName, Message topicType) {
        this.topicName = topicName;
        this.topicType = topicType;
    }

    public String getTopicName() {
        return topicName;
    }

    public Message getTopicType() {
        return topicType;
    }

    public static Topic matchFor(String topicName) {
        return Stream.of(values()).filter(topic -> topic.getTopicName().equals(topicName)).findAny()
                .orElseThrow(() -> new RuntimeException("未知的topicName:" + topicName));
    }
}
