package com.me.challenge.infra.exceptionhandler;

/**
 *
 * @author Thiago Santos
 * @version 1.0 18/11/2020
 */
public class CreateOrderException extends RuntimeException {

    public CreateOrderException(String message) { super(message); }

}