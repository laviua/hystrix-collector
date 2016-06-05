package ua.com.lavi.hystrixcollector.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Oleksandr Loushkin on 04.06.2016.
 */
@Service
@ConditionalOnProperty(prefix = "staticDiscoveryClient", value = "services")
public class StaticDiscoveryClient implements DiscoveryClient {

    @Value("${staticDiscoveryClient.services}")
    private String[] services;

    @Override
    public String description() {
        return "Static data from configuration";
    }

    @Override
    public ServiceInstance getLocalServiceInstance() {
        return null;
    }

    @Override
    public List<ServiceInstance> getInstances(String serviceId) {
        return Collections.emptyList();
    }

    @Override
    public List<String> getServices() {
        return Arrays.stream(services).collect(Collectors.toList());
    }
}
