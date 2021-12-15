package com.charter.campaign.bulk.processor.step;

import com.charter.campaign.bulk.processor.model.Campaign;
import com.charter.campaign.bulk.processor.model.Mapper;
import com.charter.campaign.bulk.processor.utils.PropertyUtils;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.AfterStep;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static com.charter.campaign.bulk.processor.constant.ApplicationConstants.CAMPAIGN_TASKS_YML;
import static com.charter.campaign.bulk.processor.constant.ApplicationConstants.MAPPING_FILE_DIR;

@Data
@Component
@Slf4j
public class FileReader implements ItemReader<String> {

    private BufferedReader br;

    @Autowired
    private PropertyUtils propertyUtils;


    @Override
    public String read() throws Exception, UnexpectedInputException,
            ParseException, NonTransientResourceException {
        String line = br.readLine();
        while(line != null){
            //log.info("File Reader : "+line);
          return line;
        }
        return null;
    }

    @BeforeStep
    public void beforeFileReader(StepExecution stepExecution) {
        try{
            log.info("Job Parameters ::::::: {}",stepExecution.getJobParameters());

            String campaignName = stepExecution.getJobParameters().getString("campaign");
            String inputFileName = stepExecution.getJobParameters().getString("inputFile");
            String mappingFileDir = propertyUtils.getProperty(MAPPING_FILE_DIR);

            Campaign campaign = propertyUtils.getCampaignTasks(CAMPAIGN_TASKS_YML)
                    .stream().filter(c -> c.getName().equals(campaignName)).findFirst().get();
            Mapper mapper = propertyUtils.getMapper(StringUtils.join(mappingFileDir, campaign.getMapping()));

            String inputFile = StringUtils.join(campaign.getSourceDirectory(), inputFileName);

            stepExecution.getExecutionContext().put("campaign", campaign);
            stepExecution.getExecutionContext().put("mapper", mapper);

            br = Files.newBufferedReader(Paths.get(inputFile));
            br.readLine();
        }catch (Exception e){
            log.error("Exception in Reading File : "+e.getMessage());
        }
    }

    @AfterStep
    public void afteFileReader(StepExecution stepExecution){
        try {
            br.close();
        } catch (IOException e) {
            log.error("After FileReader Step : {}", e.getMessage());
        }
    }
}
