package com.ba.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("h2-console/**").permitAll();
        http.csrf().disable();
        http.headers().frameOptions().disable();
        http.authorizeRequests().antMatchers("/hi/user").access("hasAnyRole('USER','ADMIN')");
        http.authorizeRequests().antMatchers("/hi/admin").access("hasRole('ADMIN')");
        http.authorizeRequests().antMatchers("/user/**").access("hasRole('ADMIN')");
        http.authorizeRequests().antMatchers("/backoffice/**").access("hasRole('ADMIN')");
        http.authorizeRequests().antMatchers("/client/**").access("hasAnyRole('ADMIN','USER')");
        http.authorizeRequests().antMatchers("/productsales/add").access("hasAnyRole('ADMIN','USER')");
        http.authorizeRequests().antMatchers("/productsales/list").access("hasRole('ADMIN')");





        http.httpBasic();
        http.cors();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource);
    }


}
