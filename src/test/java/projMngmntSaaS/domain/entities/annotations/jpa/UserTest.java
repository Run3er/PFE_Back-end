package projMngmntSaaS.domain.entities.annotations.jpa;

import org.junit.BeforeClass;
import org.junit.Test;
import projMngmntSaaS.domain.entities.User;

import javax.persistence.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import static projMngmntSaaS.domain.entities.annotations.testUtils.AssertAnnotations.assertAnnotation;

/**
 * Validating specific JPA annotations in {@link User}.
 */
public class UserTest
{
    private static final Class<User> CLASS = User.class;
    private static Field id;
    private static Field login;
    private static Field passwordHash;
    private static Field passwordSalt;
    private static Field firstName;
    private static Field lastName;
    private static Method getLogin;

    // Apart from initialization, this also checks for annotated elements name conformity to the used literals,
    // by throwing NoSuchFieldException hint, for annotated elements with JPA DB default name setting.
    @BeforeClass
    public static void getClassContents() throws NoSuchFieldException {
        // Retrieve fields
        id = CLASS.getDeclaredField("id");
        login = CLASS.getDeclaredField("login");
        passwordHash = CLASS.getDeclaredField("passwordHash");
        passwordSalt = CLASS.getDeclaredField("passwordSalt");
        firstName = CLASS.getDeclaredField("firstName");
        lastName = CLASS.getDeclaredField("lastName");
    }

    @Test
    public void classEntity() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Map<String, String> annotationParameters = new HashMap<>();
        annotationParameters.put("name", "");
        assertAnnotation(CLASS, Entity.class, annotationParameters);
    }

    @Test
    public void classTable() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Map<String, String> annotationParameters = new HashMap<>();
        annotationParameters.put("name", "user_account");
        assertAnnotation(CLASS, Table.class, annotationParameters);
    }

    @Test
    public void idId() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        assertAnnotation(id, Id.class);
    }

    @Test
    public void idGenerationValue() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Map<Class<?>, GenerationType> annotationParameters = new HashMap<>();
        annotationParameters.put(GenerationType.class, GenerationType.AUTO);
        assertAnnotation(id, GeneratedValue.class, annotationParameters);
    }

    @Test
    public void loginColumn() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Map<String, Object> annotationParameters = new HashMap<>();
        annotationParameters.put("name", "");
        annotationParameters.put("nullable", false);
        annotationParameters.put("length", 10);
        assertAnnotation(login, Column.class, annotationParameters);
    }

    @Test
    public void passwordHashColumn() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Map<String, Object> annotationParameters = new HashMap<>();
        annotationParameters.put("name", "");
        annotationParameters.put("nullable", false);
        assertAnnotation(passwordHash, Column.class, annotationParameters);
    }

    @Test
    public void passwordSaltColumn() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Map<String, Object> annotationParameters = new HashMap<>();
        annotationParameters.put("name", "");
        annotationParameters.put("nullable", false);
        assertAnnotation(passwordSalt, Column.class, annotationParameters);
    }

    @Test
    public void firstNameColumn() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Map<String, Object> annotationParameters = new HashMap<>();
        annotationParameters.put("name", "");
        annotationParameters.put("nullable", false);
        annotationParameters.put("length", 40);
        assertAnnotation(firstName, Column.class, annotationParameters);
    }

    @Test
    public void lastNameColumn() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Map<String, Object> annotationParameters = new HashMap<>();
        annotationParameters.put("name", "");
        annotationParameters.put("nullable", false);
        annotationParameters.put("length", 40);
        assertAnnotation(lastName, Column.class, annotationParameters);
    }
}