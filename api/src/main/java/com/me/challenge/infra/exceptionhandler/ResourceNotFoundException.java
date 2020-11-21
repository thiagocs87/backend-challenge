package com.me.challenge.infra.exceptionhandler;

/**
 *
 * @author Thiago Santos
 * @version 1.0 18/11/2020
 */
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }

}