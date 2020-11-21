package com.me.challenge.infra.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 *
 * @author Thiago Santos
 * @version 1.0 18/11/2020
 */
public abstract class AbstractSerializer<T> extends JsonSerializer<T> {

    @Override
    public void serialize(final T t, final JsonGenerator jsonGenerator, final SerializerProvider serializerProvider) throws IOException {
        final JsonWriter jsonWriter = new JsonWriter(jsonGenerator);
        serialize(t, jsonWriter);
    }

    public abstract void serialize(final T t, final JsonWriter jsonWriter) throws IOException;

}
