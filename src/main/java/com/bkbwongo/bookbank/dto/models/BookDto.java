package com.bkbwongo.bookbank.dto.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;

/**
 * @author bkaaron
 * @created 04/02/2022 - 11:00 AM
 * @project bookbank
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class BookDto {
    private Long id;
    private Date createdOn;
    private Date modifiedOn;
    private String title;
    private String author;
    private String year;
    private String edition;
    private String publisher;
    private Long topicId;
    private Long fileSize;
    private String fileExtension;
    private String md5;
    private String sha1;
    private String fileName;
    private String description;
    private String filePath;
    private String originalFileName;
    private String language;
}
