package org.robbins.raspberry.pi.client;

import org.robbins.raspberry.pi.model.PiAction;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component("piActionClient")
public class DefaultPiActionClient implements PiActionClient {

    private static final String PI_ACTION_URL = "/pi-action";

    @Value("${server.address}")
    private String serverAddress;

    private RestTemplate restTemplate = new RestTemplate();

    @Override
    public void postAction(final PiAction piAction)
    {
        ResponseEntity reponse = restTemplate.postForEntity(createUrl(), piAction, ResponseEntity.class);
    }

    private String createUrl() {
        return serverAddress + PI_ACTION_URL;
    }
}
