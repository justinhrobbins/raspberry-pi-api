package org.robbins.raspberry.pi.service;

import org.robbins.raspberry.pi.exceptions.RaspberryPiAppException;
import org.robbins.raspberry.pi.model.PiSchedule;

public interface PiScheduleService {

    void scheduleAction(final PiSchedule schedule) throws RaspberryPiAppException;
}
