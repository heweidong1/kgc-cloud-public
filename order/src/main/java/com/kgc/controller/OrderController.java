package com.kgc.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class OrderController {
    @RequestMapping("/getorder.do")
    public Object getorder()
    {
        Map<String,Object> map = new HashMap<>();
        map.put("key","order");
        return map;
    }
}
