package projMngmntSaaS.domain.entities.annotations.jpa;

import org.junit.BeforeClass;
import org.junit.Test;
import projMngmntSaaS.domain.entities.TenantDetails;

import javax.persistence.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import static projMngmntSaaS.domain.entities.annotations.testUtils.AssertAnnotations.assertAnnotation;

/**
 * Validating specific JPA annotations in {@link TenantDetails}.
 */
public class TenantDetailsTest
{
    private static final Class<TenantDetails> CLASS = TenantDetails.class;
    private static Field id;
    private static Field name;
    private static Field email;
    private static Field websiteUrl;

    // Apart from initialization, this also checks for annotated elements name conformity to the used literals,
    // by throwing NoSuchFieldException hint, for annotated elements with JPA DB default name setting.
    @BeforeClass
    public static void getClassContents() throws NoSuchFieldException {
        // Retrieve fields
        id = CLASS.getDeclaredField("id");
        name = CLASS.getDeclaredField("name");
        email = CLASS.getDeclaredField("email");
        websiteUrl = CLASS.getDeclaredField("websiteUrl");
    }

    @Test
    public void classEntity() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Map<String, String> annotationParameters = new HashMap<>();
        annotationParameters.put("name", "");
        assertAnnotation(CLASS, Entity.class, annotationParameters);
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
    public void nameColumn() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Map<String, Object> annotationParameters = new HashMap<>();
        annotationParameters.put("name", "");
        annotationParameters.put("nullable", false);
        annotationParameters.put("length", 100);
        assertAnnotation(name, Column.class, annotationParameters);
    }

    @Test
    public void emailColumn() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Map<String, Object> annotationParameters = new HashMap<>();
        annotationParameters.put("name", "");
        annotationParameters.put("nullable", false);
        annotationParameters.put("length", 254);
        assertAnnotation(email, Column.class, annotationParameters);
    }

    @Test
    public void websiteUrlColumn() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Map<String, Object> annotationParameters = new HashMap<>();
        annotationParameters.put("name", "");
        annotationParameters.put("length", 2000);
        assertAnnotation(websiteUrl, Column.class, annotationParameters);
    }
}