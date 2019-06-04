package com.dangdang.arch.consul.sp.conf;

import com.dangdang.arch.consul.sp.registry.MultiPathConsulServiceRegistry;
import com.ecwid.consul.v1.ConsulClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.consul.discovery.ConsulDiscoveryProperties;
import org.springframework.cloud.consul.discovery.HeartbeatProperties;
import org.springframework.cloud.consul.discovery.TtlScheduler;
import org.springframework.cloud.consul.serviceregistry.ConsulServiceRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Configuration
public class MultiPathConsulRegConf {

    @Value("${ddcloud.registry.multipath.clients}")
    private String clientStr;

    @Value("#{'${ddcloud.registry.multipath.clients}'.split(',')}")
    private List<String> clients;

    @Autowired(required = false)
    private TtlScheduler ttlScheduler;

    @Bean
    @Primary
    public ConsulServiceRegistry consulServiceRegistry(ConsulClient consulClient, ConsulDiscoveryProperties properties, HeartbeatProperties heartbeatProperties) {
        return new MultiPathConsulServiceRegistry(clients,consulClient, properties, ttlScheduler, heartbeatProperties);
    }
}
