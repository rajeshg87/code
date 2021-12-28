package com.charter.dataprocessor.utils;

import com.charter.dataprocessor.mapper.DataMapper;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@ConfigurationProperties(prefix = "mappers")
@Data
public class PropertyUtils {

    @Autowired
    private Environment environment;

    private List<DataMapper> csvMapper = new ArrayList<>();
    private List<DataMapper> dbMapper = new ArrayList<>();
    private String sql;

    public String getProperty(String name){
        return environment.getProperty(name, StringUtils.EMPTY);
    }

}
