package com.me.challenge.zuul.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(url = "${me.service.authorization.url}", name = "login")
public interface AuthorizationClient {

    @GetMapping("token/validation")
    Object login(@RequestHeader("token") String token);
}