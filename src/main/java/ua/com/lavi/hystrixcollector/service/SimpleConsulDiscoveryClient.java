package ua.com.lavi.hystrixcollector.service;

import com.ecwid.consul.v1.ConsulClient;
import com.ecwid.consul.v1.QueryParams;
import com.ecwid.consul.v1.Response;
import com.ecwid.consul.v1.health.model.Check;
import com.ecwid.consul.v1.health.model.HealthService;
import org.springframework.cloud.consul.discovery.ConsulDiscoveryProperties;
import ua.com.lavi.hystrixcollector.model.hystrix.ServiceInstance;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.cloud.consul.discovery.ConsulServerUtils.findHost;

/**
 * Created by Oleksandr Loushkin on 07.06.2016.
 */
public class SimpleConsulDiscoveryClient {

    private final ConsulClient consulClient;
    private final ConsulDiscoveryProperties consulDiscoveryProperties;

    public SimpleConsulDiscoveryClient(ConsulClient consulClient, ConsulDiscoveryProperties consulDiscoveryProperties) {
        this.consulClient = consulClient;
        this.consulDiscoveryProperties = consulDiscoveryProperties;
    }


    public List<ServiceInstance> getAllInstances() {
        Response<Map<String, List<String>>> services = consulClient.getCatalogServices(QueryParams.DEFAULT);
        List<ServiceInstance> instanceList = new ArrayList<>();

        for (String serviceId : services.getValue().keySet()) {
            Response<List<HealthService>> healthServices = consulClient.getHealthServices(serviceId,
                    consulDiscoveryProperties.isQueryPassing(), QueryParams.DEFAULT);

            instanceList.addAll(healthServices.getValue()
                    .stream()
                    .map(service -> new ServiceInstance(serviceId, findHost(service), service.getService().getPort(),
                            false, isAlive(service)))
                    .collect(Collectors.toList()));
        }

        return instanceList;
    }

    private boolean isAlive(HealthService service) {
        List<Check> checks = service.getChecks();
        for (Check check : checks) {
            if (check.getStatus() == Check.CheckStatus.CRITICAL) {
                return false;
            }
        }
        return true;
    }
}
