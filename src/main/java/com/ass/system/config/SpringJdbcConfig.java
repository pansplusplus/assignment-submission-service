package com.ass.system.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
@Configuration
public class SpringJdbcConfig {


    @Bean
    public DataSource mysqlDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl("jdbc:mysql://b06bc2fe1fc17f:d7911769@eu-cdbr-west-03.cleardb.net/heroku_ac343e17d3fd718?reconnect=true");
        dataSource.setUsername("b06bc2fe1fc17f");
        dataSource.setPassword("d7911769");
        return dataSource;
    }

}