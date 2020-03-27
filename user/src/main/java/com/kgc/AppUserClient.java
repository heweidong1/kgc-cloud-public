package com.kgc;

import com.config.OrderRuleConfig;
import com.config.PowerRuleConfig;
import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.netflix.ribbon.RibbonClients;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableEurekaClient
@RibbonClients({
        @RibbonClient(name="SERVER-ORDER",configuration = OrderRuleConfig.class),
        @RibbonClient(name="SERVER-POWER",configuration = PowerRuleConfig.class)
})
@EnableFeignClients
@EnableHystrix
public class AppUserClient
{
    public static void main(String[] args) {
        SpringApplication.run(AppUserClient.class);
    }

    /**
     * 服务监控
     * @return
     */
    @Bean
    public ServletRegistrationBean getServlet(){

        HystrixMetricsStreamServlet streamServlet = new HystrixMetricsStreamServlet();

        ServletRegistrationBean registrationBean = new ServletRegistrationBean(streamServlet);

        registrationBean.setLoadOnStartup(1);

        registrationBean.addUrlMappings("/actuator/hystrix.stream");

        registrationBean.setName("HystrixMetricsStreamServlet");


        return registrationBean;
    }

}
