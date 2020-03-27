package com.kgc.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name="SERVER-POWER",fallback = PowerServiceFallBack.class)
public interface PowerFeignClient
{
    @RequestMapping("/getPower.do")
    public Object getPower();

}
