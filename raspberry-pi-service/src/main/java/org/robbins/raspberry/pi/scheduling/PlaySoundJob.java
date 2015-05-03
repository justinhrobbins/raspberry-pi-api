package org.robbins.raspberry.pi.scheduling;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.robbins.raspberry.pi.exceptions.RaspberryPiAppException;
import org.robbins.raspberry.pi.model.PiAction;
import org.robbins.raspberry.pi.model.PiSchedule;
import org.robbins.raspberry.pi.service.PiActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class PlaySoundJob extends QuartzJobBean {

    @Qualifier("playSound")
    @Autowired
    private PiActionService playSoundService;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        try {
            // create PiAction with data from job
            final PiSchedule piSchedule = (PiSchedule)jobExecutionContext.getJobDetail().getJobDataMap().get("piSchedule");

            playSoundService.invokeAction(new PiAction("playSound", piSchedule.actionValue));
        }
        catch (RaspberryPiAppException e) {
            throw new JobExecutionException(e);
        }
    }
}
