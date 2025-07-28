package com.example.vms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.vms.mapper")
public class wmsApplication {
    public static void main(String[] args) {
        SpringApplication.run(wmsApplication.class, args);
    }

}
