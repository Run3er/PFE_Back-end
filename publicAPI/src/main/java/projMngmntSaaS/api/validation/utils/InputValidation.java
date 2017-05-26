package projMngmntSaaS.api.validation.utils;

import org.hibernate.validator.internal.constraintvalidators.hv.EmailValidator;

import java.net.MalformedURLException;

/**
 * User input validation utilities.
 */
public abstract class InputValidation
{
    public static boolean isEmpty(String input) {
        return (input == null || input.trim().length() == 0);
    }

    public static boolean isValidEmail(String email) {
        // Basic Hibernate's Email validation
        return new EmailValidator().isValid(email, null);
    }

    public static boolean isValidURL(String url) {
        try {
            new java.net.URL(url);
        } catch (MalformedURLException e) {
            return false;
        }
        return true;
    }
}