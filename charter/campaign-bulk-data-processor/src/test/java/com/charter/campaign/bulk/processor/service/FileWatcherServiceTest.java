package com.charter.campaign.bulk.processor.service;

import com.charter.campaign.bulk.processor.config.FileWatcherConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class FileWatcherServiceTest {

    @Autowired
    private FileWatcherService fileWatcherService;

    @Autowired
    private FileWatcherConfig watcherConfig;

    @Test
    public void testLaunchFileWatcher(){
        try{
            fileWatcherService.launchFileWatcher();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
