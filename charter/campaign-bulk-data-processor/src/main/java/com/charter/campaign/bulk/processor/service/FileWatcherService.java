package com.charter.campaign.bulk.processor.service;

import com.charter.campaign.bulk.processor.config.FileWatcherConfig;
import com.charter.campaign.bulk.processor.model.Campaign;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.stereotype.Service;

import javax.annotation.PreDestroy;
import java.io.IOException;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
@AllArgsConstructor
public class FileWatcherService {

    private final WatchService watchService;

    private final FileWatcherConfig watcherConfig;

    private final JobLauncher jobLauncher;

    private final Job fileJob;

    public void launchFileWatcher(){
        log.info("Started File Watcher");
        try {
            WatchKey watchKey;
            while ((watchKey = watchService.take()) != null){
                Campaign campaign = watcherConfig.getWatchKey(watchKey);

                for (WatchEvent<?> event : watchKey.pollEvents()){
                    log.info("Event Kind : {}; File Affected: {}; File Path: {}",
                            event.kind(),
                            event.context(),
                            null);

                    if(event.context().toString().endsWith(".csv")){
                        Map<String, JobParameter> jobConfigMap = new HashMap<>();
                        jobConfigMap.put("time", new JobParameter(System.currentTimeMillis()));
                        jobConfigMap.put("campaign", new JobParameter(campaign.getName()));
                        jobConfigMap.put("inputFile", new JobParameter(event.context().toString()));
                        JobParameters parameters = new JobParameters(jobConfigMap);

                        log.info("Job Launcher Job Parameters : {}", parameters);

                        jobLauncher.run(fileJob, parameters);
                    }
                }
                watchKey.reset();
            }
        }catch (Exception e){
            log.error("Exception in Launch File Watcher : {}", e.getMessage());
        }
    }

    @PreDestroy
    public void stopFileWatcher(){
        log.info("Stop File Watcher");
        if(null != watchService){
            try {
                watchService.close();
            } catch (IOException e) {
                log.error("Exception in Stopping File Watcher : {}", e.getMessage());
            }
        }
    }
}
