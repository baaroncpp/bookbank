package com.bkbwongo.bookbank.commons.exceptions;
/**
 * @created on 23/04/2021
 * @project ebaasa-sms
 * @author  bkaaron
 */

public class ApiRequestException extends RuntimeException{

    public ApiRequestException(String message) {
        super(message);
    }

    public ApiRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}
