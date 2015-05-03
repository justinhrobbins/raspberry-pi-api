
package org.robbins.raspberry.pi.resource;

import org.robbins.raspberry.pi.exceptions.RaspberryPiAppException;
import org.robbins.raspberry.pi.model.PiAction;
import org.robbins.raspberry.pi.model.RegisteredActions;
import org.robbins.raspberry.pi.service.PiActionServiceDelegator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.Set;

@Component
@Path("/")
@Produces({ "application/xml", "application/json" })
@Consumes({ "application/xml", "application/json" })
public class ActionResource
{
    private static final Logger LOGGER = LoggerFactory.getLogger(ActionResource.class);

    @Autowired
    private PiActionServiceDelegator serviceDelegator;

    @POST
    @Path("/pi-action")
    public Response postAction(final PiAction action) throws RaspberryPiAppException {
        LOGGER.debug("Received request to create action: {}", action);

        serviceDelegator.invokeAction(action);

        return Response.status(200).build();
    }

    @GET
    @Path("/pi-action")
    public Response listActions() {
        RegisteredActions actions = serviceDelegator.listRegisteredActions();
        return Response.ok(actions).build();
    }
}
