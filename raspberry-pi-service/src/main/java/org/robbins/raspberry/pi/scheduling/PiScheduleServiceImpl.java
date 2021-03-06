package org.robbins.raspberry.pi.scheduling;

import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.robbins.raspberry.pi.exceptions.RaspberryPiAppException;
import org.robbins.raspberry.pi.model.PiSchedule;
import org.robbins.raspberry.pi.model.PiSchedules;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.stereotype.Service;

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
        jobDetailFactoryBean.setJobClass(PiJob.class);
        jobDetailFactoryBean.getJobDataMap().put("piSchedule", piSchedule);
        jobDetailFactoryBean.afterPropertiesSet();

        return jobDetailFactoryBean.getObject();
    }

    @Override
    public PiSchedules getSchedules() throws RaspberryPiAppException {
        try {
            Set<JobKey> jobKeys = scheduler.getJobKeys(GroupMatcher.anyJobGroup());
            return convertJobKeysToPiSchedules(jobKeys);
        } catch (SchedulerException e) {
            throw new RaspberryPiAppException(e.getMessage());
        }
    }

    private PiSchedules convertJobKeysToPiSchedules(final Set<JobKey> jobKeys) {
        PiSchedules piSchedules = new PiSchedules();
        jobKeys.stream()
                .forEach(jobKey ->
                        piSchedules.getPiSchedules().add(convertJobKeyToPiSchedule(jobKey)));
        return piSchedules;
    }

    private PiSchedule convertJobKeyToPiSchedule(JobKey job) {
        PiSchedule schedule = new PiSchedule();
        schedule.setScheduleName(job.getName());
        return schedule;
    }

    @Override
    public void deleteSchedule(final String scheduleName) throws RaspberryPiAppException {
        try {
            scheduler.deleteJob(new JobKey(scheduleName, Scheduler.DEFAULT_GROUP));
        } catch (SchedulerException e) {
            throw new RaspberryPiAppException(e.getMessage());
        }
    }
}
