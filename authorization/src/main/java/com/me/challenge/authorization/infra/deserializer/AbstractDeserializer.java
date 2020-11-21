package com.me.challenge.authorization.infra.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;

public abstract class AbstractDeserializer<T> extends JsonDeserializer<T> {

    @Override
    public T deserialize(final JsonParser jsonParser, final DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        final ObjectCodec codec = jsonParser.getCodec();
        final JsonNode jsonNode = codec.readTree(jsonParser);
        return deserialize(jsonNode);
    }

    public abstract T deserialize(final JsonNode jsonNode) throws IOException;

    protected JsonNode get(final JsonNode node, final Enum<?> fieldName) {
        return node.get(fieldName.toString());
    }

}

