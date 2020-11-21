package com.me.challenge.infra.integration.converter;

/**
 *
 * @author Thiago Santos
 * @version 1.0 18/11/2020
 */
@FunctionalInterface
public interface Converter<T, S> {
    T convert(S s);
}
