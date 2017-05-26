package tenancyAdmin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import projMngmntSaaS.multiTenancy.domain.Tenant;
import tenancyAdmin.domain.User;

/**
 * Configuration for Spring Boot.
 * It allows the application to be stand-alone (embedded TomCat server, ...).
 */
@SpringBootApplication
@EntityScan(basePackageClasses = { User.class, Tenant.class })
public class AppConfiguration
{
    public static void main(String[] args) {
        SpringApplication.run(AppConfiguration.class, args);
    }

    /**
     * API CORS configuration
     * TODO: should only allow same domain requests (different ports allowed)
     */
    @Bean
    public FilterRegistrationBean corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        config.addExposedHeader("Authorization");
        source.registerCorsConfiguration("/**", config);
        FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
        bean.setOrder(0);
        return bean;
    }
}
