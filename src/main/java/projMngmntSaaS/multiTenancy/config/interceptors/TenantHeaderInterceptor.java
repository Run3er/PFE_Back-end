package projMngmntSaaS.multiTenancy.config.interceptors;

import io.jsonwebtoken.*;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import projMngmntSaaS.multiTenancy.config.TenantContext;
import projMngmntSaaS.multiTenancy.config.TenantUserContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * Ensures proper tenant setting before executing requests.
 */
@Component
public class TenantHeaderInterceptor extends HandlerInterceptorAdapter
{
    private static final String AUTH_HEADER = "Authorization";
    private static final String BEARER_SCHEME_PREFIX = "Bearer ";
    //  @Value("${jwt.subject}") // TODO
    private static final String JWT_SUBJECT = "api_access_token";
    //  @Value("${jwt.secret}") // TODO
    private static String JWT_SECRET = "43d44eae-cd10-4ffb-97e1-c3e189119659";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
            String header = request.getHeader(AUTH_HEADER);

        if (header == null || !header.startsWith(BEARER_SCHEME_PREFIX)) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.getWriter().write("{\"error\": \"No JWT token found in the Authorization header with the Bearer authentication scheme\"}");
            response.getWriter().flush();
            return false;
        }
        String jwtToken = header.substring(BEARER_SCHEME_PREFIX.length());

        Jws<Claims> claimsJws;
        try {
            claimsJws = Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(jwtToken);
        } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | IllegalArgumentException | SignatureException e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.getWriter().write("{\"error\": \"Invalid JWT token\"}");
            response.getWriter().flush();
            return false;
        }

        UUID userId = UUID.fromString(claimsJws.getBody().get("userId", String.class));
        TenantUserContext.setCurrentUser(userId);

        // We trust that the tenant is always present in the signed token
        String tenantId = claimsJws.getBody().get("tenantId", String.class);
        TenantContext.setCurrentTenant(tenantId);
        return true;
    }

    @Override
    public void postHandle(
            HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
            throws Exception {
        TenantContext.clear();
        TenantUserContext.clear();
    }
}