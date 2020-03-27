package com.kgc.controller;

import com.kgc.service.PowerFeignClient;
import com.kgc.util.R;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController
{
    @Autowired
    RestTemplate restTemplate;

    private static final String FOWER_URL= "http://SERVER-POWER";
    private static final String ORDER_URL= "http://SERVER-ORDER";

    @Autowired
    PowerFeignClient powerFeignClient;

    @RequestMapping("getUser")
    public R getUser()
    {
        Map<String,Object> map =new HashMap<String, Object>();
        map.put("key","user");
        return R.success("返回成功",map);
    }

    @RequestMapping("getFeignPower.do")
    public R getFeignPower()
    {
        System.out.println("进入方法");
        return R.success("操作成功",powerFeignClient.getPower());
    }

    @RequestMapping("getOrder.do")
    public R getOrder()
    {
        return R.success("操作成功",restTemplate.getForObject(ORDER_URL+"/getorder.do",Object.class));
    }




    /*
    * 限流，当有5个服务卡在这的时候，第六个会降级
    * */
    @RequestMapping("getPower.do")
    @HystrixCommand(fallbackMethod = "getPowerFullBack",
            threadPoolKey = "power",
            threadPoolProperties ={@HystrixProperty(name = "coreSize",value = "5")
            })
    public R getPower()
    {
        System.out.println("进入方法");
        return R.success("操作成功",restTemplate.getForObject(FOWER_URL+"/getPower.do",Object.class));
    }
    public R getPowerFullBack()
    {
        return R.error("系统正在维护中，请稍后重试");
    }

















}
