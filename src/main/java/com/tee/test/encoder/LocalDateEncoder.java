package com.tee.test.encoder;

import org.apache.avro.Schema;
import org.apache.avro.io.Decoder;
import org.apache.avro.io.Encoder;
import org.apache.avro.reflect.CustomEncoding;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateEncoder extends CustomEncoding<LocalDate> {

    {
        schema = Schema.create(Schema.Type.STRING);
        schema.addProp("CustomEncoding", "LocalDateAsStringEncoding");
    }

    @Override
    protected void write(Object datum, Encoder out) throws IOException {
        out.writeString(((LocalDate) datum).format(DateTimeFormatter.ISO_LOCAL_DATE));
    }

    @Override
    protected LocalDate read(Object reuse, Decoder in) throws IOException {
        if (reuse instanceof LocalDate) {
            return (LocalDate) reuse;
        } else
            return LocalDate.parse(in.readString(), DateTimeFormatter.ISO_LOCAL_DATE);
    }
}
