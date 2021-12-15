package com.charter.campaign.bulk.processor.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;

@Service
@Slf4j
public class DynamicSchedulerService {

    @Autowired
    private TaskScheduler taskScheduler;

    // A map for keeping scheduled tasks
    Map<String, ScheduledFuture<?>> jobsMap = new HashMap<>();

    public void addTaskToScheduler(String taskName, Runnable task, Date runningDate) {
        ScheduledFuture<?> scheduledTask = taskScheduler.schedule(task, runningDate);
        jobsMap.put(taskName, scheduledTask);
    }

    // Schedule Task to be executed based on Cron
    public void addTaskToScheduler(String taskName, Runnable task, CronTrigger cronTrigger) {
        ScheduledFuture<?> scheduledTask = taskScheduler.schedule(task, cronTrigger);
        jobsMap.put(taskName, scheduledTask);
    }

    // Remove scheduled task
    public void removeTaskFromScheduler(String taskName) {
        ScheduledFuture<?> scheduledTask = jobsMap.get(taskName);
        if (scheduledTask != null) {
            scheduledTask.cancel(true);
            jobsMap.put(taskName, null);
        }
    }

    // A context refresh event listener
    @EventListener({ContextRefreshedEvent.class})
    void contextRefreshedEvent() {
        // Get all tasks from DB and reschedule them in case of context restarted
    }
}
