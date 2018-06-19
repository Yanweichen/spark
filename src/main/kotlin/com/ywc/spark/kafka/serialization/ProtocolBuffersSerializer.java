package com.ywc.spark.kafka.serialization;

import com.google.protobuf.Message;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

/**
 * @author yanweichen
 * @date 2018/6/19
 */
public class ProtocolBuffersSerializer implements Serializer<Message> {
    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {

    }

    @Override
    public byte[] serialize(String topic, Message data) {
        return data.toByteArray();
    }

    @Override
    public void close() {

    }
}
