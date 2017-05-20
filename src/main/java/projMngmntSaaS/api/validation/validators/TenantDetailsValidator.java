package projMngmntSaaS.api.validation.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import projMngmntSaaS.api.validation.utils.InputValidation;
import projMngmntSaaS.domain.entities.TenantDetails;

/**
 * Validation steps for {@link TenantDetails} entity prior to its creation.
 * Checking for mandatory fields and fields semantic.
 */
@Component("beforeCreateTenantDetailsValidator")
public class TenantDetailsValidator implements Validator
{
    @Override
    public boolean supports(Class<?> clazz) {
        return TenantDetails.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        TenantDetails tenantDetails = (TenantDetails) target;

        // Validate presence of mandatory fields
        if (InputValidation.isEmpty(tenantDetails.getName())) {
            errors.rejectValue("name", "field.empty");
        }
        if (InputValidation.isEmpty(tenantDetails.getEmail())) {
            errors.rejectValue("email", "field.empty");
        }

        // Validate fields semantic
        if (!InputValidation.isValidEmail(tenantDetails.getEmail())) {
            errors.rejectValue("email", "field.invalid");
        }
        if (!InputValidation.isEmpty(tenantDetails.getWebsiteUrl()) && !InputValidation.isValidURL(tenantDetails.getWebsiteUrl())) {
            errors.rejectValue("websiteUrl", "field.invalid");
        }
    }
}
