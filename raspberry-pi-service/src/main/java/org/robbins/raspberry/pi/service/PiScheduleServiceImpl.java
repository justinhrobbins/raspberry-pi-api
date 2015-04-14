package org.robbins.raspberry.pi.service;
import static org.quartz.CronScheduleBuilder.*;
import org.quartz.*;
import org.quartz.impl.triggers.SimpleTriggerImpl;
import org.robbins.raspberry.pi.exceptions.RaspberryPiAppException;
import org.robbins.raspberry.pi.model.PiSchedule;
import org.robbins.raspberry.pi.scheduling.PlaySoundJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import static org.quartz.TriggerBuilder.*;
@Service
public class PiScheduleServiceImpl implements PiScheduleService {

    @Autowired
    Scheduler scheduler;

    @Autowired
    private ApplicationContext context;

    @Override
    public void scheduleAction(final PiSchedule piSchedule) throws RaspberryPiAppException {
        try {
            scheduler.scheduleJob(createJobDetail(piSchedule), createTrigger(piSchedule));
        }
        catch (SchedulerException e) {
            throw new RaspberryPiAppException(e.getMessage());
        }
    }

    private Trigger createTrigger(final PiSchedule piSchedule) {
        final CronTrigger trigger = newTrigger()
                .withIdentity(piSchedule.getScheduleName() + "Trigger")
                .withSchedule(cronSchedule(piSchedule.cronTrigger))
//                .forJob(piSchedule.actionName)
                .build();

         return trigger;
    }

    private JobDetail createJobDetail(final PiSchedule piSchedule) {
        final JobDetailFactoryBean jobDetailFactoryBean = new JobDetailFactoryBean();
        jobDetailFactoryBean.setApplicationContext(context);
        jobDetailFactoryBean.setName(piSchedule.getScheduleName());
        jobDetailFactoryBean.setJobClass(PlaySoundJob.class);
        jobDetailFactoryBean.getJobDataMap().put("piSchedule", piSchedule);
        jobDetailFactoryBean.afterPropertiesSet();

        return jobDetailFactoryBean.getObject();
    }
}
