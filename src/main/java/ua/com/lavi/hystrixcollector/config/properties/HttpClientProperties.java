package ua.com.lavi.hystrixcollector.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by Oleksandr Loushkin on 07.06.2016.
 */
@Component
@ConfigurationProperties(prefix="httpClient")
public class HttpClientProperties {

    private Integer retry;
    private Integer socketTimeoutMs;
    private Integer connectTimeoutMs;
    private Integer maxConnPerRoute;
    private Integer maxConnTotal;

    public Integer getRetry() {
        return retry;
    }

    public void setRetry(Integer retry) {
        this.retry = retry;
    }

    public Integer getSocketTimeoutMs() {
        return socketTimeoutMs;
    }

    public void setSocketTimeoutMs(Integer socketTimeoutMs) {
        this.socketTimeoutMs = socketTimeoutMs;
    }

    public Integer getConnectTimeoutMs() {
        return connectTimeoutMs;
    }

    public void setConnectTimeoutMs(Integer connectTimeoutMs) {
        this.connectTimeoutMs = connectTimeoutMs;
    }

    public Integer getMaxConnPerRoute() {
        return maxConnPerRoute;
    }

    public void setMaxConnPerRoute(Integer maxConnPerRoute) {
        this.maxConnPerRoute = maxConnPerRoute;
    }

    public Integer getMaxConnTotal() {
        return maxConnTotal;
    }

    public void setMaxConnTotal(Integer maxConnTotal) {
        this.maxConnTotal = maxConnTotal;
    }
}
