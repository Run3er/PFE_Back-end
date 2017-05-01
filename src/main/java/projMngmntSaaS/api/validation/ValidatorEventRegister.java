package projMngmntSaaS.api.validation;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.event.ValidatingRepositoryEventListener;
import org.springframework.validation.Validator;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Manual validators events registering workaround for
 * <a href="http://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/validation/Errors.html">
 * events registering issue</a>.
 * <br>
 * <i>source: <a href="http://www.baeldung.com/spring-data-rest-validators">www.baeldung.com/spring-data-rest-validators</a></i>
 */
@Configuration
public class ValidatorEventRegister implements InitializingBean
{
    @Autowired
    private ValidatingRepositoryEventListener validatingRepositoryEventListener;

    @Autowired
    private Map<String, Validator> validators;

    @Override
    public void afterPropertiesSet() throws Exception {
        List<String> events = Arrays.asList("beforeCreate");
        for (Map.Entry<String, Validator> entry : validators.entrySet()) {
            events.stream()
                    .filter(p -> entry.getKey().startsWith(p))
                    .findFirst()
                    .ifPresent(
                            p -> validatingRepositoryEventListener
                                    .addValidator(p, entry.getValue()));
        }
    }
}