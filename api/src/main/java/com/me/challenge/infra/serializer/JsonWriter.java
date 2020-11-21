package com.me.challenge.infra.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import lombok.AllArgsConstructor;
import org.springframework.util.ObjectUtils;

import java.io.IOException;

/**
 *
 * @author Thiago Santos
 * @version 1.0 18/11/2020
 */
@AllArgsConstructor
public class JsonWriter {

    private final JsonGenerator jsonGenerator;

    public void writeStartObject() throws IOException {
        jsonGenerator.writeStartObject();
    }

    public void writeArrayFieldStart(final Enum<?> fieldName) throws IOException {
        jsonGenerator.writeArrayFieldStart(fieldName.toString());
    }

    public void writeStringField(final Enum<?> fieldName, final String value) throws IOException {
        if (!ObjectUtils.isEmpty(value)) {
            jsonGenerator.writeStringField(fieldName.toString(), value);
        }
    }

    public void writeStringField(final String value) throws IOException {
        if (!ObjectUtils.isEmpty(value)) {
            jsonGenerator.writeString(value);
        }
    }

    public void writeEndObject() throws IOException {
        jsonGenerator.writeEndObject();
    }


    public void writeEndArray() throws IOException {
        jsonGenerator.writeEndArray();
    }

}
