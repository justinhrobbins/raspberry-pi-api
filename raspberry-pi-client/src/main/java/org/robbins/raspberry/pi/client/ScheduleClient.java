package org.robbins.raspberry.pi.client;

import org.robbins.raspberry.pi.model.PiSchedule;

public interface ScheduleClient
{
    void createSchedule(PiSchedule piSchedule);
    void deleteSchedule(String scheduleName);
}
