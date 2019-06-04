package com.dangdang.arch.consul.sp.registry;

import com.ecwid.consul.v1.ConsulClient;
import org.springframework.cloud.consul.discovery.ConsulDiscoveryProperties;
import org.springframework.cloud.consul.discovery.HeartbeatProperties;
import org.springframework.cloud.consul.discovery.TtlScheduler;
import org.springframework.cloud.consul.serviceregistry.ConsulRegistration;
import org.springframework.cloud.consul.serviceregistry.ConsulServiceRegistry;
import java.util.List;

public class MultiPathConsulServiceRegistry extends ConsulServiceRegistry {

    private boolean multiPathRegEnable;

    private List<String> clients;

    public MultiPathConsulServiceRegistry(List<String> clients, ConsulClient client, ConsulDiscoveryProperties properties, TtlScheduler ttlScheduler, HeartbeatProperties heartbeatProperties) {
        super(client, properties, ttlScheduler, heartbeatProperties);
        this.multiPathRegEnable = true;
        this.clients = clients;
    }

    @Override
    public void register(ConsulRegistration reg) {
        reg.getService().setId(reg.getService().getName() + "-" + reg.getService().getAddress() +"-"+ reg.getService().getPort());
        //开启多路注册
        if(multiPathRegEnable){
            for(String cc:clients){
                ConsulClient consulClient = new ConsulClient(cc,8500);
                //TODO set ACLTOKEN
                consulClient.agentServiceRegister(reg.getService(), null);
            }
        }
        super.register(reg);
    }
}