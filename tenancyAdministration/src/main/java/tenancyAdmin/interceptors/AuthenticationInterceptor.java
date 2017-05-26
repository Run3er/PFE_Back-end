package tenancyAdmin.interceptors;

import io.jsonwebtoken.*;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Checks requests authentication.
 */
@Component
public class AuthenticationInterceptor extends HandlerInterceptorAdapter
{
    //  @Value("${tenancy.schema}") // TODO
    private static final String TENANT_GLOBAL = "tenants_global";
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

        boolean accessAllowed = TENANT_GLOBAL.equals(claimsJws.getBody().get("tenantId", String.class));
        if (!accessAllowed) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.getWriter().flush();
            return false;
        }

        return true;
    }
}