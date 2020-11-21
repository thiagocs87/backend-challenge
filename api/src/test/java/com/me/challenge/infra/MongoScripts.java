package com.me.challenge.infra;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * @author Thiago Santos
 * @version 1.0 18/11/2020
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface MongoScripts {
    /**
     * @return
     */
    MongoScript[] scripts();
}
