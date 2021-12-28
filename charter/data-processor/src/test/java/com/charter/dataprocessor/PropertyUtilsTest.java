package com.charter.dataprocessor;

import com.charter.dataprocessor.mapper.DataMapper;
import com.charter.dataprocessor.utils.PropertyUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@SpringBootTest
@Slf4j
public class PropertyUtilsTest {

    @Autowired
    private PropertyUtils propertyUtils;

    @Test
    public void testGetPropertyByName(){
        System.out.println(propertyUtils.getProperty("find.all.cutomer.bill.data"));
    }

    @Test
    public void testGetCsvMapper(){
        propertyUtils.getCsvMapper().stream().forEach(System.out::println);
    }

    @Test
    public void testDbMapper(){
        propertyUtils.getDbMapper().stream().forEach(System.out::println);
    }

    @Test
    public void testGetSql(){
        Yaml yaml=new Yaml();
        try(InputStream in = new FileInputStream(new File(propertyUtils.getProperty("mapping.file.location")))){
            PropertyUtils mapper=yaml.loadAs(in, PropertyUtils.class);
            System.out.println(mapper.getSql());
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
