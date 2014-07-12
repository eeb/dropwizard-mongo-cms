package com.eeb.dropwizardmongo.cms.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.mongojack.ObjectId;

/**
 * Created by eeb on 7/2/14.
 */
public class BasicPage extends MongoDocument {

    private String nonce;
    private Metadata metadata;


    @ObjectId
    @JsonProperty
    public String getNonce() {
        return nonce;
    }

    @ObjectId
    @JsonProperty
    public void setNonce(String nonce) {
        this.nonce = nonce;
    }

    @JsonProperty
    public Metadata getMetadata() {
        return metadata;
    }

    @JsonProperty
    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }
}
