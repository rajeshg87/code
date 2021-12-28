package com.charter.dataprocessor.db;

import com.charter.dataprocessor.mapper.DataMapper;
import com.charter.dataprocessor.utils.JsonUtils;
import com.charter.dataprocessor.utils.PropertyUtils;
import io.r2dbc.spi.Row;
import io.r2dbc.spi.RowMetadata;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class DBRowMapper implements BiFunction<Row, RowMetadata, String> {

    private final PropertyUtils propertyUtils;

    private final JsonUtils jsonUtils;

    @Override
    public String apply(Row row, RowMetadata rowMetadata) {

        String outputFileDataSplitter = propertyUtils.getProperty("output.file.data.splitter");

        StringBuffer dataRow = propertyUtils.getDbMapper().stream().filter(p -> !p.isParameterValue())
                .map(m -> row.get(m.getValueName()))
                .collect(StringBuffer::new,
                        (s,v) -> s.append(v).append(outputFileDataSplitter),
                        (s1,s2) -> {});

        Map<String, String> parameterValuesMap = propertyUtils.getDbMapper().stream().filter(DataMapper::isParameterValue)
                .collect(Collectors.toMap(DataMapper::getDisplayName, m -> row.get(m.getValueName()).toString()));

        return dataRow.append(jsonUtils.createJsonString(parameterValuesMap)).toString();
    }
}
