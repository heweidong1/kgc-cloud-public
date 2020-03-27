package com.kgc.config;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {
    @Bean
    public RestTemplate restTemplate()
    {
        return new RestTemplate();
    }

//    @Bean
//    public TomcatServletWebServerFactory tomcat()
//    {
//        TomcatServletWebServerFactory tomcatServletWebServerFactory = new TomcatServletWebServerFactory();
//        tomcatServletWebServerFactory.setPort(6001);
//        return tomcatServletWebServerFactory;
//    }
}
