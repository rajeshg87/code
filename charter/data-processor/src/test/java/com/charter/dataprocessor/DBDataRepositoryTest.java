package com.charter.dataprocessor;

import com.charter.dataprocessor.db.DBDataRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.scheduler.Schedulers;

@SpringBootTest
public class DBDataRepositoryTest extends TestConfig{

    @Autowired
    private DBDataRepository repository;

    @Test
    public void testFindAll(){
        repository.findAll()
                .parallel()
                .runOn(Schedulers.parallel())
                .subscribe(data -> System.out.println(Thread.currentThread().getName()+" "+data));

        sleep(20000);
    }

}
