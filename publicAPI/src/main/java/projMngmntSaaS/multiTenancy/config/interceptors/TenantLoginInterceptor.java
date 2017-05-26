package projMngmntSaaS.multiTenancy.config.interceptors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import projMngmntSaaS.multiTenancy.config.TenantContext;
import projMngmntSaaS.multiTenancy.domain.Tenant;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.LinkedHashMap;

/**
 * Ensures proper tenant setting before checking a tenant user login credentials in {@link projMngmntSaaS.multiTenancy.api.controllers.AuthenticationController}.
 * <br><br>
 * Note: Separate DB tenant & user fetching used to automatically create tenant-wise {@link projMngmntSaaS.repositories.UserRepository} by Spring Data within {@link projMngmntSaaS.multiTenancy.api.controllers.AuthenticationController}.
 */
@Component
public class TenantLoginInterceptor extends HandlerInterceptorAdapter
{
    private final EntityManagerFactory entityManagerFactory;

    @Autowired
    public TenantLoginInterceptor(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        @SuppressWarnings("unchecked")
        LinkedHashMap<String, String> pathVariables = (LinkedHashMap<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);

        // Spring Data REST native controller pathVariable (id) OR custom controller's (id) OR interceptor's (id)
        // TODO: make sure which one is selected, treat only that(ose) case(s)
        String tenantPseudo = pathVariables.get("id");

        // First, use global schema
        TenantContext.setCurrentTenant(TenantContext.TENANTS_GLOBAL);
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        // Find tenant by unique pseudo
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Tenant> q = cb.createQuery(Tenant.class);
        Root<Tenant> c = q.from(Tenant.class);
        q.select(c).where(cb.equal(c.get("pseudo"), tenantPseudo));
        Tenant tenant = entityManager.createQuery(q).getSingleResult();

        if (tenant == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
//            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
//            response.getWriter().write("{\"error\": \"Tenant not found\"}");
            response.getWriter().flush();
            return false;
        } else {
            TenantContext.setCurrentTenant(tenant.getDbSchemaName());
            return true;
        }
    }

    @Override
    public void postHandle(
            HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
            throws Exception {
        TenantContext.clear();
    }
}