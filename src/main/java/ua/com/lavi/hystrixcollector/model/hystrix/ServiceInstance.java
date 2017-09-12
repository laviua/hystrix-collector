package ua.com.lavi.hystrixcollector.model.hystrix;

import java.net.URI;

/**
 * Created by Oleksandr Loushkin on 07.06.2016.
 */
public class ServiceInstance {
    private  String serviceId;
    private  String url;

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public URI getUri() {
        return URI.create(url);
    }
}
