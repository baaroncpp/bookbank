package com.bkbwongo.bookbank.dto.models;

import lombok.Data;

/**
 * @Project ddd
 * @Author bkaaron
 * @Date 18/03/2022 11:44
 **/
@Data
public class SearchTemplateObject {
    private String searchValue;
    private String searchBy;
    private int page;
    private int size;

    public void validate(){}
}
