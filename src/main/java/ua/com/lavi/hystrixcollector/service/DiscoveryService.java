package ua.com.lavi.hystrixcollector.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ua.com.lavi.hystrixcollector.config.properties.DiscoveryServiceProperties;
import ua.com.lavi.hystrixcollector.model.hystrix.HystrixServiceStream;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by Oleksandr Loushkin on 04.06.2016.
 */
@Service
public class DiscoveryService {

    private final static Logger log = LoggerFactory.getLogger(DiscoveryService.class);

    private final DiscoveryClient discoveryClient;
    private final StreamSubscriptionService streamSubscriptionService;
    private final DiscoveryServiceProperties discoveryServiceProperties;
    private List<String> registeredServices = new CopyOnWriteArrayList<>();

    @Autowired
    public DiscoveryService(DiscoveryClient discoveryClient, StreamSubscriptionService streamSubscriptionService, DiscoveryServiceProperties discoveryServiceProperties) {
        this.discoveryClient = discoveryClient;
        this.streamSubscriptionService = streamSubscriptionService;
        this.discoveryServiceProperties = discoveryServiceProperties;
    }

    @Scheduled(fixedDelayString = "${discoveryService.fixedDelayMs}")
    void discover() {
        List<String> discoveredServices = discoveryClient.getServices();
        discoveredServices.stream().filter(discoveredService -> !registeredServices.contains(discoveredService)).forEach(this::registerService);
        registeredServices.stream().filter(registeredService -> !discoveredServices.contains(registeredService)).forEach(this::unregisterService);
    }

    private void registerService(String service) {
        if (service != null) {
            log.info("Registering: {}", service);
            registeredServices.add(service);
            streamSubscriptionService.subscribe(new HystrixServiceStream(service, buildStreamUrl(service)));
        }
    }

    private void unregisterService(String service) {
        if (service != null) {
            log.info("Unregistering: {}", service);
            registeredServices.remove(service);
            streamSubscriptionService.unSubscribe(service);
        }
    }

    private String buildStreamUrl(String service) {
        return discoveryServiceProperties.getPrefixUrl() + service + discoveryServiceProperties.getSuffixUrl();
    }
}
