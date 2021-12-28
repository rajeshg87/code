package com.charter.dataprocessor;

import com.charter.dataprocessor.processor.FileDataProcessor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DataFileProcessorTest extends TestConfig{

    @Autowired
    private FileDataProcessor dataFileProcessor;

    @Test
    public void testProcessDataFile(){
        dataFileProcessor.processData();
        sleep(20000);
    }
}
