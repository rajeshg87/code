package com.charter.campaign.bulk.processor.config;

import com.charter.campaign.bulk.processor.step.*;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableBatchProcessing
public class BatchJobConfig {

    @Autowired
    private JobBuilderFactory jobs;

    @Autowired
    private StepBuilderFactory steps;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private FileReader fileReader;

    @Autowired
    private FileProcessor fileProcessor;

    @Autowired
    private FileWriter fileWriter;

    @Autowired
    private DBDataReader dbReader;

    @Autowired
    private DBDataWriter dbWriter;

    @Autowired
    private DBDataProcessor dbProcessor;

    @Bean
    public Step fileProcessingStep(){
        return steps.get("processFile")
                .<String, String> chunk(1000)
                .reader(fileReader)
                .processor(fileProcessor)
                .writer(fileWriter)
                //.taskExecutor(taskExecutor())
                .build();
    }

    @Bean
    public Job fileJob(){
        return jobs
                .get("File-Job")
                .start(fileProcessingStep())
                .build();
    }

    @Bean
    public Job dbJob() {
        return jobs.get("DB-Job")
                .start(dbProcessingStep())
                .build();
    }

    @Bean
    public Step dbProcessingStep() {
        return steps.get("processDB")
                .<String, String> chunk(1)
                .reader(dbReader)
                .processor(dbProcessor)
                .writer(dbWriter)
                .build();
    }

    @Bean
    public TaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(15);
        taskExecutor.setMaxPoolSize(20);
        taskExecutor.setQueueCapacity(30);
        return taskExecutor;
    }

    @Bean
    public JobLauncher jobLauncher() throws Exception {
        SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
        //jobLauncher.setTaskExecutor(taskExecutor());
        jobLauncher.setJobRepository(jobRepository);
        jobLauncher.afterPropertiesSet();
        return jobLauncher;
    }

}
