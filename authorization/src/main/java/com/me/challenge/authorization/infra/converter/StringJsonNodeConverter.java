package com.me.challenge.authorization.infra.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class StringJsonNodeConverter implements Converter<JsonNode, String> {

    private final ObjectMapper objectMapper;

    @Override
    public JsonNode convert(final String value) {
        try {
            final JsonNode jsonNode = objectMapper.readTree(value);
            return jsonNode;
        } catch (final JsonProcessingException e) {
            return null;
        }
    }

}
