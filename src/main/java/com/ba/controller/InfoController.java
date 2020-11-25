package com.ba.controller;

import com.ba.repository.ProductRepository;
import model.ServerInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController

public class InfoController {

    @Value("${server.port}")
    private String serverPort;


    @Value("${spring.datasource.url}")
    private String  databaseUrl;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String  ddlAuto;


    @Value("${spring.h2.console.enabled}")
    private String  consolenabled;


    @Value("${logging.level.org.hibernate.type}")
    private String  hiberbateType;


    @Value("${spring.jpa.show-sql}")
    private String  showSql;


    @Value("${spring.jpa.properties.hibernate.format_sql}")
    private String  hibernateFormat;


    @GetMapping("server-info")
    public List<ServerInfo> getInfo(){
        List<ServerInfo> server_info = new ArrayList<>();
        ServerInfo info1 = new ServerInfo("server.port",serverPort);
        server_info.add(info1);
        ServerInfo info2 = new ServerInfo("spring.datasource.url",databaseUrl);
        server_info.add(info2);
        ServerInfo info3 = new ServerInfo("spring.jpa.hibernate.ddl-auto",ddlAuto);
        server_info.add(info3);
        ServerInfo info4 = new ServerInfo("spring.h2.console.enabled",consolenabled);
        server_info.add(info4);
        ServerInfo info5 = new ServerInfo("logging.level.org.hibernate.type",hiberbateType);
        server_info.add(info5);
        ServerInfo info6 = new ServerInfo("spring.jpa.show-sql",showSql);
        server_info.add(info6);
        ServerInfo info7 = new ServerInfo("spring.jpa.properties.hibernate.format_sql",hibernateFormat);
        server_info.add(info7);
        return server_info;

    }
}
