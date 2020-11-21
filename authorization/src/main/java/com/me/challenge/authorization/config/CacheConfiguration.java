package com.me.challenge.authorization.config;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@EnableCaching
@Configuration
@RequiredArgsConstructor
public class CacheConfiguration {

    public final LettuceConnectionFactory lettuceConnectionFactory;

    @Bean
    public RedisTemplate<String, Boolean> redisTemplateStringList() {
        RedisTemplate<String, Boolean> template = new RedisTemplate<>();
        template.setConnectionFactory(this.lettuceConnectionFactory);
        template.setKeySerializer(new StringRedisSerializer());
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(new StringRedisSerializer());
        return template;
    }

}
