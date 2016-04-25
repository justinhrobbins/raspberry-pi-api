package org.robbins.raspberry.pi;

import org.glassfish.jersey.server.ResourceConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Path;
import java.util.Map;

@Component
@ApplicationPath("/v1")
public class JerseyConfig extends ResourceConfig {

    private static final Logger log = LoggerFactory.getLogger(JerseyConfig.class);

    @Autowired
    ApplicationContext appCtx;

    @PostConstruct
    public void setup() {
        Map<String,Object> beans = appCtx.getBeansWithAnnotation(Path.class);
        for (Object o : beans.values()) {
            log.info("Registering REST class: " + o.getClass().getName());
            register(o);
        }
    }
}
