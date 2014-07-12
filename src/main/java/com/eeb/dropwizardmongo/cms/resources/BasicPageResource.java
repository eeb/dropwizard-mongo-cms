package com.eeb.dropwizardmongo.cms.resources;

import com.eeb.dropwizardmongo.cms.api.BasicPage;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST interface for the BasicPage type.
 */

@Path("/cms/{slug}")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BasicPageResource {


    /**
     * Creates a new BasicPage in the CMS system.
     * @param slug TODO: Relies on a properly formatted slug from the client. Should this be case?
     * @return A BasicPage with the _id field populated with the value from Mongo.
     */
    @PUT
    public BasicPage put(@PathParam("slug") String slug) {

        return null;
    }

    @POST
    public BasicPage post(@PathParam("slug") String slug) {

        return null;
    }

    @GET
    public BasicPage get(@PathParam("slug") String slug) {

        Response.ResponseBuilder response = Response.status(Response.Status.FORBIDDEN);
        response.entity("{\"message\":\"Object not found\"}");

        throw new WebApplicationException(response.build());


    }


}




