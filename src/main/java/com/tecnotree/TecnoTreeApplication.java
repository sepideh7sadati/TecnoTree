package com.tecnotree;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class TecnoTreeApplication {
    public static void main(String[] args) {
        SpringApplication.run(TecnoTreeApplication.class, args);
    }

}
