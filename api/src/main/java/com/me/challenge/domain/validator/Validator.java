package com.me.challenge.domain.validator;

/**
 * Class comments go here...
 *
 * @author Thiago Santos
 * @version 1.0 18/11/2020
 */
@FunctionalInterface
public interface Validator<T, S, Y> {
    T validate(S s, Y y);
}
