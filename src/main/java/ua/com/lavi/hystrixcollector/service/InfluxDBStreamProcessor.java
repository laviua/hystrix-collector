package ua.com.lavi.hystrixcollector.service;

import com.google.gson.Gson;
import org.influxdb.InfluxDB;
import org.influxdb.dto.Point;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.com.lavi.hystrixcollector.config.properties.InfluxDBProperties;
import ua.com.lavi.hystrixcollector.model.hystrix.*;

import java.util.concurrent.TimeUnit;

/**
 * Created by Oleksandr Loushkin on 05.06.2016.
 */
@Component
public class InfluxDBStreamProcessor {

    private final static Logger log = LoggerFactory.getLogger(InfluxDBStreamProcessor.class);

    private static final String TAG_SERVICE_ID = "serviceId";
    private static final String TAG_SERVICE_URL = "serviceUrl";
    private static final String TAG_GROUP = "group";
    private static final String TAG_NAME = "name";

    private Gson gson = new Gson();

    private final InfluxDB influxDB;
    private final InfluxDBProperties influxDBProperties;

    @Autowired
    public InfluxDBStreamProcessor(InfluxDB influxDB, InfluxDBProperties influxDBProperties) {
        this.influxDB = influxDB;
        this.influxDBProperties = influxDBProperties;
    }


    void process(String data, ServiceInstance serviceInstance) {
        StreamType streamType = gson.fromJson(data, StreamType.class);
        if (StreamType.Type.HystrixThreadPool == streamType.getType()) {
            HystrixThreadPoolData hystrixThreadPoolData = gson.fromJson(data, HystrixThreadPoolData.class);
            writeThreadPoolData(hystrixThreadPoolData, serviceInstance);
        }
        if (StreamType.Type.HystrixCommand == streamType.getType()) {
            HystrixCommandData hystrixCommandData = gson.fromJson(data, HystrixCommandData.class);
            writeHystrixCommandData(hystrixCommandData, serviceInstance);
        }

        if (log.isDebugEnabled()){
            log.debug(data);
        }

    }

    private void writeHystrixCommandData(HystrixCommandData hystrixCommandData, ServiceInstance serviceInstance) {
        Point point = Point.measurement(hystrixCommandData.getName())
                .time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
                .tag(TAG_SERVICE_ID, serviceInstance.getServiceId())
                .tag(TAG_SERVICE_URL, serviceInstance.getUri().toString())
                .tag(TAG_GROUP, hystrixCommandData.getGroup())
                .tag(TAG_NAME, hystrixCommandData.getName())
                .addField("isCircuitBreakerOpen", hystrixCommandData.isCircuitBreakerOpen())
                .addField("errorPercentage", hystrixCommandData.getErrorPercentage())
                .addField("errorCount", hystrixCommandData.getErrorCount())
                .addField("requestCount", hystrixCommandData.getRequestCount())
                .build();

        writeData(point);

    }

    private void writeThreadPoolData(HystrixThreadPoolData hystrixThreadPoolData, ServiceInstance serviceInstance) {
        Point point = Point.measurement(hystrixThreadPoolData.getName())
                .time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
                .tag(TAG_SERVICE_ID, serviceInstance.getServiceId())
                .tag(TAG_SERVICE_URL, serviceInstance.getUri().toString())
                .tag(TAG_NAME, hystrixThreadPoolData.getName())
                .addField("currentActiveCount", hystrixThreadPoolData.getCurrentActiveCount())
                .addField("currentCompletedTaskCount", hystrixThreadPoolData.getCurrentCompletedTaskCount())
                .addField("currentCorePoolSize", hystrixThreadPoolData.getCurrentCorePoolSize())
                .addField("currentLargestPoolSize", hystrixThreadPoolData.getCurrentLargestPoolSize())
                .addField("currentMaximumPoolSize", hystrixThreadPoolData.getCurrentMaximumPoolSize())
                .addField("currentPoolSize", hystrixThreadPoolData.getCurrentPoolSize())
                .addField("currentQueueSize", hystrixThreadPoolData.getCurrentQueueSize())
                .addField("currentTaskCount", hystrixThreadPoolData.getCurrentTaskCount())
                .build();

        writeData(point);

    }

    private void writeData(Point point) {
        try {

            influxDB.write(influxDBProperties.getDbName(), "default", point);
        } catch (Exception e) {
            log.error("Error while writing to db: ", e);
        }

    }
}
