package com.me.challenge.authorization.infra.integration;

import com.me.challenge.authorization.domain.Auth;
import com.me.challenge.authorization.domain.User;
import com.me.challenge.authorization.domain.repository.UserRepository;
import com.me.challenge.authorization.infra.cache.TokenCache;
import com.me.challenge.authorization.infra.jwtwebtoken.JwtTokenUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@AllArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final JwtTokenUtil jwtUtil;
    private final UserRepository repository;
    private final TokenCache cache;

    @Override
    public User checkValidUser(String login, String password) {
        return repository.findByUsernameAndPassword(login, password).orElse(null);
    }

    @Override
    public Auth login(Login login) {
        User user = checkValidUser(login.getLogin(), login.getPassword());
        if(Objects.nonNull(user)){
            String token = jwtUtil.generateToken(user.getUsername());
            cache.persistToken(token);
            return new Auth(token);
        }
        throw new RuntimeException("ERROR ON LOGIN");
    }

    @Override
    public boolean verifyToken(String token) {
        if(!jwtUtil.validateToken(token)) {
            return false;
        }
        return cache.tokenIsValid(token);
    }

}
