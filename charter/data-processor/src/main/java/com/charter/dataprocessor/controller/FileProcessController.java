package com.charter.dataprocessor.controller;

import com.charter.dataprocessor.processor.DBDataProcessor;
import com.charter.dataprocessor.processor.FileDataProcessor;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/file")
@AllArgsConstructor
public class FileProcessController {

    private final DBDataProcessor dbFileProcessor;
    private final FileDataProcessor fileProcessor;

    @GetMapping(value = "/db")
    public String processFileFromDBData(){
        dbFileProcessor.processData();
        return "Completed File Process from DB Data";
    }

    @GetMapping(value = "/csv")
    public String processFileFromFileData(){
        fileProcessor.processData();
        return "Completed File Process from File Data";
    }
}
