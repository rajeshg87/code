package com.charter.campaign.bulk.processor.utils;

import com.charter.campaign.bulk.processor.model.FieldMapper;
import com.charter.campaign.bulk.processor.model.Mapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
public class DBRowMapper implements RowMapper<String> {

    private Mapper mapper;

    private JsonUtils jsonUtils;

    public DBRowMapper(JsonUtils jsonUtils,Mapper mapper){
        this.jsonUtils = jsonUtils;
        this.mapper = mapper;
    }

    @Override
    public String mapRow(ResultSet rs, int rowNum){
        StringBuffer dataRow = mapper.getFieldMapper().stream().filter(p -> !p.isParameterValue())
                .map(m -> getData(rs, m))
                .collect(StringBuffer::new,
                        (s,v) -> s.append(v).append(mapper.getOutputFileDataSpliter()),
                        (s1,s2) -> {});

        Map<String, String> parameterValuesMap = mapper.getFieldMapper().stream().filter(FieldMapper::isParameterValue)
                .collect(Collectors.toMap(FieldMapper::getDisplayName, m -> getData(rs, m)));

        return dataRow.append(jsonUtils.createJsonString(parameterValuesMap)).toString();
    }

    private String getData(ResultSet rs, FieldMapper fieldMapper){
        try {
            return rs.getString(fieldMapper.getValueName());
        } catch (SQLException e) {
            return StringUtils.EMPTY;
        }
    }
}
