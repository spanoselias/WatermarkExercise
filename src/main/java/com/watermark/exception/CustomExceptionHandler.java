package com.watermark.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * @author Elias Spanos
 */
@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(InvalidTicketIdException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public final ResponseEntity<ErrorMsg> handleInsuranceNotAvailableException(InvalidTicketIdException ex) {
        return new ResponseEntity<>(new ErrorMsg(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
