package ua.com.lavi.hystrixcollector.service;

import org.apache.http.nio.client.HttpAsyncClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rx.Subscription;
import rx.apache.http.ObservableHttp;
import ua.com.lavi.hystrixcollector.model.hystrix.HystrixServiceStream;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Oleksandr Loushkin on 04.06.2016.
 */
@Service
public class StreamSubscriptionService {

    private static final Logger log = LoggerFactory.getLogger(StreamSubscriptionService.class);

    private final HttpAsyncClient httpAsyncClient;
    private final InfluxDBStreamProcessor influxDBStreamProcessor;
    private Map<String, Subscription> subscriptions = new HashMap<>();

    @Autowired
    public StreamSubscriptionService(HttpAsyncClient httpAsyncClient, InfluxDBStreamProcessor influxDBStreamProcessor) {
        this.httpAsyncClient = httpAsyncClient;
        this.influxDBStreamProcessor = influxDBStreamProcessor;
    }

    public void subscribe(HystrixServiceStream hystrixServiceStream) {
        log.info("Subscribing: {}", hystrixServiceStream.getStreamUrl());
        Subscription subscription = ObservableHttp.createGet(hystrixServiceStream.getStreamUrl(), httpAsyncClient).toObservable().
                flatMap(response -> response.getContent().map(String::new)).
                filter(hystrixEvent -> hystrixEvent.startsWith("data: ")).
                map(data -> data.substring("data: ".length())).
                map(data -> influxDBStreamProcessor.process(data, hystrixServiceStream)).
                subscribe();

        subscriptions.put(hystrixServiceStream.getId(), subscription);
    }


    public void unSubscribe(String service) {
        log.info("Unsubscribing: {}", service);
        subscriptions.get(service).unsubscribe();
    }
}
