package com.ba.profiles.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@ConfigurationProperties("spring.datasource")
@SuppressWarnings("unused")
public class DBConfiguration {
    private String driverClassName;
    private String url;
    private String username;
    @Profile("dev")
    @Bean
    public String devDatabaseConnection(){
        System.out.println("Db connection for DEV - H2");
    return "Db connection for DEV - H2";
    }

    @Profile("prod")
    @Bean
    public String prodDatabaseConnection(){
        System.out.println("Db connection for Prod- Mysql");
        return "Db connection for Prod- Mysql";
    }
}
