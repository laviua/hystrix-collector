package ua.com.lavi.hystrixcollector.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ua.com.lavi.hystrixcollector.config.properties.DiscoveryServiceProperties;
import ua.com.lavi.hystrixcollector.model.hystrix.ServiceInstance;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by Oleksandr Loushkin on 04.06.2016.
 */
@Service
public class DiscoveryService {

    private final static Logger log = LoggerFactory.getLogger(DiscoveryService.class);

    private final SimpleConsulDiscoveryClient discoveryClient;
    private final DiscoveryServiceProperties discoveryServiceProperties;
    private final HystrixSubscriptionService hystrixSubscriptionService;
    private List<ServiceInstance> registeredServiceInstances = new CopyOnWriteArrayList<>();

    @Autowired
    public DiscoveryService(SimpleConsulDiscoveryClient discoveryClient,
            DiscoveryServiceProperties discoveryServiceProperties,
            HystrixSubscriptionService hystrixSubscriptionService) {
        this.discoveryClient = discoveryClient;
        this.discoveryServiceProperties = discoveryServiceProperties;
        this.hystrixSubscriptionService = hystrixSubscriptionService;
    }

    @Scheduled(fixedDelayString = "${discoveryService.fixedDelayMs}")
    void discover() {
        List<ServiceInstance> discoveredInstances = discoveryClient.getAllInstances();

        discoveredInstances.stream()
                .filter(discoveredInstance -> discoveredInstance.isAlive() == discoveryServiceProperties.isOnlyAlive())
                .filter(discoveredService -> !discoveryServiceProperties.getExclude()
                        .contains(discoveredService.getServiceId()))
                .filter(discoveredService -> !registeredServiceInstances.contains(discoveredService))
                .forEach(this::registerService);

        registeredServiceInstances.stream()
                .filter(registeredService -> !discoveredInstances.contains(registeredService))
                .forEach(this::unregisterService);
    }

    private void registerService(ServiceInstance serviceInstance) {
        if (serviceInstance != null) {
            log.info("Registering: {}", serviceInstance.getServiceId());
            registeredServiceInstances.add(serviceInstance);
            hystrixSubscriptionService.doSubscribe(serviceInstance);
        }
    }

    private void unregisterService(ServiceInstance serviceInstance) {
        if (serviceInstance != null) {
            log.info("Unregistering: {}", serviceInstance.getServiceId());
            registeredServiceInstances.remove(serviceInstance);
            hystrixSubscriptionService.doUnSubscribe(serviceInstance);
        }
    }
}
