package com.eeb.dropwizardmongo.cms.api;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by eeb on 7/2/14.
 */
public class BasicDetail {

    private String text;

    @JsonProperty
    public String getText() {
        return text;
    }

    @JsonProperty
    public void setText(String text) {
        this.text = text;
    }
}
