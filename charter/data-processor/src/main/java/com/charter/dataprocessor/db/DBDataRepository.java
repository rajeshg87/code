package com.charter.dataprocessor.db;

import com.charter.dataprocessor.utils.PropertyUtils;
import lombok.AllArgsConstructor;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
@AllArgsConstructor
public class DBDataRepository {

    private final DatabaseClient dbc;

    private final DBRowMapper mapper;

    private final PropertyUtils propertyUtils;

    public Flux<String> findAll(){
        Flux<String> data = dbc.sql(propertyUtils.getProperty("find.all.customer.bill.data"))
                .map(mapper::apply)
                .all();

        return data;
    }

}
