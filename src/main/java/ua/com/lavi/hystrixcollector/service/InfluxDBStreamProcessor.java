package ua.com.lavi.hystrixcollector.service;

import com.google.gson.Gson;
import org.influxdb.InfluxDB;
import org.influxdb.dto.Point;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;
import ua.com.lavi.hystrixcollector.config.properties.InfluxDBProperties;
import ua.com.lavi.hystrixcollector.model.hystrix.HystrixData;

import java.util.concurrent.TimeUnit;

/**
 * Created by Oleksandr Loushkin on 05.06.2016.
 */
@Component
public class InfluxDBStreamProcessor {

    private final static Logger log = LoggerFactory.getLogger(InfluxDBStreamProcessor.class);

    private static final String TAG_SERVICE_ID = "serviceId";
    private static final String TAG_SERVICE_URL = "serviceUrl";

    private Gson gson = new Gson();

    private final InfluxDB influxDB;
    private final InfluxDBProperties influxDBProperties;

    @Autowired
    public InfluxDBStreamProcessor(InfluxDB influxDB, InfluxDBProperties influxDBProperties) {
        this.influxDB = influxDB;
        this.influxDBProperties = influxDBProperties;
    }


    public Void process(String data, ServiceInstance serviceInstance) {
        HystrixData hystrixResponse = gson.fromJson(data, HystrixData.class);
        writeData(hystrixResponse, serviceInstance);
        return null;
    }

    private void writeData(HystrixData hystrixData, ServiceInstance serviceInstance) {
        try {
            Point point = Point.measurement(hystrixData.getName())
                    .time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
                    .tag(TAG_SERVICE_ID, serviceInstance.getServiceId())
                    .tag(TAG_SERVICE_URL, serviceInstance.getUri().toString())
                    .addField("isCircuitBreakerOpen", hystrixData.isCircuitBreakerOpen)
                    .addField("requestCount", hystrixData.getRequestCount())
                    .addField("errorPercentage", hystrixData.getErrorPercentage())
                    .addField("errorCount", hystrixData.getErrorCount())
                    .build();

            influxDB.write(influxDBProperties.getDbName(), "default", point);
        } catch (Exception e) {
            log.error("Error while writing to db: ", e);
        }

    }
}
