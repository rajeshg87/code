package com.charter.dataprocessor.scheduler;

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
public class ScheduleTaskService {

    // Task Scheduler
    TaskScheduler scheduler;

    // A map for keeping scheduled tasks
    Map<Integer, ScheduledFuture<?>> jobsMap = new HashMap<>();

    public ScheduleTaskService(TaskScheduler scheduler) {
        this.scheduler = scheduler;
    }


    // Schedule Task to be executed every night at 00 or 12 am
    public void addTaskToScheduler(int id, Runnable task, Date runningDate) {
        ScheduledFuture<?> scheduledTask = scheduler.schedule(task, runningDate);
        jobsMap.put(id, scheduledTask);
    }

    // Schedule Task to be executed every night at 00 or 12 am
    public void addTaskToScheduler(int id, Runnable task, CronTrigger cronTrigger) {
        ScheduledFuture<?> scheduledTask = scheduler.schedule(task, cronTrigger);
        jobsMap.put(id, scheduledTask);
    }

    // Remove scheduled task
    public void removeTaskFromScheduler(int id) {
        ScheduledFuture<?> scheduledTask = jobsMap.get(id);
        if (scheduledTask != null) {
            scheduledTask.cancel(true);
            jobsMap.put(id, null);
        }
    }

    // A context refresh event listener
    @EventListener({ContextRefreshedEvent.class})
    void contextRefreshedEvent() {
        // Get all tasks from DB and reschedule them in case of context restarted
    }
}
