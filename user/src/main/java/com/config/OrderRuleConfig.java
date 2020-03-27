package com.config;

import com.netflix.loadbalancer.RoundRobinRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderRuleConfig {

    @Bean
    public RoundRobinRule roundRobinRule()
    {
        return new RoundRobinRule();
    }
}
