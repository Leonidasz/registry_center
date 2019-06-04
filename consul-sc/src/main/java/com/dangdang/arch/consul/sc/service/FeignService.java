package com.dangdang.arch.consul.sc.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("service-provider")
@Service
public interface FeignService {

    @RequestMapping(value="/hello")
    public String hello();

}
