package com.charter.campaign.bulk.processor.utils;

import com.charter.campaign.bulk.processor.model.Campaign;
import com.charter.campaign.bulk.processor.model.Mapper;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@SpringBootTest
public class PropertyUtilsTest {

    @Autowired
    private PropertyUtils propertyUtils;

    @Test
    public void testDataMapperUsingJackson(){
        try {
            String fileName = "campaign-4-mapping.yml";
            String mappingFile = StringUtils.join(propertyUtils.getProperty("mapping.file.dir"), fileName);
            ObjectMapper objectMapper=new ObjectMapper(new YAMLFactory());
            objectMapper.findAndRegisterModules();
            objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            Mapper mapper=objectMapper.readValue(new File(mappingFile), Mapper.class);
            System.out.println(mapper.getCampaignDatasource());
            System.out.println(mapper.getSql());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

   @Test
   public void testGetCampaignTasksUsingJackson(){
       try {
           String fileName = "campaign-tasks.yml";
           String mappingFile = StringUtils.join(propertyUtils.getProperty("mapping.file.dir"), fileName);
           ObjectMapper objectMapper=new ObjectMapper(new YAMLFactory());
           objectMapper.findAndRegisterModules();
           objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
           List<Campaign> campaigns=objectMapper.readValue(new File(mappingFile), new TypeReference<List<Campaign>>() {});
           for(Campaign campaign:campaigns){
               System.out.println(campaign);
           }
       }catch (Exception e){
           e.printStackTrace();
       }
   }
}
