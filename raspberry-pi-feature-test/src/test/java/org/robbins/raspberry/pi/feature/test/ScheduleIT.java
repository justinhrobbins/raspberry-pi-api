package org.robbins.raspberry.pi.feature.test;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robbins.raspberry.pi.client.ScheduleClient;
import org.robbins.raspberry.pi.model.PiSchedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:applicatonContext-client.xml"})
public class ScheduleIT
{
    @Autowired
    private ScheduleClient scheduleClientClient;

    private static final String ACTION_NAME = "playSound";
    private static final String ACTION_VALUE = "tps_reports.wav";
    private static final String SCHEDULE_NAME = RandomStringUtils.randomAlphabetic(10);
    private static final String CRON_TRIGGER = "0 0/1 * * * ?";

    @Test
    public void testCreateAndDeleteSchedule() {
        PiSchedule schedule = createSchedule();
        scheduleClientClient.createSchedule(schedule);
        scheduleClientClient.deleteSchedule(schedule.getScheduleName());
    }

    private PiSchedule createSchedule() {
        PiSchedule schedule = new PiSchedule();
        schedule.setActionName(ACTION_NAME);
        schedule.setActionValue(ACTION_VALUE);
        schedule.setScheduleName(SCHEDULE_NAME);
        schedule.setTrigger(CRON_TRIGGER);
        return schedule;
    }
}
