package org.robbins.raspberry.pi.client;


import java.util.Collections;

import org.robbins.raspberry.pi.model.PiSchedule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component("scheduleClient")
public class DefaultPiScheduleClient implements PiScheduleClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultPiScheduleClient.class);
    private static final String SCHEDULE_ENDPOINT = "schedule";
    private static final String DELETE_SCHEDULE_ENDPOINT = "schedule/{scheduleName}";

    @Value("${api.server.address}")
    private String serverAddress;

    private RestTemplate restTemplate = new RestTemplate();

    @Override
    public void createSchedule(final PiSchedule piSchedule)
    {
        LOGGER.debug("Posting to: {}", createUrl());

        restTemplate.postForEntity(createUrl(), piSchedule, ResponseEntity.class);
    }

    @Override
    public void deleteSchedule(final String scheduleName)
    {
        LOGGER.debug("Deleting to : {}", createUrl());
        restTemplate.delete(deleteUrl(), Collections.singletonMap("scheduleName", scheduleName));
    }

    private String createUrl() {
        return serverAddress + SCHEDULE_ENDPOINT;
    }
    private String deleteUrl() {
        return serverAddress + DELETE_SCHEDULE_ENDPOINT;
    }
}
