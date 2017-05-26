package projMngmntSaaS.domain.entities.annotations.jackson;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.junit.BeforeClass;
import org.junit.Test;
import projMngmntSaaS.domain.entities.User;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import static projMngmntSaaS.domain.entities.annotations.testUtils.AssertAnnotations.assertAnnotation;

/**
 * Validating specific Jackson annotations in {@link User}.
 */
public class UserTest
{
    private static final Class<User> CLASS = User.class;
    private static Field passwordHash;
    private static Field passwordSalt;

    // Apart from initialization, this also checks for annotated elements name conformity to the used literals,
    // by throwing NoSuchFieldException hint, for annotated elements with JPA DB default name setting.
    @BeforeClass
    public static void getClassContents() throws NoSuchFieldException {
        // Retrieve fields
        passwordHash = CLASS.getDeclaredField("passwordHash");
        passwordSalt = CLASS.getDeclaredField("passwordSalt");
    }

    @Test
    public void passwordHashJsonProperty() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Map<String, Object> annotationParameters = new HashMap<>();
        annotationParameters.put("access", JsonProperty.Access.WRITE_ONLY);
        annotationParameters.put("value", "password");
        assertAnnotation(passwordHash, JsonProperty.class, annotationParameters);
    }

    @Test
    public void passwordSaltJsonIgnore() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Map<String, Object> annotationParameters = new HashMap<>();
        annotationParameters.put("value", true);
        assertAnnotation(passwordSalt, JsonIgnore.class, annotationParameters);
    }
}