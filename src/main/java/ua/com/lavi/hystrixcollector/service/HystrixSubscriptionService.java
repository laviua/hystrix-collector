package ua.com.lavi.hystrixcollector.service;

import org.apache.http.nio.client.HttpAsyncClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Service;
import rx.Subscription;
import rx.apache.http.ObservableHttp;
import ua.com.lavi.hystrixcollector.config.properties.HystrixProperties;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Oleksandr Loushkin on 04.06.2016.
 */
@Service
public class HystrixSubscriptionService {

    private static final Logger log = LoggerFactory.getLogger(HystrixSubscriptionService.class);

    private final HttpAsyncClient httpAsyncClient;
    private final HystrixProperties hystrixProperties;
    private final InfluxDBStreamProcessor influxDBStreamProcessor;
    private Map<ServiceInstance, Subscription> subscriptions = new HashMap<>();

    @Autowired
    public HystrixSubscriptionService(HttpAsyncClient httpAsyncClient, HystrixProperties hystrixProperties,
            InfluxDBStreamProcessor influxDBStreamProcessor) {
        this.httpAsyncClient = httpAsyncClient;
        this.hystrixProperties = hystrixProperties;
        this.influxDBStreamProcessor = influxDBStreamProcessor;
    }

    public void subscribe(ServiceInstance serviceInstance) {
        String url = serviceInstance.getUri().toString() + hystrixProperties.getSuffixUrl();
        log.info("Subscribing: {}", url);
        Subscription subscription = ObservableHttp.createGet(url, httpAsyncClient).toObservable().
                flatMap(response -> response.getContent().map(String::new)).
                filter(hystrixEvent -> hystrixEvent.startsWith("data: ")).
                map(data -> data.substring("data: ".length())).
                map(data -> influxDBStreamProcessor.process(data, serviceInstance)).
                subscribe();

        subscriptions.put(serviceInstance, subscription);
    }


    public void unSubscribe(ServiceInstance serviceInstance) {
        log.info("Unsubscribing: {}", serviceInstance.getUri());
        subscriptions.get(serviceInstance).unsubscribe();
    }
}
