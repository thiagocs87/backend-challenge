package com.me.challenge.restapi.controlleradvice;

import com.me.challenge.infra.exceptionhandler.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

/**
 *
 * @author Thiago Santos
 * @version 1.0 18/11/2020
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CreateOrderException.class)
    public ResponseEntity<ExceptionResponse> createOrderFailed(CreateOrderException ex) {
        ExceptionResponse response = new ExceptionResponse();
        response.setErrorCode("INTERNAL ERROR");
        response.setErrorMessage(ex.getMessage());
        response.setTimestamp(LocalDateTime.now());
        this.logException(response);
        return new ResponseEntity<ExceptionResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UpdateOrderException.class)
    public ResponseEntity<ExceptionResponse> createOrderFailed(UpdateOrderException ex) {
        ExceptionResponse response = new ExceptionResponse();
        response.setErrorCode("INTERNAL ERROR");
        response.setErrorMessage(ex.getMessage());
        response.setTimestamp(LocalDateTime.now());
        this.logException(response);
        return new ResponseEntity<ExceptionResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ExceptionResponse> resourceNotFound(ResourceNotFoundException ex) {
        ExceptionResponse response = new ExceptionResponse();
        response.setErrorCode("NOT_FOUND");
        response.setErrorMessage(ex.getMessage());
        response.setTimestamp(LocalDateTime.now());
        this.logException(response);
        return new ResponseEntity<ExceptionResponse>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ConvertException.class)
    public ResponseEntity<ExceptionResponse> customException(ConvertException ex) {
        ExceptionResponse response=new ExceptionResponse();
        response.setErrorCode("BAD_REQUEST");
        response.setErrorMessage(ex.getMessage());
        response.setTimestamp(LocalDateTime.now());
        this.logException(response);
        return new ResponseEntity<ExceptionResponse>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> generalException(Exception ex) {
        ExceptionResponse response=new ExceptionResponse();
        response.setErrorCode("INTERNAL ERROR");
        response.setErrorMessage(ex.getMessage());
        response.setTimestamp(LocalDateTime.now());
        this.logException(response);
        return new ResponseEntity<ExceptionResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private void logException(ExceptionResponse exception) {
        log.error("Exception occurred: ErrorMessage {}, OccurredAt {}", exception.getErrorMessage(), exception.getTimestamp());
    }

}