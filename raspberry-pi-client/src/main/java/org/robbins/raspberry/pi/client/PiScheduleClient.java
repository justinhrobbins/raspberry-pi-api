package org.robbins.raspberry.pi.client;

import org.robbins.raspberry.pi.model.PiSchedule;

public interface PiScheduleClient
{
    void createSchedule(PiSchedule piSchedule);
    void deleteSchedule(String scheduleName);
}
