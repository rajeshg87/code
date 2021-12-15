package com.charter.campaign.bulk.processor.service;

import com.charter.campaign.bulk.processor.model.Campaign;
import com.charter.campaign.bulk.processor.utils.PropertyUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.support.CronTrigger;

import java.util.List;

@SpringBootTest
public class DynamicSchedulerServiceTest {

    @Autowired
    private DynamicSchedulerService dynamicSchedulerService;

    @Autowired
    private CampaignJobService campaignJobService;

    @Autowired
    private FileWatcherService fileWatcherService;

    @Autowired
    private PropertyUtils propertyUtils;

    @Test
    public void testTaskScheduling(){
        try {
            campaignJobService.campaignJobScheduler();
            fileWatcherService.launchFileWatcher();
            sleep(300000);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void sleep(int sleep) {
        try {
            Thread.sleep(sleep);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
