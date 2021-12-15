package com.charter.campaign.bulk.processor.job;

import org.junit.jupiter.api.Test;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
public class FileJobTest {

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job fileJob;


    @Test
    public void testFileJob(){
        Map<String, JobParameter> jobConfigMap = new HashMap<>();
        jobConfigMap.put("time", new JobParameter(System.currentTimeMillis()));
        jobConfigMap.put("campaign", new JobParameter("CAMPAIGN_2"));
        jobConfigMap.put("inputFile", new JobParameter("TestData_100.csv"));
        JobParameters parameters = new JobParameters(jobConfigMap);
        try {
            jobLauncher.run(fileJob, parameters);
            sleep(50000);
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
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
