package com.me.challenge.zuul.filter.authentication;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.me.challenge.zuul.service.AuthorizationService;
import lombok.AllArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AuthenticationRouteFilter extends ZuulFilter {

    private Environment env;
    private AuthorizationService service;

    @Override
    public String filterType() {
        return "route";
    }

    @Override
    public int filterOrder() {
        return -1;
    }

    @Override
    public boolean shouldFilter() {
        final RequestContext ctx = RequestContext.getCurrentContext();
        return !AuthenticationEnum.NONE.equals(getAuthType(ctx));
    }

    @Override
    public Object run() {
        final RequestContext ctx = RequestContext.getCurrentContext();
        if(!service.authorized(ctx)){
            service.authenticationError(ctx, "AUTHENTICATION ERROR");
        }
        return null;
    }

    private AuthenticationEnum getAuthType(RequestContext ctx) {
        String proxy = (String) ctx.get("proxy");
        String authType = env.getRequiredProperty(String.format("zuul.routes.%s.auth-type", proxy));
        return AuthenticationEnum.valueOf(authType);
    }
}
