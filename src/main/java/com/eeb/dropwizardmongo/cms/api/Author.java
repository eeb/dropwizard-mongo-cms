package com.eeb.dropwizardmongo.cms.api;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by eeb on 7/2/14.
 */
public class Author extends MongoDocument {

    private String name;

    @JsonProperty
    public String getName() {
        return name;
    }

    @JsonProperty
    public void setName(String name) {
        this.name = name;
    }
}
