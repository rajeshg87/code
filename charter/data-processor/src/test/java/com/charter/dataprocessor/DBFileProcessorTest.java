package com.charter.dataprocessor;

import com.charter.dataprocessor.processor.DBDataProcessor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DBFileProcessorTest extends TestConfig{

    @Autowired
    private DBDataProcessor dbFileProcessor;

    @Test
    public void testProcessFileFromDBData(){
        try {
            dbFileProcessor.processData();
            sleep(20000);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
