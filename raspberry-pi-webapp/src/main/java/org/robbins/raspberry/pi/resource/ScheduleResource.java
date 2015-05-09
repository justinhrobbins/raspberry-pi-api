
package org.robbins.raspberry.pi.resource;

import org.robbins.raspberry.pi.exceptions.RaspberryPiAppException;
import org.robbins.raspberry.pi.model.PiSchedule;
import org.robbins.raspberry.pi.model.PiSchedules;
import org.robbins.raspberry.pi.scheduling.PiScheduleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.Set;

@Component
@Path("/schedule")
@Produces({ "application/xml", "application/json" })
@Consumes({ "application/xml", "application/json" })
public class ScheduleResource
{
    private static final Logger LOGGER = LoggerFactory.getLogger(ScheduleResource.class);

    @Autowired
    private PiScheduleService scheduleService;

    @POST
    public Response createSchedule(final PiSchedule piSchedule) throws RaspberryPiAppException {
        LOGGER.debug("Received request to create schedule: {}", piSchedule);

        scheduleService.createSchedule(piSchedule);

        return Response.status(200).build();
    }

    @GET
    public Response getSchedules() throws RaspberryPiAppException {
        LOGGER.debug("Received request to GET schedules");

        PiSchedules schedules = scheduleService.getSchedules();
        return Response.ok(schedules).build();
    }

    @DELETE
    @Path("/{scheduleName}")
    public Response deleteSchedule(@PathParam("scheduleName") final String scheduleName) throws RaspberryPiAppException {
        LOGGER.debug("Deleting schedule: '{}'", scheduleName);

        scheduleService.deleteSchedule(scheduleName);

        return Response.status(200).build();
    }
}
