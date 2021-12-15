package com.charter.campaign.bulk.processor.service;

import com.charter.campaign.bulk.processor.constant.ApplicationConstants;
import com.charter.campaign.bulk.processor.model.Campaign;
import com.charter.campaign.bulk.processor.utils.PropertyUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CampaignJobService {

    @Autowired
    private PropertyUtils propertyUtils;

    @Autowired
    private DynamicSchedulerService dynamicSchedulerService;

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job fileJob;

    public void campaignJobScheduler(){
        try {
            List<Campaign> campaigns = propertyUtils.getCampaignTasks(ApplicationConstants.CAMPAIGN_TASKS_YML);
            for (Campaign campaign:campaigns){
                if(campaign.getTrigger().equals("cron")){
                    dynamicSchedulerService.addTaskToScheduler(campaign.getName(),
                            () -> scheduleJobByCampaign(campaign),
                            new CronTrigger(campaign.getCron()));
                }
            }
        }catch (Exception e){
            log.error("Exception in Scheduling Job : {}", e.getMessage());
        }
    }

    public void scheduleJobByCampaign(Campaign campaign){
        try {
            List<String> files = Files.list(Paths.get(campaign.getSourceDirectory()))
                    .filter(Files::isRegularFile)
                    .filter(path -> path.toString().endsWith(".csv"))
                    .map(Path::getFileName)
                    .map(String::valueOf)
                    .collect(Collectors.toList());

            for(String file: files){
                Map<String, JobParameter> jobConfigMap = new HashMap<>();
                jobConfigMap.put("time", new JobParameter(System.currentTimeMillis()));
                jobConfigMap.put("campaign", new JobParameter(campaign.getName()));
                jobConfigMap.put("inputFile", new JobParameter(file));
                JobParameters parameters = new JobParameters(jobConfigMap);

                jobLauncher.run(fileJob, parameters);
            }
        } catch (IOException | JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException | JobParametersInvalidException e) {
            log.error("Exception in Scheduling Campaign Job {} : {}", campaign, e.getMessage());
        }
    }
}
