package ua.com.lavi.hystrixcollector.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by Oleksandr Loushkin on 05.06.2016.
 */
@Component
@ConfigurationProperties(prefix="discoveryService")
public class DiscoveryServiceProperties {

    private String prefixUrl;
    private String suffixUrl;

    public String getPrefixUrl() {
        return prefixUrl;
    }

    public void setPrefixUrl(String prefixUrl) {
        this.prefixUrl = prefixUrl;
    }

    public String getSuffixUrl() {
        return suffixUrl;
    }

    public void setSuffixUrl(String suffixUrl) {
        this.suffixUrl = suffixUrl;
    }
}
