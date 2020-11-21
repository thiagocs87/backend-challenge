package com.me.challenge.authorization.infra.converter;

@FunctionalInterface
public interface Converter<T, S> {
    T convert(S s);
}
