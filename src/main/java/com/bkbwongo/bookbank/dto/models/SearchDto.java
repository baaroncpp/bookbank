package com.bkbwongo.bookbank.dto.models;

import com.bkbwongo.bookbank.commons.Validate;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * @author bkaaron
 * @created 20/02/2022 - 1:15 PM
 * @project bookbank
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class SearchDto {
    private String search;
    private boolean title;
    private boolean author;
    private boolean year;
    private boolean publisher;
    private boolean topic;
    private boolean md5;
    private boolean defaultSearch;
    private int page;
    private int size;
    private String sort;

    public void validate(){
        Validate.notEmpty(search, "Null search value");
        Validate.notEmpty(sort, "Null sort value");
    }
}
