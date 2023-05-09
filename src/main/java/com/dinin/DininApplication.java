package com.dinin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class DininApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext app = SpringApplication.run(DininApplication.class, args);
        String port = app.getEnvironment().getProperty("server.port");
        System.out.println("文档地址 http://localhost:" + port + "/doc.html");
    }
}