package ru.javapro.task4;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.SQLException;

@Configuration
public class MyConfig {
    @Bean
    public HikariCPDataSource hikariCPDataSource(){
        return new HikariCPDataSource();
    }
}
