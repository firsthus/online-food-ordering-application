package platformdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class PlatformApplication {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(PlatformApplication.class, args);
    }
}

