package com.me.challenge.infra.exceptionhandler;

import com.me.challenge.infra.exception.MercadoEletronicoException;

/**
 *
 * @author Thiago Santos
 * @version 1.0 18/11/2020
 */
public class ResourceNotFoundException extends MercadoEletronicoException {

    public ResourceNotFoundException(String message) {
        super(message);
    }

}