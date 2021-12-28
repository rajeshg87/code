package com.charter.dataprocessor.processor;

import com.charter.dataprocessor.db.DBDataRepository;
import com.charter.dataprocessor.utils.FileUtils;
import com.charter.dataprocessor.utils.PropertyUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.scheduler.Schedulers;

import java.io.IOException;
import java.nio.file.Path;

@Component
@AllArgsConstructor
public class DBDataProcessor {

    private final DBDataRepository dbDataRepository;
    private final FileUtils fileUtils;
    private final PropertyUtils propertyUtils;

    public void processData(){
        try{
            Path opPath = fileUtils.getOutputFilePath("DB", propertyUtils.getDbMapper());

            dbDataRepository.findAll()
                    .parallel()
                    .runOn(Schedulers.parallel())
                    .subscribe(data -> fileUtils.writeToFile(opPath, data));

        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
