package com.charter.dataprocessor;

import com.charter.dataprocessor.repository.Campaign;
import com.charter.dataprocessor.repository.CampaignRepository;
import com.charter.dataprocessor.scheduler.ScheduleTaskService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.support.CronTrigger;
import reactor.core.publisher.Flux;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
public class CampaignSchedulerTest extends TestConfig{

    @Autowired
    private CampaignRepository campaignRepository;

    @Autowired
    private ScheduleTaskService taskService;

    @Test
    public void initializeData(){

        campaignRepository.save(new Campaign(null, "FILE_TEST", "FILE", "0/50 * * * * ?", "file-mapping.yml", true))
                .subscribe(m -> System.out.println(m.getName()));

        sleep(5000);
    }

    @Test
    public void testTaskScheduling(){
        // Add a new task with runtime after 10 seconds
        //taskService.addTaskToScheduler(1, () -> System.out.println("my task is running -> 1"), Date.from(LocalDateTime.now().plusSeconds(10).atZone(ZoneId.systemDefault()).toInstant()));
        taskService.addTaskToScheduler(1, () -> System.out.println("My Task"+LocalDateTime.now()), new CronTrigger("0/5 * * * * ?"));
        // Remove scheduled task
        //taskService.removeTaskFromScheduler(1);
        sleep(5000);

    }
}
