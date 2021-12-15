package com.charter.campaign.bulk.processor.step;

import com.charter.campaign.bulk.processor.model.Mapper;
import com.charter.campaign.bulk.processor.utils.FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.AfterStep;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.file.Path;
import java.util.List;

@Component
@Slf4j
public class DBDataWriter implements ItemWriter<String> {

    int rowCount = 0;
    private Mapper mapper;
    private Path outputFilePath;

    @Autowired
    private FileUtils fileUtils;

    @Override
    public void write(List<? extends String> lines) throws Exception {
        rowCount += 1;
        lines.stream()
                .forEach(line -> fileUtils.writeToFile(outputFilePath, line));

        //lines.stream().forEach(line -> log.info("FileWriter : {}", line));
    }

    @BeforeStep
    public void beforeDBDataWriter(StepExecution stepExecution){
        try{
            Mapper mapper = (Mapper) stepExecution.getExecutionContext().get("mapper");
            this.mapper = mapper;

            String campaign = stepExecution.getJobParameters().getString("campaign");
            outputFilePath = fileUtils.createOutputFileWithHeader(mapper, campaign);

        }catch (Exception e){
            log.error("File Write Exception : {}", e.getMessage());
        }
    }

    @AfterStep
    public void afterDBDataWriter(){
        log.info("Total DB Rows : {}", rowCount);
    }
}
