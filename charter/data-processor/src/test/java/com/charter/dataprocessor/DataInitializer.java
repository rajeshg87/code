package com.charter.dataprocessor;

import com.charter.dataprocessor.db.DBDataRepository;
import com.charter.dataprocessor.repository.CustomerBill;
import com.charter.dataprocessor.repository.CustomerBillRespository;
import com.github.javafaker.Faker;
import org.apache.commons.lang3.RandomUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.util.Locale;
import java.util.Random;
import java.util.stream.Collectors;

@SpringBootTest
public class DataInitializer extends TestConfig{

    Long id = 8000000000009999L;

    @Autowired
    CustomerBillRespository customerBillRespository;

    @Autowired
    DBDataRepository dataRepository;

    @Test
    public void testGetAllCoffee(){
        dataRepository.findAll().collect(Collectors.joining())
                .subscribe(System.out::println);
    }

    @Test
    public void testDataGenerate_CSV() {

        try{
            Random random=new Random();

            String fileName = "/Users/rajeshg87/code/charter/input/TestData_10000.csv";
            Path path = Paths.get(fileName);
            Files.createFile(path);

            Flux.range(1, 50000)
                    .parallel(10)
                    .runOn(Schedulers.parallel())
                    .map(i -> createTestData(i))
                    .subscribe( data -> writeToFile(path, data))
            ;

        }catch (IOException e){
            e.printStackTrace();
        }

        sleep(600000);

    }

    @Test
    public void testDataGenerate_DB() {

        try{
            Random random=new Random();

            Flux.range(0, 10000)
                    .parallel(10)
                    .runOn(Schedulers.parallel())
                    .map(i -> createCustomerBillData(i))
                    .subscribe( data -> writeToDB(data))
            ;

        }catch (Exception e){
            e.printStackTrace();
        }

        sleep(300000);

    }

    @Test
    public void testDBData(){
        Flux<CustomerBill> customerBillData = customerBillRespository.findAll();
        System.out.println("Collected Data");
        customerBillData.count().subscribe(System.out::println);
    }

    private void writeToDB(CustomerBill data) {
        customerBillRespository.save(data)
                .subscribe();
    }

    private void writeToFile(Path path, TestData data) {
        try (BufferedWriter writer =
                     Files.newBufferedWriter(path, StandardCharsets.UTF_8, StandardOpenOption.APPEND)) {

            writer.write(data.toString());
            writer.newLine();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private TestData createTestData(Integer i) {
        Faker faker = new Faker(new Random(i));
        TestData testData=new TestData();
        testData.setBillingAccountNumber(id+i);
        testData.setSpcDivisionId("CHTR."+ RandomUtils.nextInt(7000,9000));
        testData.setCustomerType("RES");
        testData.setCustomerNumber(faker.phoneNumber().phoneNumber());
        testData.setEmail((id+i)+".gmail.com");
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

    private CustomerBill createCustomerBillData(Integer i) {
        Faker faker = new Faker(new Random(i));
        CustomerBill customerBill=new CustomerBill();
        customerBill.setAccountNumber(id+i);
        customerBill.setDivisionId("CHTR."+ RandomUtils.nextInt(7000,9000));
        customerBill.setCustomerType("RES");
        customerBill.setPhoneNumber(faker.phoneNumber().phoneNumber());
        customerBill.setEmail((id+i)+".gmail.com");
        customerBill.setSmsNumber(null);
        customerBill.setIvrNumber(faker.phoneNumber().cellPhone());
        customerBill.setAddressOne(String.format("%s %s", faker.address().buildingNumber(),
                faker.address().streetName()));
        customerBill.setAddressTwo(null);
        customerBill.setCity(faker.address().city());
        customerBill.setState(faker.address().state().toUpperCase(Locale.US));
        customerBill.setZip(faker.address().zipCode().toUpperCase(Locale.US));

        customerBill.setName(faker.name().fullName());
        customerBill.setDueDate(LocalDate.now().plusDays(RandomUtils.nextInt(5,10)).toString());
        customerBill.setLastStatementBalance(String.valueOf(RandomUtils.nextDouble(150.01, 210.02)));
        customerBill.setEBill("Y");
        return customerBill;
    }
}
