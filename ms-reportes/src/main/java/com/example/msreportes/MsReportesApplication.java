package com.example.msreportes;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableFeignClients
@SpringBootApplication
public class MsReportesApplication {
    public static void main(String[] args) {
        SpringApplication.run(MsReportesApplication.class, args);
    }
}
