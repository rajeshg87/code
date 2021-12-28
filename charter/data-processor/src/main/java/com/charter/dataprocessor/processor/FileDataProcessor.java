package com.charter.dataprocessor.processor;

import com.charter.dataprocessor.mapper.DataMapper;
import com.charter.dataprocessor.utils.FileUtils;
import com.charter.dataprocessor.utils.JsonUtils;
import com.charter.dataprocessor.utils.PropertyUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.stream.BaseStream;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
@Slf4j
public class FileDataProcessor {

    private final PropertyUtils propertyUtils;
    private final JsonUtils jsonUtils;
    private final FileUtils fileUtils;

    public void processData() {

        try{
            String inputFileDataSplitter = propertyUtils.getProperty("input.file.data.splitter");

            Path inPath = Paths.get(propertyUtils.getProperty("input.data.file"));

            Path opPath = fileUtils.getOutputFilePath("FILE", propertyUtils.getCsvMapper());

            Flux.using(() -> Files.lines(inPath).skip(1).parallel().map(data -> mapRowData(data.split(inputFileDataSplitter),
                            propertyUtils.getCsvMapper())),
                    Flux::fromStream,
                    BaseStream::close
            ).subscribe(data -> fileUtils.writeToFile(opPath, data));
        }catch (IOException e){
            log.error("IOException in Processing Data {}", e.getMessage());
        }
    }

    private String mapRowData(String[] dataArray, List<DataMapper> mapper){
        String outputFileDataSplitter = propertyUtils.getProperty("output.file.data.splitter");
        StringBuffer dataBuffer = new StringBuffer();
        dataBuffer.append(mapper.stream().filter(pv -> !pv.isParameterValue()).map(m ->  dataArray[m.getDataPosition()])
                .collect(Collectors.joining(outputFileDataSplitter)));


        Map<String, String> parameterValuesMap = mapper.stream().filter(DataMapper::isParameterValue)
                .collect(Collectors.toMap(m -> m.getDisplayName(), m -> dataArray[m.getDataPosition()]));

        return dataBuffer.append(outputFileDataSplitter)
                .append(jsonUtils.createJsonString(parameterValuesMap)).toString();
    }

}
