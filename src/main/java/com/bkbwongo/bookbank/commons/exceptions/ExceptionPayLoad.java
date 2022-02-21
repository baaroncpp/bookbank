package com.bkbwongo.bookbank.commons.exceptions;

import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

/**
 * @author bkaaron
 * @created on 23/04/2021
 * @project ebaasa-sms
 */
public class ExceptionPayLoad {

    private final String message;
    private final HttpStatus httpStatus;
    private final ZonedDateTime timeStamp;

    public ExceptionPayLoad(String message, HttpStatus httpStatus, ZonedDateTime timeStamp) {
        this.message = message;
        this.httpStatus = httpStatus;
        this.timeStamp = timeStamp;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public ZonedDateTime getTimeStamp() {
        return timeStamp;
    }
}
