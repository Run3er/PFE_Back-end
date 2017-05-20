package projMngmntSaaS.multiTenancy.config.interceptors;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.handler.MappedInterceptor;

/**
 * Used to pre-process requests to ensure Multi-Tenancy (setting the appropriate tenant) when controllers are executed.
 * <br><br>
 * Note: Used instead of {@link RepositoryRestMvcConfiguration#addInterceptors(InterceptorRegistry)} because the
 * registered interceptor has to be called before Spring Data REST native controllers repository instances get injected
 * (which was not the case with the former).
 */
@Configuration
public class RepositoryRestMvcConfig extends RepositoryRestMvcConfiguration
{
    private final TenantHeaderInterceptor tenantHeaderInterceptor;
    private final TenantLoginInterceptor tenantLoginInterceptor;

    @Autowired
    public RepositoryRestMvcConfig(TenantHeaderInterceptor tenantHeaderInterceptor, TenantLoginInterceptor tenantLoginInterceptor) {
        this.tenantHeaderInterceptor = tenantHeaderInterceptor;
        this.tenantLoginInterceptor = tenantLoginInterceptor;
    }

    /**
     * Registers {@link TenantHeaderInterceptor}.
     */
    @Bean
    public MappedInterceptor tenantHeaderAware() {
        return new MappedInterceptor(new String[]{"/**"}, new String[]{"/tenants/{id}/login"}, tenantHeaderInterceptor);
    }

    /**
     * Registers {@link TenantLoginInterceptor}.
     */
    @Bean
    public MappedInterceptor tenantRequestBodyAware() {
        return new MappedInterceptor(new String[]{"/tenants/{id}/login"}, tenantLoginInterceptor);
    }

    /**
     * Hack to avoid APPLICATION FAILED TO START error, w/ description :<br>
     * <p>
     * Constructor in org.springframework.boot.actuate.autoconfigure.EndpointMBeanExportAutoConfiguration required a single bean, but 3 were found:<br>
     * - objectMapper: defined by method 'objectMapper' in class path resource [&lt;package_path&gt;/{@link RepositoryRestMvcConfig}.class]<br>
     * - halObjectMapper: defined by method 'halObjectMapper' in class path resource [&lt;package_path&gt;/{@link RepositoryRestMvcConfig}.class]<br>
     * - _halObjectMapper: defined in null<br>
     * <br>
     * <i>source</i> : <a href="https://github.com/spring-projects/spring-boot/issues/6529#issuecomment-237626677">Github issue</a>
     */
    @Primary
    @Bean
    @Override
    public ObjectMapper objectMapper() {
        return super.objectMapper();
    }
}