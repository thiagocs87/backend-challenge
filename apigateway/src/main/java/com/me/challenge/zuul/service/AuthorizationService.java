package com.me.challenge.zuul.service;

import com.me.challenge.zuul.client.AuthorizationClient;
import com.netflix.zuul.context.RequestContext;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthorizationService {

    private final AuthorizationClient client;

    public Boolean authorized(RequestContext ctx) {
        try {
            return (Boolean) client.login(ctx.getRequest().getHeader("token"));
        } catch (Exception e) {
            return false;
        }
    }

    public void authenticationError(RequestContext ctx, String msg) {
        ctx.setResponseStatusCode(401);
        ctx.setSendZuulResponse(false);
    }
}
