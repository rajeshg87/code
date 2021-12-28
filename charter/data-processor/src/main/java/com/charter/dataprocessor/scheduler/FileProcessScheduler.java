package com.charter.dataprocessor.scheduler;

import com.charter.dataprocessor.processor.DBDataProcessor;
import com.charter.dataprocessor.processor.FileDataProcessor;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Slf4j
@AllArgsConstructor
public class FileProcessScheduler {

    private final FileDataProcessor dataFileProcessor;
    private final DBDataProcessor dbFileProcessor;


    //@Scheduled(cron = "${db.process.scheduler}")
    public void processFileFromDBData(){
        log.info("DB Data Process Started : {}",LocalDateTime.now());
        dbFileProcessor.processData();
    }

    //s@Scheduled(cron = "${file.process.scheduler}")
    public void processFileFromFileData(){
        log.info("File Data Execute Time : {}", LocalDateTime.now());
        dataFileProcessor.processData();
    }
}
