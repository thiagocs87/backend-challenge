package com.me.challenge.authorization.restapi;

import com.me.challenge.authorization.domain.Auth;
import com.me.challenge.authorization.infra.integration.Login;
import com.me.challenge.authorization.infra.integration.LoginService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;

@AllArgsConstructor
@RestController
public class AuthorizationController {

    private final LoginService service;

    @ResponseStatus(CREATED)
    @PostMapping("/login")
    Auth login(@RequestBody Login payload) {
        return service.login(payload);
    }

    @ResponseStatus(OK)
    @GetMapping("/token/validation")
    Boolean login(@RequestHeader(value = "token", required = false) final String token) {
        return service.verifyToken(token);
    }
}
