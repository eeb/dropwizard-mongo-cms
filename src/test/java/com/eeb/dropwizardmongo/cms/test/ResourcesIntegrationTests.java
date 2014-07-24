package com.eeb.dropwizardmongo.cms.test;

import com.eeb.dropwizardmongo.cms.CMSApplication;
import com.eeb.dropwizardmongo.cms.CMSConfiguration;
import com.eeb.dropwizardmongo.cms.api.BasicPage;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import io.dropwizard.testing.junit.DropwizardAppRule;
import org.bson.types.ObjectId;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Test;

import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.net.UnknownHostException;

import static java.lang.ClassLoader.getSystemClassLoader;

/**
 * Created by eeb on 7/12/14.
 */
public class ResourcesIntegrationTests {

    final private ObjectMapper m = new ObjectMapper();
    protected static DB mongoDB;
    private DBCollection collection;


    @ClassRule
    public static final DropwizardAppRule<CMSConfiguration> rule =
            new DropwizardAppRule<>(CMSApplication.class,
                    getSystemClassLoader().getResource("config.yaml").getPath());



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
        collection = mongoDB.getCollection("assets");
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

        JsonNode message = getMessage(response).get("message");;
        assert message.asText().equals("Object not found") : "Message is not correct.";
    }

    private JsonNode getMessage(ClientResponse response) throws IOException {
        String entity = response.getEntity(String.class);
        JsonNode rootNode = m.readValue(m.getFactory().createParser(entity), JsonNode.class);
        return rootNode;
    }

    @Test
    public void putTest() throws Exception {
        final BasicPage bp = new Mocks.MockBasicPage();
        final Client client = new Client();
        final WebResource resource = client.resource("http://localhost:8080/cms/item_1");
        ClientResponse response = resource.type(MediaType.APPLICATION_JSON).put(ClientResponse.class, m.writeValueAsString(bp));
        JsonNode node = getMessage(response);
        String id = node.get("_id").textValue();
        final BasicDBObject id1 = new BasicDBObject();
        id1.put("_id", new ObjectId(id));
        final DBCursor cursor = collection.find(id1);
        assert cursor.hasNext() : "Object was not inserted.";


    }

    @Test
    public void putErrorTest() throws Exception {
        final BasicPage bp = new Mocks.MockBasicPage();
        bp.setId("1");
        final Client client = new Client();
        final WebResource resource = client.resource("http://localhost:8080/cms/item_1");
        final ClientResponse response = resource.type(MediaType.APPLICATION_JSON).put(ClientResponse.class, m.writeValueAsString(bp));
        JsonNode message = getMessage(response).get("message");
        //TODO: This error message is not informative
        assert message.asText().equals("Error mapping BSON to POJOs") : "Invalid error message.";


    }



}
