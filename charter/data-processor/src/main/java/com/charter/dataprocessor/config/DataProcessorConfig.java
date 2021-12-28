package com.charter.dataprocessor.config;

import io.r2dbc.spi.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.r2dbc.connection.init.CompositeDatabasePopulator;
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer;
import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator;
import org.springframework.scheduling.annotation.EnableScheduling;


@Configuration
@EnableScheduling
public class DataProcessorConfig {

    @Bean
    ConnectionFactoryInitializer databaseInitializer(ConnectionFactory cf) {
        var initializer = new ConnectionFactoryInitializer();
        var populator = new CompositeDatabasePopulator(
                new ResourceDatabasePopulator(new ClassPathResource("schema.sql"))
        );
        initializer.setConnectionFactory(cf);
        initializer.setDatabasePopulator(populator);
        return initializer;
    }

}
