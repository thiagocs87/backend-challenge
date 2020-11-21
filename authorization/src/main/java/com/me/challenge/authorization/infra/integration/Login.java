package com.me.challenge.authorization.infra.integration;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.me.challenge.authorization.infra.deserializer.LoginDeserializer;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonDeserialize(using = LoginDeserializer.class)
public class Login {
    private String login;
    private String password;
}
