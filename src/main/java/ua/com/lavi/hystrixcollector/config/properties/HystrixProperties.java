package ua.com.lavi.hystrixcollector.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import ua.com.lavi.hystrixcollector.model.hystrix.ServiceInstance;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Oleksandr Loushkin on 05.06.2016.
 */
@Component
@ConfigurationProperties(prefix="hystrix")
public class HystrixProperties {

    private List<ServiceInstance> subscriptions = new ArrayList<>();

    public List<ServiceInstance> getSubscriptions() {
        return subscriptions;
    }

    public void setSubscriptions(List<ServiceInstance> subscriptions) {
        this.subscriptions = subscriptions;
    }
}
