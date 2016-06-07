package ua.com.lavi.hystrixcollector.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Oleksandr Loushkin on 07.06.2016.
 */
@Component
@ConfigurationProperties(prefix="discoveryService")
public class DiscoveryServiceProperties {

    private List<String> exclude;

    public List<String> getExclude() {
        return exclude;
    }

    public void setExclude(List<String> exclude) {
        this.exclude = exclude;
    }
}
