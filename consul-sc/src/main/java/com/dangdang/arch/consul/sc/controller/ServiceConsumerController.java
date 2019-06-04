package com.dangdang.arch.consul.sc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class ServiceConsumerController {

    private final String SERVICE_NAME = "service-provider";

    @Autowired
    private LoadBalancerClient loadBalancer;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    RestTemplate restTemplate;

    @RequestMapping("/sd")
    public Object sd() {
        return discoveryClient.getInstances(SERVICE_NAME);
    }

    @RequestMapping("/sl")
    public String sl() {
        List<ServiceInstance> list = discoveryClient.getInstances(SERVICE_NAME);
        StringBuffer sb = new StringBuffer();
        if (list != null && list.size() > 0 ) {
            sb.append(list.get(0).getUri().toString()).append("\n");
        }
        return sb.toString();
    }

    @RequestMapping("/svc")
    public String svc() {
        return loadBalancer.choose(SERVICE_NAME).getUri().toString();
    }



    @RequestMapping("/lbs1")
    public String lbs1() {
        ServiceInstance serviceInstance = loadBalancer.choose(SERVICE_NAME);

        System.out.println("服务地址：" + serviceInstance.getUri());
        System.out.println("服务名称：" + serviceInstance.getServiceId());

        String callServiceResult = new RestTemplate().getForObject(serviceInstance.getUri().toString() + "/hello", String.class);
        System.out.println(callServiceResult);
        return callServiceResult;
    }


    @RequestMapping("/lbs2")
    public String lbs2() {
        return restTemplate.getForObject("http://"+SERVICE_NAME+"/hello", String.class);
    }




    @RequestMapping("/c1")
    public String c1() {
        /*JSONObject json = new JSONObject();
        json.put("name", "leon");
        json.put("group", "arch");*/
        String jsonParam = "{\"name\":\"leon\",\"group\":\"arch\"}";

        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        HttpEntity<String> formEntity = new HttpEntity<String>(jsonParam, headers);
        String s = restTemplate.postForEntity("http://" + SERVICE_NAME + "/t1", formEntity, String.class).getBody();
        return s;
    }

    @RequestMapping("/cf/{fn}")
    public String cf(@PathVariable("fn") String fn) {
        String jsonParam = "{\"name\":\"leon\",\"group\":\"arch\"}";

        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        HttpEntity<String> formEntity = new HttpEntity<String>(jsonParam, headers);
        String s = restTemplate.postForEntity("http://" + SERVICE_NAME + "/"+fn, formEntity, String.class).getBody();
        return s;
    }
}
