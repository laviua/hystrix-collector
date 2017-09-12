package ua.com.lavi.hystrixcollector;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.EmbeddedServletContainerAutoConfiguration;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import ua.com.lavi.hystrixcollector.service.HystrixInstanceRegistrar;

@SuppressWarnings("PMD.UseUtilityClass")
@SpringBootApplication(exclude = {
        EmbeddedServletContainerAutoConfiguration.class,
        WebMvcAutoConfiguration.class})
public class HystrixCollectorApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(HystrixCollectorApplication.class, args);
        applicationContext.getBean(HystrixInstanceRegistrar.class).register();
    }
}
