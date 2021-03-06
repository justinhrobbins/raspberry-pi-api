package org.robbins.raspberry.pi.feature.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robbins.raspberry.pi.client.PiActionClient;
import org.robbins.raspberry.pi.model.PiAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:applicatonContext-client.xml"})
public class PiActionIT
{
    @Autowired
    private PiActionClient actionClient;

    private static final String ACTION_NAME = "playSound";
    private static final String ACTION_VALUE = "tps_reports.wav";

    @Test
    public void testPostAction() {
        actionClient.postAction(new PiAction(ACTION_NAME, ACTION_VALUE));
    }
}
