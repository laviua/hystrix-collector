package ua.com.lavi.hystrixcollector.service;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rx.Subscription;
import rx.apache.http.ObservableHttp;
import ua.com.lavi.hystrixcollector.config.properties.HttpClientProperties;
import ua.com.lavi.hystrixcollector.model.hystrix.ServiceInstance;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Oleksandr Loushkin on 04.06.2016.
 */
@Service
public class HystrixSubscriptionServiceImpl implements HystrixSubscriptionService {

    private static final Logger log = LoggerFactory.getLogger(HystrixSubscriptionServiceImpl.class);

    private final InfluxDBStreamProcessor influxDBStreamProcessor;
    private final HttpClientProperties httpClientProperties;
    private Map<ServiceInstance, Subscription> subscriptions = new HashMap<>();

    @Autowired
    public HystrixSubscriptionServiceImpl(InfluxDBStreamProcessor influxDBStreamProcessor,
            HttpClientProperties httpClientProperties) {
        this.influxDBStreamProcessor = influxDBStreamProcessor;
        this.httpClientProperties = httpClientProperties;
    }

    public void doSubscribe(ServiceInstance serviceInstance) {
        String url = serviceInstance.getUrl();
        log.info("Subscribing {} to: {}", serviceInstance.getServiceId(), url);
        Subscription subscription = ObservableHttp.createGet(url, closeableHttpAsyncClient())
                .toObservable()
                .flatMap(response -> response.getContent().map(String::new))
                .filter(hystrixEvent -> hystrixEvent.startsWith("data: "))
                .map(data -> data.substring("data: ".length()))
                .doOnNext(data -> influxDBStreamProcessor.process(data, serviceInstance))
                .doOnError((e) -> handleError(e, serviceInstance))
                .retry(httpClientProperties.getRetry())
                .subscribe();
        log.info("Subscribed to: {}", url);
        subscriptions.put(serviceInstance, subscription);
    }

    private void handleError(Throwable e, ServiceInstance serviceInstance) {
        log.warn("Something went wrong: {}", e.getMessage(), e);
        doUnSubscribe(serviceInstance);
    }


    public void doUnSubscribe(ServiceInstance serviceInstance) {
        log.info("Unsubscribing from: {}", serviceInstance.getUri());
        subscriptions.get(serviceInstance).unsubscribe();
        subscriptions.remove(serviceInstance);
        log.info("Unsubscribed from: {}", serviceInstance.getUri());
    }

    private CloseableHttpAsyncClient closeableHttpAsyncClient() {

        final RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(httpClientProperties.getSocketTimeoutMs())
                .setConnectTimeout(httpClientProperties.getConnectTimeoutMs()).build();

        final CloseableHttpAsyncClient httpclient = HttpAsyncClients.custom()
                .setDefaultRequestConfig(requestConfig)
                .setMaxConnPerRoute(httpClientProperties.getMaxConnPerRoute())
                .setMaxConnTotal(httpClientProperties.getMaxConnTotal())
                .build();

        httpclient.start();
        return httpclient;
    }

}
