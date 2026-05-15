package com.example.mspagos;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableFeignClients
@SpringBootApplication
public class MsPagosApplication {
    public static void main(String[] args) {
        SpringApplication.run(MsPagosApplication.class, args);
    }
}
