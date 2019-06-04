package com.dangdang.arch.consul.sc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ConsulScApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsulScApplication.class, args);
    }

}
