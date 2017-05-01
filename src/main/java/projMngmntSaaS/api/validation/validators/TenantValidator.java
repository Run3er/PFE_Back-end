package projMngmntSaaS.api.validation.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import projMngmntSaaS.api.validation.utils.InputValidation;
import projMngmntSaaS.domain.entities.Tenant;

/**
 * Validation steps for {@link Tenant} entity prior to its creation.
 * Checking for mandatory fields and fields semantic.
 */
@Component("beforeCreateTenantValidator")
public class TenantValidator implements Validator
{
    @Override
    public boolean supports(Class<?> clazz) {
        return Tenant.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Tenant tenant = (Tenant) target;

        // Validate presence of mandatory fields
        if (InputValidation.isEmpty(tenant.getName())) {
            errors.rejectValue("name", "field.empty");
        }
        if (InputValidation.isEmpty(tenant.getEmail())) {
            errors.rejectValue("email", "field.empty");
        }

        // Validate fields semantic
        if (!InputValidation.isValidEmail(tenant.getEmail())) {
            errors.rejectValue("email", "field.invalid");
        }
        if (!InputValidation.isEmpty(tenant.getWebsiteUrl()) && !InputValidation.isValidURL(tenant.getWebsiteUrl())) {
            errors.rejectValue("websiteUrl", "field.invalid");
        }
    }
}
