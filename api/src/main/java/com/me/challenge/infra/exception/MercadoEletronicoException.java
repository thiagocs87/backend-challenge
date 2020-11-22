package com.me.challenge.infra.exception;

/**
 *
 * @author Thiago Santos
 * @version 1.0 18/11/2020
 */
public abstract class MercadoEletronicoException extends RuntimeException {

    protected MercadoEletronicoException(final String message) {
        super(message);
    }

}
