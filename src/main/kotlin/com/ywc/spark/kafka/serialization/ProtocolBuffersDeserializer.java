package com.ywc.spark.kafka.serialization;

import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import com.ywc.spark.kafka.Topic;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;

/**
 * @author yanweichen
 * @date 2018/6/19
 */
public class ProtocolBuffersDeserializer implements Deserializer<Message> {
    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {

    }

    @Override
    public Message deserialize(String topic, byte[] data) {
        try {
            return Topic.Companion.matchFor(topic).getPTopicType().getParserForType().parseFrom(data);
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void close() {

    }
}
