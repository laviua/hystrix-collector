package ua.com.lavi.hystrixcollector.config;

import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ua.com.lavi.hystrixcollector.config.properties.InfluxDBProperties;

/**
 * Created by Oleksandr Loushkin on 04.06.2016.
 */
@Configuration
public class HystrixCollectorConfig {

    @Autowired
    InfluxDBProperties influxDBProperties;

    @Bean
    public InfluxDB influxDB() {
        InfluxDB influxDB = InfluxDBFactory.connect(influxDBProperties.getUrl(), influxDBProperties.getUsername(),
                influxDBProperties.getPassword());
        String dbName = influxDBProperties.getDbName();
        influxDB.createDatabase(dbName);
        return influxDB;
    }

}
