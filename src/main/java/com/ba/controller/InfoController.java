package com.ba.controller;

import com.ba.entity.ServerInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController

public class InfoController {

    @Value("${spring.profiles.active}")
    private String profile;
    @Value("${server.port}")
    private String serverPort;
    @Value("${spring.datasource.url}")
    private String  databaseUrl;
    @Value("${logging.level.org.hibernate.type}")
    private String  hiberbateType;
    @Value("${spring.jpa.show-sql}")
    private String  showSql;
    @Value("${spring.jpa.properties.hibernate.format_sql}")
    private String  hibernateFormat;
    @Value("${spring.datasource.driverClassName}")
    private String  driverClass;
    @Value("${spring.datasource.username}")
    private String  username;
    @Value("${spring.datasource.password}")
    private String  password;
    @Value("${app.message}")
    private String appmessage;


    @GetMapping("server-info")
    public List<ServerInfo> getInfo(){
        List<ServerInfo> server_info = new ArrayList<>();
        ServerInfo info11= new ServerInfo("Database",appmessage);
        server_info.add(info11);
        ServerInfo info1 = new ServerInfo("server.port",serverPort);
        server_info.add(info1);
        ServerInfo info4 = new ServerInfo("spring.profiles.active",profile);
        server_info.add(info4);
        ServerInfo info8 = new ServerInfo("spring.datasource.driverClassName",driverClass);
        server_info.add(info8);
        ServerInfo info2 = new ServerInfo("spring.datasource.url",databaseUrl);
        server_info.add(info2);
        ServerInfo info9 = new ServerInfo("spring.datasource.username",username);
        server_info.add(info9);
        ServerInfo info10= new ServerInfo("spring.datasource.password",password);
        server_info.add(info10);
        ServerInfo info5 = new ServerInfo("logging.level.org.hibernate.type",hiberbateType);
        server_info.add(info5);
        ServerInfo info6 = new ServerInfo("spring.jpa.show-sql",showSql);
        server_info.add(info6);
        ServerInfo info7 = new ServerInfo("spring.jpa.properties.hibernate.format_sql",hibernateFormat);
        server_info.add(info7);
        return server_info;

    }
    @GetMapping("/deneme")
    public String hello(){
        return "welcome";
    }
}

