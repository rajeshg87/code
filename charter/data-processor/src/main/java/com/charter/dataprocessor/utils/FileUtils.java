package com.charter.dataprocessor.utils;

import com.charter.dataprocessor.mapper.DataMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
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

@Component
@AllArgsConstructor
@Slf4j
public class FileUtils {

    private final PropertyUtils propertyUtils;

    public Path getOutputFilePath(String processType, List<DataMapper> dataMappers) throws IOException {
        String outputFilename = String.format("%s/%s_%s.csv", propertyUtils.getProperty("output.data.dir"),
                processType,
                RandomStringUtils.randomAlphabetic(5));

        Path opPath = Paths.get(outputFilename);
        Files.createFile(opPath);

        String header = generateHeaders(dataMappers);
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


    private String generateHeaders(List<DataMapper> dataMappers) {
        String outputDataSplitter = propertyUtils.getProperty("output.file.data.splitter");
        String header = new StringBuffer(dataMappers.stream()
                .filter(pv -> !pv.isParameterValue())
                .map(DataMapper::getDisplayName)
                .collect(Collectors.joining(outputDataSplitter)))
                .append(outputDataSplitter)
                .append(propertyUtils.getProperty("parameter.header.value"))
                .toString()
                ;
        return header;
    }
}
