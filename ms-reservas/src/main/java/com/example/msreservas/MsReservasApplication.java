package com.example.msreservas;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableFeignClients
@SpringBootApplication
public class MsReservasApplication {
    public static void main(String[] args) {
        SpringApplication.run(MsReservasApplication.class, args);
    }
}
