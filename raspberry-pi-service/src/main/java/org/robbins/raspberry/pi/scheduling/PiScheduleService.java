package org.robbins.raspberry.pi.scheduling;

import org.robbins.raspberry.pi.exceptions.RaspberryPiAppException;
import org.robbins.raspberry.pi.model.PiSchedule;
import org.robbins.raspberry.pi.model.PiSchedules;

import java.util.Set;

public interface PiScheduleService {

    void createSchedule(PiSchedule schedule) throws RaspberryPiAppException;
    PiSchedules getSchedules() throws RaspberryPiAppException;
    void deleteSchedule(final String scheduleName) throws RaspberryPiAppException;
}
