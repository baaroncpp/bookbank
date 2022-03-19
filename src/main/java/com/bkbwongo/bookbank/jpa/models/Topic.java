package com.bkbwongo.bookbank.jpa.models;

import javax.persistence.*;

/**
 * @author bkaaron
 * @created 04/02/2022 - 12:48 PM
 * @project bookbank
 */
@Table(name = "t_topics")
@Entity
public class Topic extends BaseEntity{
    private String name;
    private String description;

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
