package tenancyAdmin.controllers;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import tenancyAdmin.controllers.utils.LoginCredentials;
import tenancyAdmin.domain.User;
import tenancyAdmin.domain.utils.PasswordHashing;
import tenancyAdmin.repositories.UserRepository;

import java.util.Arrays;
import java.util.Collections;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * API controller for authenticating a tenant's user by granting him an access token.
 */
@RestController
public class AuthenticationController
{
    //  @Value("${tenancy.schema}") // TODO
    private static final String TENANT_GLOBAL = "tenants_global";
    //  @Value("${jwt.subject}") // TODO
    private static final String JWT_SUBJECT = "api_access_token";
    //  @Value("${jwt.secret}") // TODO
    private static String JWT_SECRET = "43d44eae-cd10-4ffb-97e1-c3e189119659";
    private static int JWT_DEFAULT_EXPIRATION_MINUTES = 30;

    private final UserRepository userRepository;

    @Autowired
    public AuthenticationController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @ResponseBody
    @RequestMapping(method = POST, consumes = "application/json", value = "/login")
    public ResponseEntity<?> login(@RequestBody LoginCredentials loginCredentials) {
        // Find user
        User user = userRepository.findByLogin(loginCredentials.getUser());
        if (user == null) {
            return new ResponseEntity<>(Collections.singletonMap("error", "LOGIN INVALID"), HttpStatus.UNAUTHORIZED);
        }

        // Verify password
        byte[] submittedPasswordHash = PasswordHashing.hash(loginCredentials.getPassword(), user.getPasswordSalt());
        if (!Arrays.equals(user.getPasswordHash(), submittedPasswordHash)) {
            return new ResponseEntity<>(Collections.singletonMap("error", "PASSWORD INVALID"), HttpStatus.UNAUTHORIZED);
        }

        // Generate JWT
        Claims claims = Jwts.claims().setSubject(JWT_SUBJECT);
        claims.put("tenantId", TENANT_GLOBAL);
        claims.put("userId", user.getId() + "");

        String jwt = Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, JWT_SECRET)
                .compact();

        // Respond with JWT
        return new ResponseEntity<>(Collections.singletonMap("jwt", jwt), HttpStatus.OK);
    }
}
