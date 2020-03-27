package com.config;

import com.kgc.rule.KgcRule;
import com.netflix.loadbalancer.RoundRobinRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PowerRuleConfig {

    @Bean
    public RoundRobinRule kgcRule()
    {
        return new RoundRobinRule();
    }
}
