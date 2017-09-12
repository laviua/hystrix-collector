package ua.com.lavi.hystrixcollector.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.com.lavi.hystrixcollector.config.properties.HystrixProperties;
import ua.com.lavi.hystrixcollector.model.hystrix.ServiceInstance;

import java.util.List;

/**
 * Created by Oleksandr Loushkin on 12.09.17.
 */
@Component
public class HystrixInstanceRegistrar {

    private static final Logger log = LoggerFactory.getLogger(HystrixInstanceRegistrar.class);

    private final HystrixSubscriptionService subscriptionService;
    private final HystrixProperties hystrixProperties;

    @Autowired
    public HystrixInstanceRegistrar(HystrixSubscriptionService subscriptionService, HystrixProperties hystrixProperties) {
        this.subscriptionService = subscriptionService;
        this.hystrixProperties = hystrixProperties;
    }

    public void register() {
        List<ServiceInstance> subscriptions = hystrixProperties.getSubscriptions();
        log.info("Found {} subscriptions", subscriptions.size());
        subscriptions.forEach(subscriptionService::doSubscribe);
    }
}
