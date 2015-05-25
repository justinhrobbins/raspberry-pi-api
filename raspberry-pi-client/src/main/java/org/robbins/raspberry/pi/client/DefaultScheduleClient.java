/*
 * [y] hybris Platform
 *
 * Copyright (c) 2000-2015 hybris AG
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of hybris
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with hybris.
 */
package org.robbins.raspberry.pi.client;


import java.util.Collections;
import java.util.Map;

import org.robbins.raspberry.pi.model.PiSchedule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component("scheduleClient")
public class DefaultScheduleClient implements ScheduleClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultScheduleClient.class);
    private static final String SCHEDULE_ENDPOINT = "schedule";
    private static final String DELETE_SCHEDULE_ENDPOINT = "schedule/{scheduleName}";

    @Value("${server.address}")
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
