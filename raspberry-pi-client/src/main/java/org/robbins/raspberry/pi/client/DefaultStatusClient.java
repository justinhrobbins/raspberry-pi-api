package org.robbins.raspberry.pi.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class DefaultStatusClient implements StatusClient {

    private static final String statusUrl = "status";

    @Value("${api.server.address}")
    private String serverAddress;

    private RestTemplate restTemplate = new RestTemplate();

    @Override
    public String getStatus() {
        return restTemplate.getForObject(serverAddress + "status", String.class);
    }
}
