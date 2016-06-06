package ua.com.lavi.hystrixcollector.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.consul.discovery.ConsulDiscoveryClient;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by Oleksandr Loushkin on 04.06.2016.
 */
@Service
public class DiscoveryService {

    private final static Logger log = LoggerFactory.getLogger(DiscoveryService.class);

    private final ConsulDiscoveryClient discoveryClient;
    private final HystrixSubscriptionService hystrixSubscriptionService;
    private List<ServiceInstance> registeredServiceInstances = new CopyOnWriteArrayList<>();

    @Autowired
    public DiscoveryService(ConsulDiscoveryClient discoveryClient,
            HystrixSubscriptionService hystrixSubscriptionService) {
        this.discoveryClient = discoveryClient;
        this.hystrixSubscriptionService = hystrixSubscriptionService;
    }

    @Scheduled(fixedDelayString = "${discoveryService.fixedDelayMs}")
    void discover() {
        List<ServiceInstance> discoveredServices = discoveryClient.getAllInstances();
        discoveredServices.stream()
                .filter(discoveredService -> !registeredServiceInstances.contains(discoveredService))
                .forEach(this::registerService);
        registeredServiceInstances.stream()
                .filter(registeredService -> !discoveredServices.contains(registeredService))
                .forEach(this::unregisterService);
    }

    private void registerService(ServiceInstance serviceInstance) {
        if (serviceInstance != null) {
            log.info("Registering: {}", serviceInstance.getServiceId());
            registeredServiceInstances.add(serviceInstance);
            hystrixSubscriptionService.subscribe(serviceInstance);
        }
    }

    private void unregisterService(ServiceInstance serviceInstance) {
        if (serviceInstance != null) {
            log.info("Unregistering: {}", serviceInstance.getServiceId());
            registeredServiceInstances.remove(serviceInstance);
            hystrixSubscriptionService.unSubscribe(serviceInstance);
        }
    }
}
