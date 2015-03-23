package org.robbins.raspberry.pi.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DefaultStatusClient implements StatusClient {

    private static final String statusUrl = "/status";

    @Value("${server.address}")
    private String serverAddress;

    @Override
    public String getStatus() {
        return null;
    }
}
