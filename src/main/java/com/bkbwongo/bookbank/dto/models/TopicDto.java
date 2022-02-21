package com.bkbwongo.bookbank.dto.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;

/**
 * @author bkaaron
 * @created 04/02/2022 - 12:53 PM
 * @project bookbank
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class TopicDto {
    private Long id;
    private Date createdOn;
    private Date modifiedOn;
    private String name;
    private String description;
}
