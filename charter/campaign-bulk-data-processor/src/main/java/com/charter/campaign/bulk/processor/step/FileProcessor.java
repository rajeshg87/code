package com.charter.campaign.bulk.processor.step;

import com.charter.campaign.bulk.processor.model.FieldMapper;
import com.charter.campaign.bulk.processor.model.Mapper;
import com.charter.campaign.bulk.processor.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Component
public class FileProcessor implements ItemProcessor<String, String> {

    public static final String INITIAL_SPLIT_CHAR = "\\";
    private Mapper mapper;

    @Autowired
    private JsonUtils jsonUtils;

    @Override
    public String process(String line) throws Exception {

        String updatedLine = mapRowData(
                line.split(StringUtils.join(INITIAL_SPLIT_CHAR,
                        mapper.getInputFileDataSpliter()))
        );

        //log.info("FileProcessor Line : {}", updatedLine);
        return updatedLine;
    }

    private String mapRowData(String[] dataArray){
        List<FieldMapper> fieldMappers = mapper.getFieldMapper();
        String outputFileDataSplitter = mapper.getOutputFileDataSpliter();

        StringBuffer dataBuffer = new StringBuffer();

        dataBuffer.append(fieldMappers.stream().filter(pv -> !pv.isParameterValue()).map(m -> mapData(dataArray, m.getDataPosition()))
                .collect(Collectors.joining(outputFileDataSplitter)));

        Map<String, String> parameterValuesMap = fieldMappers.stream().filter(FieldMapper::isParameterValue)
                .collect(Collectors.toMap(m -> m.getDisplayName(), m -> mapData(dataArray, m.getDataPosition())));

        return dataBuffer.append(outputFileDataSplitter)
                .append(jsonUtils.createJsonString(parameterValuesMap)).toString();
    }

    private String mapData(String[] dataArray, Integer dataPosition) {
        String data = StringUtils.EMPTY;
        try{
            data = Integer.signum(dataPosition) < 0 ?
                    data : dataArray[dataPosition];
        }catch (ArrayIndexOutOfBoundsException e){
            log.error("Data Not Found in Position : {} ", dataPosition);
        }
        return data;
    }

    @BeforeStep
    public void beforeFileProcessor(StepExecution stepExecution){
        Mapper mapper = (Mapper) stepExecution.getExecutionContext().get("mapper");
        this.mapper = mapper;
        log.info("Mapper in File Processor : {}", mapper.getFieldMapper());

    }
}
