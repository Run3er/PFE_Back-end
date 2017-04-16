package projMngmntSaaS;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Configuration for Spring Boot.
 * It allows the application to be stand-alone (embedded TomCat server, ...).
 */
@SpringBootApplication
public class ApiConfiguration
{
    public static void main(String[] args) {
        SpringApplication.run(ApiConfiguration.class, args);
    }
}
