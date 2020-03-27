package com.kgc.config;

import com.netflix.loadbalancer.RoundRobinRule;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * 这份配置类写在了kgc被主类扫描到得地方了，所以就会所有得服务使用轮询策略
 */

@Configuration
public class AppConfig {
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate()
    {
        return new RestTemplate();
    }

    /*
      轮询
    * */

//    @Bean
//    public RoundRobinRule iRule()
//    {
//        return new RoundRobinRule();
//    }

//    @Bean
//    public TomcatServletWebServerFactory tomcat()
//    {
//        TomcatServletWebServerFactory tomcatServletWebServerFactory = new TomcatServletWebServerFactory();
//        tomcatServletWebServerFactory.setPort(5000);
//        return tomcatServletWebServerFactory;
//    }
}
