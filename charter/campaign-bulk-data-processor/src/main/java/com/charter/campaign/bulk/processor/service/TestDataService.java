package com.charter.campaign.bulk.processor.service;

import com.charter.campaign.bulk.processor.model.CustomerTestData;
import com.charter.campaign.bulk.processor.utils.FileUtils;
import com.github.javafaker.Faker;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Locale;
import java.util.Random;

@Service
public class TestDataService {

    public static final String TEST_DATA_FILE_DIR = "/Users/rajeshg87/code/charter/input/";
    public static final String HEADER = "AccountId|Division|Type|Phone|Email|Sms|Ivr|Address 1|Address 2" +
            "|City|State|Zip|Name|Due Date|Last Statment|Online Bill";

    @Autowired
    private FileUtils fileUtils;

    Long id = 8000000000000000L;

    public void generateCustomerData(Integer limit){
        try{
            Random random=new Random();

            String fileName =  String.format("%sTestData_%s.csv", TEST_DATA_FILE_DIR, limit);

            Path path = Paths.get(fileName);
            Files.createFile(path);

            fileUtils.writeToFile(path, HEADER);

            Flux.range(1, limit)
                    .parallel()
                    .runOn(Schedulers.parallel())
                    .map(i -> createTestData(i))
                    .subscribe( data -> fileUtils.writeToFile(path, data.toString()))
            ;

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private CustomerTestData createTestData(Integer i) {
        Faker faker = new Faker(new Random(i));
        CustomerTestData testData=new CustomerTestData();
        Long count = id + i;
        testData.setBillingAccountNumber(count);
        testData.setSpcDivisionId("CHTR."+ RandomUtils.nextInt(7000,9000));
        testData.setCustomerType("RES");
        testData.setCustomerNumber(faker.phoneNumber().phoneNumber());
        testData.setEmail(String.format("E%S.gmail.com",count));
        testData.setSmsPhoneNumber(null);
        testData.setIvrPhoneNumber(faker.phoneNumber().cellPhone());
        testData.setAddressLine1(String.format("%s %s", faker.address().buildingNumber(),
                faker.address().streetName()));
        testData.setAddressLine2(null);
        testData.setCity(faker.address().city());
        testData.setState(faker.address().state().toUpperCase(Locale.US));
        testData.setZip(faker.address().zipCode().toUpperCase(Locale.US));

        testData.setCustomerName(faker.name().fullName());
        testData.setDueDate(LocalDate.now().plusDays(RandomUtils.nextInt(5,10)).toString());
        testData.setLastStatementBalance(String.valueOf(RandomUtils.nextDouble(150.01, 210.02)));
        testData.setEBillSubscribed("Y");
        return testData;
    }
}
