
package org.robbins.raspberry.pi.resource;

import org.robbins.raspberry.pi.exceptions.RaspberryPiAppException;
import org.robbins.raspberry.pi.model.PiAction;
import org.robbins.raspberry.pi.service.PiActionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Component
@Path("/")
@Produces({ "application/xml", "application/json" })
@Consumes({ "application/xml", "application/json" })
public class ActionResource
{
    private static final Logger LOGGER = LoggerFactory.getLogger(ActionResource.class);

    @Autowired
    private PiActionService actionService;

    @POST
    @Path("/pi-action")
    public Response action(final PiAction action) throws RaspberryPiAppException {
        LOGGER.debug("Received request: {}", action);

        actionService.invokeAction(action);

        return Response.status(200).build();
    }
}
