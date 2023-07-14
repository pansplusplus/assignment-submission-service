package com.ass.system.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
@Configuration
public class SpringJdbcConfig {
//    @Bean
//    public DataSource mysqlDataSource() {
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setUrl("jdbc:mysql://eu-cdbr-west-03.cleardb.net");
//        dataSource.setUsername("bc84558efbc28f");
//        dataSource.setPassword("35f315c0");
//        return dataSource;
//    }

    @Bean
    public DataSource mysqlDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/assign_system");
        dataSource.setUsername("root");
        dataSource.setPassword("password");
        return dataSource;
    }

}