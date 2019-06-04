# consul-sc
The service consumer with consul

## common model
See ServiceConsumerController
> Notic: It is not necessary to use the annotation '@EnableDiscoveryClient' in ConsulScApplication

## feign model
See FeignController & FeignService
> Notic: The Annotations '@EnableFeignClients' must be declared in ConsulScApplication

## loadbalance
Use ribbon

### LoadBalancerClient
See ServiceConsumerController

```java
@Autowired
private LoadBalancerClient loadBalancer;
```

### RestTemplate
See ConsulConf

```java
@LoadBalanced
@Bean
public RestTemplate loadbalancedRestTemplate() {
    return new RestTemplate();
}
```