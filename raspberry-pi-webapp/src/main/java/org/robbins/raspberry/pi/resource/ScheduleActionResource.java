
package org.robbins.raspberry.pi.resource;

import org.robbins.raspberry.pi.exceptions.RaspberryPiAppException;
import org.robbins.raspberry.pi.model.PiAction;
import org.robbins.raspberry.pi.model.PiSchedule;
import org.robbins.raspberry.pi.service.PiActionService;
import org.robbins.raspberry.pi.service.PiScheduleService;
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
@Path("/schedule")
@Produces({ "application/xml", "application/json" })
@Consumes({ "application/xml", "application/json" })
public class ScheduleActionResource
{
    private static final Logger LOGGER = LoggerFactory.getLogger(ScheduleActionResource.class);

    @Autowired
    private PiScheduleService scheduleService;

    @POST
    public Response action(final PiSchedule piSchedule) throws RaspberryPiAppException {
        LOGGER.debug("Received request to display files");

        scheduleService.scheduleAction(piSchedule);

        return Response.status(200).build();
    }
}
