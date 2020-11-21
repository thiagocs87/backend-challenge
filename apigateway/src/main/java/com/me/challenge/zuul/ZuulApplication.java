package com.me.challenge.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@SpringBootApplication
@EnableZuulProxy
@EnableFeignClients
public class ZuulApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZuulApplication.class, args);
    }

    @Configuration
    @PropertySources(
            {@PropertySource("classpath:/routes/route-api.properties"),
             @PropertySource("classpath:/routes/authorization.properties")
            })
    public class RoutesConfig {
    }
}
