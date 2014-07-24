package com.eeb.dropwizardmongo.cms.resources;

import com.eeb.dropwizardmongo.cms.api.BasicPage;
import com.eeb.dropwizardmongo.cms.api.Metadata;
import com.eeb.dropwizardmongo.cms.api.MongoDocument;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import org.bson.types.ObjectId;
import org.mongojack.DBCursor;
import org.mongojack.JacksonDBCollection;
import org.mongojack.WriteResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.concurrent.Callable;

/**
 * REST interface for the BasicPage type.
 */

@Path("/cms/{slug}")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BasicPageResource {


    private final DB mongoDb;

    public BasicPageResource(DB mongoDb) {
        this.mongoDb = mongoDb;
    }

    @PUT
    public MongoDocument put(@Valid BasicPage newPage) {
        final JacksonDBCollection<BasicPage,String> col = JacksonDBCollection.wrap(mongoDb.getCollection("assets"),
                BasicPage.class, String.class);
        WriteResult<BasicPage, String> res = null;
        try {
            res = col.insert(newPage);
        } catch(Exception e) {
            Response.ResponseBuilder response = Response.status(Response.Status.INTERNAL_SERVER_ERROR);
            response.entity("{\"message\":\""+e.getMessage()+"\"}");
            //TODO: Add logging
            throw new WebApplicationException(response.build());
        }

        MongoDocument d = new MongoDocument();
        d.setId(((ObjectId)res.getDbObject().get("_id")).toString());

        return d;
    }

    @POST
    public BasicPage post(@Valid BasicPage newPage) {

        return null;
    }

    /**
     * Returns a {@link com.eeb.dropwizardmongo.cms.api.BasicPage} using the slug as a query parameter.
     * @param slug
     * @return {@code BasicPage} object or a 403 if the object can not be found.
     */
    @GET
    public BasicPage get(@PathParam("slug") String slug) {

        JacksonDBCollection<BasicPage,String> col = JacksonDBCollection.wrap(mongoDb.getCollection("assets"),
                BasicPage.class, String.class);

        //Note: MongoJack does not support the AutoClose interface
        DBCursor<BasicPage> cursor = col.find(new BasicDBObject("slug",slug));
        try  {

            if(!cursor.hasNext()) {
                Response.ResponseBuilder response = Response.status(Response.Status.FORBIDDEN);
                response.entity("{\"message\":\"Object not found\"}");
                throw new WebApplicationException(response.build());
            } else {
                return cursor.next();
            }

        } finally {
           cursor.close();
         }

    }


}




