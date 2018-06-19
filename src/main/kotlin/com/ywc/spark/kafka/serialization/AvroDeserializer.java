package com.ywc.spark.kafka.serialization;

import com.ywc.spark.kafka.Topic;
import org.apache.avro.io.BinaryDecoder;
import org.apache.avro.io.DecoderFactory;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificRecordBase;
import org.apache.kafka.common.serialization.Deserializer;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Map;

/**
 * @author yanweichen
 * @date 2018/6/19
 */
public class AvroDeserializer implements Deserializer<SpecificRecordBase> {
    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {

    }

    @Override
    public SpecificRecordBase deserialize(String topic, byte[] data) {
        SpecificDatumReader<SpecificRecordBase> userDatumReader =
                new SpecificDatumReader<>(Topic.Companion.matchFor(topic).getTopicType().getSchema());
        BinaryDecoder binaryDecoder = DecoderFactory.get()
                .directBinaryDecoder(new ByteArrayInputStream(data), null);
        try {
            return userDatumReader.read(null, binaryDecoder);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void close() {

    }
}
