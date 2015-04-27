package org.robbins.raspberry.pi.service;

import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.robbins.raspberry.pi.exceptions.RaspberryPiAppException;
import org.robbins.raspberry.pi.model.PiSchedule;
import org.robbins.raspberry.pi.scheduling.PlaySoundJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.TriggerBuilder.newTrigger;


@Service
public class PiScheduleServiceImpl implements PiScheduleService {

    @Autowired
    Scheduler scheduler;

    @Autowired
    private ApplicationContext context;

    @Override
    public void createSchedule(final PiSchedule piSchedule) throws RaspberryPiAppException {
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

    @Override
    public Set<PiSchedule> getSchedules() throws RaspberryPiAppException {
        try {
            Set<JobKey> jobKeys = scheduler.getJobKeys(GroupMatcher.anyJobGroup());
            return convertJobKeysToPiSchedules(jobKeys);
        } catch (SchedulerException e) {
            throw new RaspberryPiAppException(e.getMessage());
        }
    }

    // TODO: replace conversion with Dozer
    private Set<PiSchedule> convertJobKeysToPiSchedules(final Set<JobKey> jobKeys) {
        Set<PiSchedule> piSchedules = new HashSet<>();
        for (JobKey job : jobKeys) {
            PiSchedule schedule = new PiSchedule();
            schedule.setScheduleName(job.getName());
            piSchedules.add(schedule);
        }
        return piSchedules;
    }

    @Override
    public void deleteSchedule(final String scheduleName) throws RaspberryPiAppException {
        try {
            scheduler.deleteJob(new JobKey(scheduleName, scheduler.DEFAULT_GROUP));
        } catch (SchedulerException e) {
            throw new RaspberryPiAppException(e.getMessage());
        }
    }
}
