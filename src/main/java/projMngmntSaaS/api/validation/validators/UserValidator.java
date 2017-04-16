package projMngmntSaaS.api.validation.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.AbstractBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.Validator;
import projMngmntSaaS.api.validation.utils.InputValidation;
import projMngmntSaaS.domain.entities.User;
import projMngmntSaaS.domain.utils.PasswordHashing;

/**
 * Validation steps for {@link User} entity prior to its creation.
 * Checking for mandatory fields and fields semantic.
 */
@Component("beforeCreateUserValidator")
public class UserValidator implements Validator
{
    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;

        // Validate presence of mandatory fields
        if (InputValidation.isEmpty(user.getFirstName())) {
            errors.rejectValue("firstName", "field.empty");
        }
        if (InputValidation.isEmpty(user.getLastName())) {
            errors.rejectValue("lastName", "field.empty");
        }
        if (InputValidation.isEmpty(user.getLogin())) {
            errors.rejectValue("login", "field.empty");
        }

        // Validate fields semantic
        if (user.getPasswordHash() == null) {
            rejectHiddenField("password", "field.empty", null, errors);
        }
        if (PasswordHashing.isHashOfPassword(user.getPasswordHash(), user.getPasswordSalt(), "")) {
            rejectHiddenField("password", "field.empty", "", errors);
        }
    }

    /**
     * Custom reimplementation of {@link Errors#rejectValue(String, String)} for avoiding actual field fetching, causing a
     * {@link org.springframework.beans.NotReadablePropertyException} in its absence, set up in the original implementation.
     *
     * @param hiddenField the field name (may be {@code null} or empty String)
     * @param errorCode error code, interpretable as a message key
     * @param rejectedValue the rejected field value
     * @param errors original alternative {@link Errors#rejectValue(String, String)} method's {@link Errors} class. Contextual state about the validation process (never {@code null})
     *
     * @see Errors#rejectValue(String, String)
     * @see FieldError#FieldError(String, String, Object, boolean, String[], Object[], String)
     */
    private void rejectHiddenField(String hiddenField, String errorCode, Object rejectedValue, Errors errors) {
        AbstractBindingResult abstractBindingResult = (AbstractBindingResult) errors;
        String[] codes = abstractBindingResult.resolveMessageCodes(errorCode, hiddenField);

        FieldError fe = new FieldError(errors.getObjectName(), hiddenField, rejectedValue, false, codes, null, null);

        abstractBindingResult.addError(fe);
    }
}
