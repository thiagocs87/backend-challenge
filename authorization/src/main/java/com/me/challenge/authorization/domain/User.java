package com.me.challenge.authorization.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Users")
@Getter
@Setter
public class User {
    private String username;
    private String password;
}
