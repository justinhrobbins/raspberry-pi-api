package org.robbins.raspberry.pi.client;


import org.robbins.raspberry.pi.model.PiAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component("piActionClient")
public class DefaultPiActionClient implements PiActionClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultPiActionClient.class);
    private static final String PI_ACTION_URL = "pi-action";

    @Value("${server.address}")
    private String serverAddress;

    private RestTemplate restTemplate = new RestTemplate();

    @Override
    public void postAction(final PiAction piAction)
    {
        String url = createUrl();
        LOGGER.debug("Posting to: {}", url);

        ResponseEntity reponse = restTemplate.postForEntity(url, piAction, ResponseEntity.class);
    }

    private String createUrl() {
        return serverAddress + PI_ACTION_URL;
    }
}
