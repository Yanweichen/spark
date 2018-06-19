package com.ywc.spark.kafka.serialization

import org.apache.avro.specific.SpecificRecordBase
import org.apache.kafka.common.serialization.Serializer
import org.apache.avro.io.EncoderFactory
import org.apache.avro.specific.SpecificDatumWriter
import java.io.ByteArrayOutputStream


class AvroSerializer<T : SpecificRecordBase> : Serializer<T> {

    override fun configure(configs: MutableMap<String, *>?, isKey: Boolean) {
    }

    override fun serialize(topic: String?, data: T): ByteArray = ByteArrayOutputStream().run {
        EncoderFactory.get().directBinaryEncoder(this, null).run {
            SpecificDatumWriter<T>(data.schema).write(data, this)
        }
        this.toByteArray()
    }

    override fun close() {
    }
}