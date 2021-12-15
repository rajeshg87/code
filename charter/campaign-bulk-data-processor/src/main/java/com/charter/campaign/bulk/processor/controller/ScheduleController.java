package com.charter.campaign.bulk.processor.controller;

import com.charter.campaign.bulk.processor.service.TestDataService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/job")
@Slf4j
@AllArgsConstructor
public class ScheduleController {

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job fileJob;

    @Autowired
    private TestDataService testDataService;

    @GetMapping("/testdata/{limit}")
    public String generateTestData(@PathVariable Integer limit){
        try {
            testDataService.generateCustomerData(limit);
        }catch (Exception e){
            log.error("Exception In Generating Test Data : {}", e.getMessage());
        }
        return "Test Data Generation Started";
    }

    @PostMapping()
    public void startJob(@RequestBody Map<String, String> request) throws Exception {
        Map<String, JobParameter> jobConfigMap = new HashMap<>();
        jobConfigMap.put("time", new JobParameter(System.currentTimeMillis()));
        jobConfigMap.put("campaign", new JobParameter(request.get("campaign")));
        jobConfigMap.put("inputFile", new JobParameter(request.get("inputFile")));
        JobParameters parameters = new JobParameters(jobConfigMap);

        jobLauncher.run(fileJob, parameters);

    }
}

