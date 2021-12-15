package com.charter.campaign.bulk.processor.utils;

import com.charter.campaign.bulk.processor.model.FieldMapper;
import com.charter.campaign.bulk.processor.model.Mapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class FileUtils{

    public static final String INITIAL_DATA_SPLIT = "\\";
    @Autowired
    private PropertyUtils propertyUtils;

    public Path createOutputFileWithHeader(Mapper mapper, String outputFileName) throws IOException {

        String outputFilename = String.format("%s/%s_%s.csv", mapper.getOutputDataDir(),
                outputFileName,
                RandomStringUtils.randomAlphabetic(5));

        Path opPath = Paths.get(outputFilename);
        Files.createFile(opPath);

        String header = generateHeaders(mapper);
        writeToFile(opPath, header);
        return opPath;
    }

    public void writeToFile(Path path, String data) {
        try (BufferedWriter writer =
                     Files.newBufferedWriter(path, StandardCharsets.UTF_8, StandardOpenOption.APPEND)) {

            writer.write(data);
            writer.newLine();

        } catch (IOException e) {
            log.error("Exception in writing Data {} to File : {}", data, e.getMessage());
        }
    }

    private String generateHeaders(Mapper mapper) {
        List<FieldMapper> fieldMappers = mapper.getFieldMapper();
        String header = new StringBuffer(fieldMappers.stream()
                .filter(pv -> !pv.isParameterValue())
                .map(FieldMapper::getDisplayName)
                .collect(Collectors.joining(mapper.getOutputFileDataSpliter())))
                .append(mapper.getOutputFileDataSpliter())
                .append(propertyUtils.getProperty("parameter.header.value"))
                .toString()
                ;
        return header;
    }
}