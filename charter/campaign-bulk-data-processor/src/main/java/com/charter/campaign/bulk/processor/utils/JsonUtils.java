package com.charter.campaign.bulk.processor.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Slf4j
public class JsonUtils {

    public String createJsonString(Map<String, String> parameterValuesMap){
        String parameterValues = StringUtils.EMPTY;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            ObjectNode rootNode = objectMapper.createObjectNode();
            parameterValues = objectMapper.writeValueAsString(parameterValuesMap);
        } catch (JsonProcessingException e) {
            log.error("JSON Exception in Processing File Data : {}", e.getMessage());
        }
        return parameterValues;
    }
}
