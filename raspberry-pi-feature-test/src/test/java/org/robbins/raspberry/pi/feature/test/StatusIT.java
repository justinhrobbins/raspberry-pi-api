package org.robbins.raspberry.pi.feature.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robbins.raspberry.pi.client.StatusClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:applicatonContext-client.xml"})
public class StatusIT {

    @Autowired
    private StatusClient statusClient;

    private static final String STATUS_OK = "Status: Ok";

    @Test
    public void testStatus() {
        String status = statusClient.getStatus();

        assertThat(status, is(STATUS_OK));
    }
}
