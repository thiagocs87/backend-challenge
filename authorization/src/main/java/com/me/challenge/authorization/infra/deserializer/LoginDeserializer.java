package com.me.challenge.authorization.infra.deserializer;


import com.fasterxml.jackson.databind.JsonNode;
import com.me.challenge.authorization.infra.integration.Login;
import com.me.challenge.authorization.infra.serializer.SerializationLabels;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Slf4j
public class LoginDeserializer extends AbstractDeserializer<Login>{
    public Login deserialize(final JsonNode jsonNode) throws IOException {
        final String login = get(jsonNode, SerializationLabels.LOGIN).asText();
        final String password = get(jsonNode, SerializationLabels.PASSWORD).asText();
        return new Login(login, password);
    }
}
