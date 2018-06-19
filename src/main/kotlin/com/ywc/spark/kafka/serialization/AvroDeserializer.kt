package com.ywc.spark.kafka.serialization

import org.apache.avro.specific.SpecificRecordBase
import org.apache.kafka.common.serialization.Deserializer
import com.ywc.spark.kafka.Topic
import java.io.ByteArrayInputStream
import org.apache.avro.io.DecoderFactory
import org.apache.avro.specific.SpecificDatumReader
import com.sun.xml.internal.ws.encoding.soap.DeserializationException
import java.io.IOException


class AvroDeserializer<T : SpecificRecordBase> : Deserializer<T> {

    override fun configure(configs: MutableMap<String, *>?, isKey: Boolean) {

    }

    override fun deserialize(topic: String, data: ByteArray?): T {
        val userDatumReader = SpecificDatumReader<T>(Topic.matchFor(topic).topicType.schema)
        val binaryEncoder = DecoderFactory.get()
                .directBinaryDecoder(ByteArrayInputStream(data), null)
        try {
            return userDatumReader.read(null, binaryEncoder)
        } catch (e: IOException) {
            throw DeserializationException(e.message)
        }

    }

    override fun close() {

    }
}