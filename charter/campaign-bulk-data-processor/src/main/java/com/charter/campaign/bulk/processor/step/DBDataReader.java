package com.charter.campaign.bulk.processor.step;

import com.charter.campaign.bulk.processor.model.Campaign;
import com.charter.campaign.bulk.processor.model.CampaignDatasource;
import com.charter.campaign.bulk.processor.model.Mapper;
import com.charter.campaign.bulk.processor.utils.DBRowMapper;
import com.charter.campaign.bulk.processor.utils.JsonUtils;
import com.charter.campaign.bulk.processor.utils.PropertyUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.AfterStep;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.*;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.charter.campaign.bulk.processor.constant.ApplicationConstants.CAMPAIGN_TASKS_YML;
import static com.charter.campaign.bulk.processor.constant.ApplicationConstants.MAPPING_FILE_DIR;

@Component
@Slf4j
public class DBDataReader implements ItemReader<String> {

    private JdbcCursorItemReader<String> jdbcCursorItemReader;
    int rowCount = 0;

    @Autowired
    private PropertyUtils propertyUtils;

    @Autowired
    private JsonUtils jsonUtils;

    @Override
    public String read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        return jdbcCursorItemReader.read();
    }

    @BeforeStep
    public void beforeDBReader(StepExecution stepExecution) {
        try{
            log.info("Job Parameters ::::::: {}",stepExecution.getJobParameters());

            String campaignName = stepExecution.getJobParameters().getString("campaign");
            String mappingFileDir = propertyUtils.getProperty(MAPPING_FILE_DIR);

            Campaign campaign = propertyUtils.getCampaignTasks(CAMPAIGN_TASKS_YML)
                    .stream().filter(c -> c.getName().equals(campaignName)).findFirst().get();
            Mapper mapper = propertyUtils.getMapper(StringUtils.join(mappingFileDir, campaign.getMapping()));

            stepExecution.getExecutionContext().put("campaign", campaign);
            stepExecution.getExecutionContext().put("mapper", mapper);

            createJdbcCursorItemReader(mapper);
        }catch (Exception e){
            log.error("Exception DB Reader Before Step : {}", e.getMessage());
        }
    }

    @AfterStep
    public void dbReaderAfterStep(StepExecution stepExecution){
        jdbcCursorItemReader.close();
    }

    private void createJdbcCursorItemReader(Mapper mapper) {
        jdbcCursorItemReader = new JdbcCursorItemReader();
        jdbcCursorItemReader.setDataSource(createDataSource(mapper));
        jdbcCursorItemReader.setSql(mapper.getSql());
        jdbcCursorItemReader.setFetchSize(100);
        jdbcCursorItemReader.setRowMapper(new DBRowMapper(jsonUtils,mapper));
        jdbcCursorItemReader.open(new ExecutionContext());
    }

    private DataSource createDataSource(Mapper mapper) {
        CampaignDatasource datasource= mapper.getCampaignDatasource();
        return DataSourceBuilder.create()
                .driverClassName(datasource.getDriver())
                .url(datasource.getUrl())
                .username(datasource.getUsername())
                .password(datasource.getPassword())
                .build();
    }
}
