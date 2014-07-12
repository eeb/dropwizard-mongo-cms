package com.eeb.dropwizardmongo.cms.api;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by eeb on 7/2/14.
 */
public class Metadata {

    private String type;
    private String section;
    private String slug;
    private String title;
    private Date created;
    private Author author;
    private List<String> tags = new ArrayList<>();
    private BasicDetail detail;

    @JsonProperty
    public String getType() {
        return type;
    }

    @JsonProperty
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty
    public String getSection() {
        return section;
    }

    @JsonProperty
    public void setSection(String section) {
        this.section = section;
    }

    @JsonProperty
    public String getSlug() {
        return slug;
    }

    @JsonProperty
    public void setSlug(String slug) {
        this.slug = slug;
    }

    @JsonProperty
    public String getTitle() {
        return title;
    }

    @JsonProperty
    public void setTitle(String title) {
        this.title = title;
    }

    @JsonProperty
    public Date getCreated() {
        return created;
    }

    @JsonProperty
    public void setCreated(Date created) {
        this.created = created;
    }

    @JsonProperty
    public Author getAuthor() {
        return author;
    }

    @JsonProperty
    public void setAuthor(Author author) {
        this.author = author;
    }

    @JsonProperty
    public List<String> getTags() {
        return tags;
    }

    @JsonProperty
    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    @JsonProperty
    public BasicDetail getDetail() {
        return detail;
    }

    @JsonProperty
    public void setDetail(BasicDetail detail) {
        this.detail = detail;
    }
}
