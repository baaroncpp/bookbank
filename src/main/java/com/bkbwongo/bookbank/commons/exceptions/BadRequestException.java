package com.bkbwongo.bookbank.commons.exceptions;

/**
 * @author bkaaron
 * @created on 23/04/2021
 * @project ebaasa-sms
 */
public class BadRequestException extends RuntimeException{

    public BadRequestException(String message, Object ... messageConstants){
        super(String.format(message, messageConstants));
    }

    public BadRequestException(String message) {
        super(message);
    }
}
