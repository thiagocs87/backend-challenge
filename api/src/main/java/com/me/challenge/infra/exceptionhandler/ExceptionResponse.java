package com.me.challenge.infra.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 *
 * @author Thiago Santos
 * @version 1.0 18/11/2020
 */

@Getter
public class ExceptionResponse {

    private String errorMessage;
    private String errorCode;

    private LocalDateTime timestamp;

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

}