package ua.com.lavi.hystrixcollector.service;

import com.google.gson.Gson;
import org.influxdb.InfluxDB;
import org.influxdb.dto.Point;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.com.lavi.hystrixcollector.config.properties.InfluxDBProperties;
import ua.com.lavi.hystrixcollector.model.hystrix.HystrixData;
import ua.com.lavi.hystrixcollector.model.hystrix.HystrixServiceStream;

import java.util.concurrent.TimeUnit;

/**
 * Created by Oleksandr Loushkin on 05.06.2016.
 */
@Component
public class InfluxDBStreamProcessor {

    private final static Logger log = LoggerFactory.getLogger(InfluxDBStreamProcessor.class);

    public static final String TAG_SERVICE_ID = "serviceId";
    public static final String TAG_STREAM_URL = "streamUrl";

    private Gson gson = new Gson();

    private final InfluxDB influxDB;
    private final InfluxDBProperties influxDBProperties;

    @Autowired
    public InfluxDBStreamProcessor(InfluxDB influxDB, InfluxDBProperties influxDBProperties) {
        this.influxDB = influxDB;
        this.influxDBProperties = influxDBProperties;
    }

    public Void process(String data, HystrixServiceStream hystrixServiceStream) {
        HystrixData hystrixResponse = gson.fromJson(data, HystrixData.class);
        writeData(hystrixResponse, hystrixServiceStream);
        return null;
    }

    private void writeData(HystrixData hystrixData, HystrixServiceStream hystrixServiceStream) {
        try {
            Point point = Point.measurement(hystrixData.getName())
                    .time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
                    .tag(TAG_SERVICE_ID, hystrixServiceStream.getId())
                    .tag(TAG_STREAM_URL, hystrixServiceStream.getStreamUrl())
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
