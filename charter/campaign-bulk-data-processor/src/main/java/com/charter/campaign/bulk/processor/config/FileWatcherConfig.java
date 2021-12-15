package com.charter.campaign.bulk.processor.config;

import com.charter.campaign.bulk.processor.constant.ApplicationConstants;
import com.charter.campaign.bulk.processor.model.Campaign;
import com.charter.campaign.bulk.processor.utils.PropertyUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.nio.file.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
@Slf4j
public class FileWatcherConfig {

    private Map<WatchKey, Campaign> watchKeys = new HashMap<>();

    @Autowired
    private PropertyUtils propertyUtils;

    @Bean
    public WatchService watchService(){
        WatchService watchService = null;
        try {
            watchService = FileSystems.getDefault().newWatchService();

            List<Campaign> campaigns = propertyUtils.getCampaignTasks(ApplicationConstants.CAMPAIGN_TASKS_YML);
            for (Campaign campaign:campaigns){
                if(campaign.getTrigger().equals("watcher")){
                    Path path = Paths.get(campaign.getSourceDirectory());
                    if(!Files.isDirectory(path)){
                        throw new RuntimeException("Incorrect Path : "+path);
                    }
                    WatchKey key = path.register(watchService,
                            StandardWatchEventKinds.ENTRY_CREATE);

                    watchKeys.put(key, campaign);
                }
            }
        } catch (IOException e) {
            log.error("Exception creating File Watcher Service : {}", e.getMessage());
        }
        return watchService;
    }

    public Campaign getWatchKey(WatchKey watchKey){
        return watchKeys.get(watchKey);
    }
}
