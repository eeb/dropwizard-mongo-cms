package com.eeb.dropwizardmongo.cms;

import com.eeb.dropwizardmongo.configuration.DropwizardMongoConfiguration;
import com.eeb.dropwizardmongo.factory.MongoFactory;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;

/**
 * Created by eeb on 7/2/14.
 */
public class CMSConfiguration extends DropwizardMongoConfiguration {

    @JsonCreator
    public CMSConfiguration(@JsonProperty("mongoDB") MongoFactory mongoFactory) {
        super(mongoFactory);
    }
}
