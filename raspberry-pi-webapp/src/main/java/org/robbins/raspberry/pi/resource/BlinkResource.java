
package org.robbins.raspberry.pi.resource;

import org.robbins.raspberry.pi.model.PiAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/")
@Produces({ "application/xml", "application/json" })
@Consumes({ "application/xml", "application/json" })
public class BlinkResource
{
    private static final Logger LOGGER = LoggerFactory.getLogger(BlinkResource.class);

    @POST
    @Path("/blink")
    public Response blink(PiAction action) {
        LOGGER.info("Received request: {}", action.toString());

        return Response.status(200).build();
    }
}
