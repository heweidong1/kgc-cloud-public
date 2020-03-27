package com.kgc.controller;

import com.kgc.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api")
public class PowerController
{

    @RequestMapping("getPower.do")
    public Object getPower(String name)throws Exception
    {
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("key1","power");
        return map;
    }



















}
