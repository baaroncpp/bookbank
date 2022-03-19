package com.bkbwongo.bookbank.commons;

import com.bkbwongo.bookbank.commons.exceptions.BadRequestException;
import org.springframework.util.StringUtils;

import java.util.Optional;

/**
 * @author bkaaron
 * @created 20/02/2022 - 1:27 PM
 * @project bookbank
 */
public class Validate {

    private Validate() {}

    public static void isTrue(boolean value, String message, Object ... params){
        if (!value) {
            throw new BadRequestException(message, params);
        }
    }

    public static void notNull(Object value, String message, String ... params){

        if (value == null) {
            throw new BadRequestException(message, params);
        }
    }

    public static void notEmpty(String value, String message, String ... params){
        if (!StringUtils.hasLength(value)) {
            throw new BadRequestException(message, params);
        }
    }

    public static void notEmpty(String value, String message){
        if (!StringUtils.hasLength(value)) {
            throw new BadRequestException(message);
        }
    }

    public static void isPresent(Optional<?> value, String message, Object ... params) {
        if (!value.isPresent()) {
            throw new BadRequestException(String.format(message, params));
        }
    }
}
