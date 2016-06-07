package ua.com.lavi.hystrixcollector.model.hystrix;

import java.net.URI;
import java.util.Objects;

/**
 * Created by Oleksandr Loushkin on 07.06.2016.
 */
public class ServiceInstance {
    private final String serviceId;
    private final String host;
    private final Integer port;
    private final boolean isSecure;
    private boolean isAlive;

    public ServiceInstance(String serviceId, String host, Integer port, boolean isSecure, boolean isAlive) {
        this.serviceId = serviceId;
        this.host = host;
        this.port = port;
        this.isSecure = isSecure;
        this.isAlive = isAlive;
    }

    public String getServiceId() {
        return serviceId;
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public boolean isSecure() {
        return isSecure;
    }

    public URI getUri() {
        return getUri(this);
    }

    public boolean isAlive() {
        return isAlive;
    }

    private static URI getUri(ServiceInstance instance) {
        String scheme = instance.isSecure() ? "https" : "http";
        String uri = String.format("%s://%s:%s", scheme, instance.getHost(),
                instance.getPort());
        return URI.create(uri);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ServiceInstance that = (ServiceInstance) o;
        return isSecure == that.isSecure &&
                isAlive == that.isAlive &&
                Objects.equals(serviceId, that.serviceId) &&
                Objects.equals(host, that.host) &&
                Objects.equals(port, that.port);
    }

    @Override
    public int hashCode() {
        return Objects.hash(serviceId, host, port, isSecure, isAlive);
    }
}
