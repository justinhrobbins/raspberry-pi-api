
package org.robbins.raspberry.pi.resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/status")
@Produces("text/plain")
@Consumes("text/plain")
public class StatusResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(StatusResource.class);

    @GET
    public String getStatus() {
        LOGGER.debug("Received request: status: OK");

        return "Status: Ok";
    }
}
