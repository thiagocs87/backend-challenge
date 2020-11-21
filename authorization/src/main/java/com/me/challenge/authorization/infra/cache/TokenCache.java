package com.me.challenge.authorization.infra.cache;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

@NoArgsConstructor
@Component
@Slf4j
public class TokenCache extends AbstractCacheService <String, Boolean>{

    private static final String tokenKeyPrefix = "authorization:";

    public boolean tokenIsValid(final String token) {
        try {
            return super.getCacheValue(buildTokenKey(token));
        } catch (Exception e) {
            log.error("Error on getting token ", e);
            return false;
        }
    }

    public void persistToken(final String token) {
        try {
            super.persistWithTtl(buildTokenKey(token), true, TimeUnit.DAYS, 1);
        } catch (Exception e) {
             log.error("Error on persisting token ", e);
        }
    }

    private String buildTokenKey(String token) {
        return tokenKeyPrefix.concat(token);
    }

}
