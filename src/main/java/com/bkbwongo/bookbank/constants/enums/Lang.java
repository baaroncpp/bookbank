package com.bkbwongo.bookbank.constants.enums;

/**
 * @author bkaaron
 * @created 04/02/2022 - 12:25 PM
 * @project bookbank
 */
public enum Lang {
    en("English"),
    ru("Russian");

    private String value;

    Lang(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
