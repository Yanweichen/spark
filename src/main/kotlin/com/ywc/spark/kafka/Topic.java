package com.ywc.spark.kafka;

import com.google.protobuf.Message;
import com.xy.ExchangeMessage;
import com.ywc.spark.mgt.model.PersonOut;
import example.avro.User;
import org.apache.avro.specific.SpecificRecordBase;

import java.util.stream.Stream;

/**
 * @author yanweichen
 * createTime 2018-06-2018/6/19 13:19
 */
public enum Topic {

    TEST("orbit_output", new User(), PersonOut.Person.getDefaultInstance()),
    TEST2("test", new User(), PersonOut.Person.getDefaultInstance()),
    TEST1("^YC.*$", new User(), ExchangeMessage.Frame.getDefaultInstance());


    private String topicName;

    private SpecificRecordBase topicType;

    private Message pTopicType;

    Topic(String topicName, SpecificRecordBase topicType, Message pTopicType) {
        this.topicName = topicName;
        this.topicType = topicType;
        this.pTopicType = pTopicType;
    }

    public String getTopicName() {
        return topicName;
    }

    public SpecificRecordBase getTopicType() {
        return topicType;
    }

    public Message getpTopicType() {
        return pTopicType;
    }

    public static Topic matchFor(String topicName) {
        return Stream.of(values()).filter(topic -> topicName.matches(topic.getTopicName())).findAny()
                .orElseThrow(() -> new RuntimeException("未知的topicName:" + topicName));
    }
}
