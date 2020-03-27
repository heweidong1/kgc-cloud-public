package com.kgc.service;

import org.springframework.stereotype.Component;

@Component
public class PowerServiceFallBack implements PowerFeignClient {
    @Override
    public Object getPower() {
        return "降级信息";
    }
}
