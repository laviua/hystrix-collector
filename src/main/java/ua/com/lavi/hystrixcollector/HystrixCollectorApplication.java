package ua.com.lavi.hystrixcollector;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.EmbeddedServletContainerAutoConfiguration;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;

@SuppressWarnings("PMD.UseUtilityClass")
@SpringBootApplication(exclude = {EmbeddedServletContainerAutoConfiguration.class,
        WebMvcAutoConfiguration.class})
@EnableScheduling
public class HystrixCollectorApplication {

    public static void main(String[] args) {
        SpringApplication.run(HystrixCollectorApplication.class, args);
    }
}
