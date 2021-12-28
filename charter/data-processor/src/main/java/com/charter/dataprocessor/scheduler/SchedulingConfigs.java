package com.charter.dataprocessor.scheduler;

import com.charter.dataprocessor.repository.CampaignRepository;
import com.charter.dataprocessor.utils.PropertyUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;

@Configuration
@EnableScheduling
@AllArgsConstructor
@Slf4j
public class SchedulingConfigs implements SchedulingConfigurer {

    private final CampaignRepository campaignRepository;
    private final PropertyUtils propertyUtils;
    //private final ScheduleTaskService taskService;

    @Bean
    public TaskScheduler poolScheduler() {
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.setThreadNamePrefix("ThreadPoolTaskScheduler");
        scheduler.setPoolSize(1);
        scheduler.initialize();
        return scheduler;
    }

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {

        log.info("Started Task Register at {}", LocalDateTime.now());

        campaignRepository.findAll()
                .subscribe(campaign -> {
                    log.info("CAMPAIGN DETAILS : {} ", campaign);
                });

        Yaml yaml=new Yaml();
        try(InputStream in = new FileInputStream(new File(propertyUtils.getProperty("mapping.file.location")))){
            PropertyUtils mapper=yaml.loadAs(in, PropertyUtils.class);
            System.out.println(mapper.getSql());
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
