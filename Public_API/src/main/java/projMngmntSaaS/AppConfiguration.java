package projMngmntSaaS;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import projMngmntSaaS.domain.entities.Tenant;

/**
 * Configuration for Spring Boot.
 * It allows the application to be stand-alone (embedded TomCat server, ...).
 */
@SpringBootApplication
@EntityScan(basePackageClasses = Tenant.class)
public class AppConfiguration
{
    public static void main(String[] args) {
        SpringApplication.run(AppConfiguration.class, args);
    }
}
