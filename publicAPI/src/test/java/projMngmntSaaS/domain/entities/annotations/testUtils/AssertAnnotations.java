package projMngmntSaaS.domain.entities.annotations.testUtils;

import org.junit.Assert;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Contains utility methods for asserting annotations existence on a an {@link AnnotatedElement}.
 * <br><br>
 * <i>Note: Inspired from <a href="https://dzone.com/articles/unit-testing-jpastop-integration-testing">blog post</a> by
 * Michael Remijan.</i>
 */
public class AssertAnnotations
{
    /**
     * Asserts that an annotation is present on the specified <code>annotatedElement</code> with the given
     * <code>annotationParams</code>.
     * <br>
     * <br>
     * <u>example</u><br><code>
     * Map&lt;String, GenerationType> annotationParameters = new HashMap<>();<br>
     * annotationParameters.put("strategy", GenerationType.AUTO);
     * </code><br>OR<br><code>
     * Map&lt;Class<?>, GenerationType> annotationParameters = new HashMap<>();<br>
     * annotationParameters.put(GenerationType.class, GenerationType.AUTO);
     * </code><br><br>Followed by<br><code>
     * assertAnnotation(id, GeneratedValue.class, annotationParameters);
     * </code>
     *
     * @param annotatedElement              Element to check annotation against
     * @param annotationType                Type of the annotation to check
     * @param annotationParams              Map of the annotation's parameter identifiers to each's expected values.
     *                                      The values type can be {@link Enum} values or their {@link String}
     *                                      counterpart.
     *                                      The keys type can either be of type {@link Class} or {@link String}.
     *                                      Using {@link String}, the getter's name on the annotation's interface is
     *                                      to be supplied.
     *                                      {@link Class} can be used solely when the annotation's interface methods
     *                                      contain only one method with the supplied class as a return type.
     * @param <T>                           extends {@link Annotation}
     * @param <U>                           either {@link String} or {@link Class}
     * @param <V>                           extends {@link Enum}
     * @throws NoSuchMethodException        Getter not existent
     * @throws InvocationTargetException    Getter uninvokable
     * @throws IllegalAccessException       Getter inaccessible
     */
    public static <T extends Annotation, U, V> void assertAnnotation(
            AnnotatedElement annotatedElement, Class<T> annotationType, Map<U, V> annotationParams)
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        // Check annotation presence
        Assert.assertFalse(annotationType.getSimpleName() + " annotation should be present."
                , annotatedElement.getAnnotation(annotationType) == null);

        // Check annotation expected parameters conformance
        if (annotationParams != null && annotationParams.size() > 0) {
            Map<String, Map<String, String>> annotationErrors = new HashMap<>();
            T annotation = annotatedElement.getAnnotation(annotationType);
            String annotationTypeName = annotation.annotationType().getName();
            Method annotationParamGetter = null;
            boolean isStringNotType  = String.class.equals(annotationParams.keySet().iterator().next().getClass());

            if (!isStringNotType) {
                for (U annotationParamIdentifier : annotationParams.keySet()) {
                    int returnTypeMethodsCount = 0;
                    for (Method method : annotation.getClass().getMethods()) {
                        if (annotationParamIdentifier.equals(method.getReturnType())) {
                            annotationParamGetter = method;
                            returnTypeMethodsCount++;
                        }
                    }
                    if (returnTypeMethodsCount == 0) {
                        throw new NoSuchMethodException("No getter exists with the supplied return type for the "
                                + "annotation. You may want to double check your tests.");
                    }
                    else if (returnTypeMethodsCount > 1) {
                        throw new IllegalArgumentException("Annotation getter unidentifiable via the supplied return type. "
                                + "More than one getter exists in the annotation's interface with the supplied return type."
                                + " You may want to double check your tests.");
                    }
                    V annotationParamValue = annotationParams.get(annotationParamIdentifier);
                    // Invoke annotation parameter getter, then compare with the expected enum value
                    if (!annotationParamGetter.invoke(annotation).equals(annotationParamValue)) {
                        // Add annotation parameter error [annotation_type, expected_value]
                        annotationErrors.putIfAbsent(annotationTypeName, new HashMap<>());
                        annotationErrors.get(annotationTypeName)
                                .put(annotationParamValue.getClass().getSimpleName(), annotationParamValue.toString());
                    }
                }
            }
            else {
                for (U annotationParamIdentifier : annotationParams.keySet()) {
                    V annotationParamValue = annotationParams.get(annotationParamIdentifier);
                    // Convert annotationParamIdentifier to itself's string & check method's existence
                    try {
                        annotationParamGetter = annotation.getClass().getMethod(String.valueOf(annotationParamIdentifier));
                    }
                    catch (NoSuchMethodException e) {
                        throw new IllegalArgumentException("No getter '" + annotationParamIdentifier
                                + "' exists for the annotation. You may want to double check your tests.", e);
                    }
                    // Invoke annotation parameter getter, then compare with the expected enum value
                    if (!annotationParamGetter.invoke(annotation).equals(annotationParamValue)) {
                        // Add annotation parameter error [annotation_type, expected_value]
                        annotationErrors.putIfAbsent(annotationTypeName, new HashMap<>());
                        annotationErrors.get(annotationTypeName)
                                .put(annotationParamIdentifier.toString(), annotationParamValue.toString());
                    }
                }
            }

            // Format annotation errors
            StringBuilder message = new StringBuilder();
            for (String annotationName : annotationErrors.keySet()) {
                message.append(annotationName).append(": missing annotation's following (parameter, value)(s);\n");
                Map<String, String> annotationParameter_value = annotationErrors.get(annotationName);
                for (String annotationParameter : annotationParameter_value.keySet()) {
                    message.append("\t").append(annotationParameter).append(" -> ")
                            .append(annotationParameter_value.get(annotationParameter)).append("\n");
                }
            }
            Assert.assertTrue(message.toString(), annotationErrors.isEmpty());
        }
    }

    /**
     * Shortcut for {@link AssertAnnotations#assertAnnotation(AnnotatedElement, Class, Map)} with no annotation
     * parameters.
     * @see AssertAnnotations#assertAnnotation(AnnotatedElement, Class, Map)
     */
    public static <T extends Annotation> void assertAnnotation(
            AnnotatedElement annotatedElement, Class<T> annotationType)
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        assertAnnotation(annotatedElement, annotationType, null);
    }
}
