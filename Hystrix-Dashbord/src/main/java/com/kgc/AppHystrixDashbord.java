package com.kgc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@EnableHystrixDashboard
@SpringBootApplication
public class AppHystrixDashbord
{
    public static void main(String[] args) {
        SpringApplication.run(AppHystrixDashbord.class);

    }
}
