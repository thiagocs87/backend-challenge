package com.me.challenge.authorization.infra.integration;

import com.me.challenge.authorization.domain.Auth;
import com.me.challenge.authorization.domain.User;

public interface LoginService {
    User checkValidUser(String login, String password);
    Auth login(Login login);
    boolean verifyToken(String token);
}
