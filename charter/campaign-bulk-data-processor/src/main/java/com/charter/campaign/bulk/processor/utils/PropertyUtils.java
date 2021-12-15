package com.charter.campaign.bulk.processor.utils;

import com.charter.campaign.bulk.processor.constant.ApplicationConstants;
import com.charter.campaign.bulk.processor.model.Campaign;
import com.charter.campaign.bulk.processor.model.Mapper;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Component
@Slf4j
public class PropertyUtils {

    @Autowired
    private Environment environment;

    public String getProperty(String name){
        return environment.getProperty(name);
    }

    public Mapper getMapper(String mappingFile){
        Mapper mapper = null;
        try{
            ObjectMapper objectMapper=new ObjectMapper(new YAMLFactory());
            objectMapper.findAndRegisterModules();
            objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            mapper=objectMapper.readValue(new File(mappingFile), Mapper.class);
        }catch (Exception e){
            log.error("Exception in Reading Mapping File : {} ; {}", mappingFile, e.getMessage());
        }
        return mapper;
    }

    public List<Campaign> getCampaignTasks(String campaignTaskFile){
        List<Campaign> campaigns = null;
        try{
            String mappingFile = StringUtils.join(getProperty(ApplicationConstants.MAPPING_FILE_DIR), campaignTaskFile);
            ObjectMapper objectMapper=new ObjectMapper(new YAMLFactory());
            objectMapper.findAndRegisterModules();
            objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            campaigns=objectMapper.readValue(new File(mappingFile), new TypeReference<List<Campaign>>() {});
        }catch (Exception e){
            log.error("Exception in Reading Campaign Tasks : {} ; {}", campaignTaskFile, e.getMessage());
        }
        return campaigns;
    }

}
