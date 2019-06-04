package com.dangdang.arch.consul.sp.controller;

import com.dangdang.arch.consul.sp.vo.ParamInVo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServiceProviderController {

    @Value("${server.port}")
    private Integer port;

    @RequestMapping("/hello")
    public String sayHello() {
        return "hello! "+" svc.port:"+port;
    }

    @RequestMapping("/t1")
    @ResponseBody
    public String t1(@RequestBody String params) {
        System.out.println(params);
        return "t1 response: ok";
    }

    @RequestMapping("/t2")
    @ResponseBody
    public String t2(@RequestBody ParamInVo params) {
        return "t2 response: "+params.getName();
    }
}
