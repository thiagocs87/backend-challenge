package com.me.challenge.infra.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;
import java.math.BigDecimal;

/**
 *
 * @author Thiago Santos
 * @version 1.0 18/11/2020
 */
public abstract class AbstractDeserializer<T> extends JsonDeserializer<T> {

    @Override
    public T deserialize(final JsonParser jsonParser, final DeserializationContext deserializationContext) throws IOException {
        final ObjectCodec codec = jsonParser.getCodec();
        final JsonNode jsonNode = codec.readTree(jsonParser);
        return deserialize(jsonNode);
    }

    public abstract T deserialize(final JsonNode jsonNode) throws IOException;

    protected String getFieldTextValue(final JsonNode node, final Enum<?> fieldName) {
        return this.hasNonNullAndNonEmpty(node, fieldName) ? node.get(fieldName.toString()).textValue() : null;
    }

    protected BigDecimal getFieldBigDecimalValue(final JsonNode node, final Enum<?> fieldName) {
        return this.hasNonNullAndNonEmpty(node, fieldName) ? new BigDecimal(node.get(fieldName.toString()).toString()) : null;
    }

    protected Integer getFieldIntegerValue(final JsonNode node, final Enum<?> fieldName) {
        return this.hasNonNullAndNonEmpty(node, fieldName) ? node.get(fieldName.toString()).intValue() : null;
    }

    protected boolean hasNonNullAndNonEmpty(final JsonNode node, final Enum<?> fieldName) {
        return this.hasNonNull(node, fieldName) && node.get(fieldName.toString()).asText().length() > 0;
    }

    protected boolean hasNonNull(final JsonNode node, final Enum<?> fieldName) {
        return this.has(node, fieldName) && node.hasNonNull(fieldName.toString());
    }

    protected boolean has(final JsonNode node, final Enum<?> fieldName) {
        return node.has(fieldName.toString());
    }


}
