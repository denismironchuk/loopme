package com.mycompany.app.dto;

import com.mycompany.app.model.enums.AppType;
import com.mycompany.app.model.enums.ContentType;

import java.util.Set;

public class AppDto {
    private Integer id;
    private String name;
    private AppType type;
    private Set<ContentType> contentTypes;
    private Integer userId;

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public AppType getType() {
        return type;
    }

    public void setType(final AppType type) {
        this.type = type;
    }

    public Set<ContentType> getContentTypes() {
        return contentTypes;
    }

    public void setContentTypes(final Set<ContentType> contentTypes) {
        this.contentTypes = contentTypes;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(final Integer userId) {
        this.userId = userId;
    }
}
