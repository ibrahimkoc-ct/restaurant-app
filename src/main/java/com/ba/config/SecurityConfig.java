package com.ba.config;


import com.ba.auth.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


import javax.sql.DataSource;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private DataSource dataSource;

    @Bean
    public UserDetailsService userDetailsService(){
        return new UserDetailsServiceImpl();
    }
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authProvider= new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("h2-console/**").permitAll();
        http.csrf().disable();
        http.headers().frameOptions().disable();
       http.authorizeRequests().antMatchers("/role/list").access("hasRole('ADMIN')");
      http.authorizeRequests().antMatchers("/role/add").access("hasRole('ADMIN')");
       http.authorizeRequests().antMatchers("/role/delete").access("hasRole('ADMIN')");
      http.authorizeRequests().antMatchers("/role/update").access("hasRole('ADMIN')");
        http.authorizeRequests().antMatchers("/role/id").access("hasRole('ADMIN')");
       http.authorizeRequests().antMatchers("/user/admin-login").access("hasRole('ADMIN')");
        http.authorizeRequests().antMatchers("/user/user-login").access("hasAnyRole('ADMIN','USER')");
      http.authorizeRequests().antMatchers("/user/add").access("hasRole('ADMIN')");
       http.authorizeRequests().antMatchers("/user/delete").access("hasRole('ADMIN')");
       http.authorizeRequests().antMatchers("/user/update").access("hasRole('ADMIN')");
        http.authorizeRequests().antMatchers("/user/id").access("hasRole('ADMIN')");
        http.authorizeRequests().antMatchers("/user/list").access("hasRole('ADMIN')");
       http.authorizeRequests().antMatchers("/backoffice/**").access("hasRole('ADMIN')");
      http.authorizeRequests().antMatchers("/client/**").access("hasAnyRole('ADMIN','USER')");
        http.authorizeRequests().antMatchers("/productsales/add").access("hasAnyRole('ADMIN','USER')");
       http.authorizeRequests().antMatchers("/productsales/list").access("hasRole('ADMIN')");
        http.authorizeRequests().antMatchers("/category/add").access("hasRole('ADMIN')");
        http.authorizeRequests().antMatchers("/category/list").access("hasAnyRole('ADMIN','USER')");
        http.authorizeRequests().antMatchers("/category/delete").access("hasRole('ADMIN')");
        http.authorizeRequests().antMatchers("/category/id").access("hasRole('ADMIN')");
        http.authorizeRequests().antMatchers("/category/update").access("hasRole('ADMIN')");
        http.authorizeRequests().antMatchers("/category/product**").access("hasAnyRole('ADMIN','USER')");
        http.authorizeRequests().antMatchers("/categorytable/list").access("hasAnyRole('ADMIN','USER')");
        http.authorizeRequests().antMatchers("/categorytable/delete").access("hasRole('ADMIN')");
        http.authorizeRequests().antMatchers("/categorytable/add").access("hasRole('ADMIN')");
        http.authorizeRequests().antMatchers("/categorytable/id").access("hasRole('ADMIN')");
        http.authorizeRequests().antMatchers("/categorytable/update").access("hasRole('ADMIN')");
        http.authorizeRequests().antMatchers("/server-info").access("hasRole('ADMIN')");
        http.authorizeRequests().antMatchers("/waiter/add").access("hasRole('ADMIN')");
        http.authorizeRequests().antMatchers("/waiter/list").access("hasAnyRole('ADMIN','USER')");
        http.authorizeRequests().antMatchers("/waiter/delete").access("hasRole('ADMIN')");
        http.authorizeRequests().antMatchers("/waiter/id").access("hasRole('ADMIN')");
        http.authorizeRequests().antMatchers("/waiter/update").access("hasRole('ADMIN')");


        http.httpBasic();
        http.cors();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }
}
