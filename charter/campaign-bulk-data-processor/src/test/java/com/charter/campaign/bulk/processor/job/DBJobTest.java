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
public class DBJobTest {

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job dbJob;


    @Test
    public void testDBJob(){
        Map<String, JobParameter> jobConfigMap = new HashMap<>();
        jobConfigMap.put("time", new JobParameter(System.currentTimeMillis()));
        jobConfigMap.put("campaign", new JobParameter("CAMPAIGN_4"));
        JobParameters parameters = new JobParameters(jobConfigMap);
        try {
            jobLauncher.run(dbJob, parameters);
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
