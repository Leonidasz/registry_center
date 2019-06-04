package com.dangdang.arch.consul.sc.controller;

import com.dangdang.arch.consul.sc.service.FeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FeignController {

    @Autowired
    private FeignService service;

    @RequestMapping("/feignc")
    @ResponseBody
    public String get() {
        return this.service.hello();
    }

}
