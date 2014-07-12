package com.eeb.dropwizardmongo.cms.test;

import com.eeb.dropwizardmongo.cms.CMSApplication;
import com.eeb.dropwizardmongo.cms.CMSConfiguration;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.oracle.javafx.jmx.json.JSONFactory;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import io.dropwizard.testing.junit.DropwizardAppRule;
import jdk.nashorn.internal.parser.JSONParser;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Test;

import java.io.IOException;
import java.net.UnknownHostException;

import static java.lang.ClassLoader.*;

/**
 * Created by eeb on 7/12/14.
 */
public class ResourcesTests {

    final private ObjectMapper m = new ObjectMapper();


    @ClassRule
    public static final DropwizardAppRule<CMSConfiguration> rule =
            new DropwizardAppRule<>(CMSApplication.class,
                    getSystemClassLoader().getResource("config.yaml").getPath());


    protected static DB mongoDB;

    @BeforeClass
    public static void setup() throws UnknownHostException {
        mongoDB = rule.getConfiguration().getMongoDBFactory().build(
                rule.getConfiguration().getMongoClientFactory().build(rule.getEnvironment())
        );
    }

    /**
     * Empty the CMS collection prior to each test
     */
    @Before
    public void beforeTest() {
        DBCollection collection = mongoDB.getCollection(rule.getConfiguration().getMongoDBFactory().getDbName());
        collection.remove(new BasicDBObject());

    }

    /**
     * Validate that a 403 error with a message that the requested item is not found
     * is returned.
     */
    @Test
    public void fourOThreeTest() throws IOException {

        Client client = new Client();
        ClientResponse response = client.resource(
                "http://localhost:8080/cms/item_1").get(ClientResponse.class);

        assert response.getStatus() == 403 : "Response is not a 403";

        String entity = response.getEntity(String.class);
        JsonNode rootNode = m.readValue(m.getFactory().createParser(entity), JsonNode.class);
        JsonNode message = rootNode.get("message");
        assert message.asText().equals("Object not found") : "Message is not correct.";
    }
}
