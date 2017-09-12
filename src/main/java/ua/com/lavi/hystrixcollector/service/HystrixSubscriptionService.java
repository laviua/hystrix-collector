package ua.com.lavi.hystrixcollector.service;

import ua.com.lavi.hystrixcollector.model.hystrix.ServiceInstance;

/**
 * Created by Oleksandr Loushkin on 12.09.17.
 */
public interface HystrixSubscriptionService {
    /**
     * Subscribe to hystrix stream
     * @param serviceInstance
     */
    void doSubscribe(ServiceInstance serviceInstance);

    /**
     * Unsubsribe from hystrix stream
     * @param serviceInstance
     */
    void doUnSubscribe(ServiceInstance serviceInstance);
}
