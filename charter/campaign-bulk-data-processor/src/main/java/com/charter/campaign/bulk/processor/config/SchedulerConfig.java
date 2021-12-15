package com.charter.campaign.bulk.processor.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

@EnableScheduling
@Configuration
public class SchedulerConfig {

    @Bean
    public TaskScheduler taskScheduler(){
        ThreadPoolTaskScheduler taskScheduler=new ThreadPoolTaskScheduler();
        taskScheduler.setThreadNamePrefix("Campaign-ThreadPool-Task-Scheduler");
        taskScheduler.setPoolSize(3);
        taskScheduler.initialize();
        return taskScheduler;
    }
}
