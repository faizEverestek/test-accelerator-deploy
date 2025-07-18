package com.fineos.idam.petInsurance.commons;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.time.OffsetDateTime;
import org.junit.jupiter.api.Assertions;
import java.lang.reflect.Constructor;

/**
 * Utility class for performing assertions on Data Transfer Objects (DTOs).
 * Includes methods for verifying getter/setter correctness,
 * validating hashCode and equals implementations,
 * and generating JSON representations of objects.
 *
 * @param <T> the type of the object being validated
 */
@SuppressWarnings({"PMD.AvoidAccessibilityAlteration", "PMD.AvoidThrowingRawExceptionTypes"})
public final class AssertionUtilDto<T> {

    /**
     * An {@link ObjectMapper} instance for serializing and deserializing objects to and from JSON.
     * Configured with the JavaTime module and a specific date format.
     */
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper()
            .registerModule(new JavaTimeModule())
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
            .setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", java.util.Locale.US));

    /**
     * Private constructor to prevent instantiation of the utility class.
     *
     * @throws UnsupportedOperationException Always throws an exception since this is a utility class.
     */
    private AssertionUtilDto() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    /**
     * Asserts that two objects are equal by comparing their JSON representations.
     * If the objects are instances of {@link OffsetDateTime}, the comparison is done
     * by comparing their {@link java.time.Instant} values.
     *
     * @param actual   the actual object to be validated
     * @param expected the expected object to be compared with
     * @param <T>      the type of the objects being compared
     */
    public static <T> void verifySetterGetters(T actual, T expected) {
        try {
            if (actual instanceof OffsetDateTime time && expected instanceof OffsetDateTime time1) {
                Assertions.assertEquals(
                        time1.toInstant(),
                        time.toInstant(),
                        "The OffsetDateTime objects do not match. Getters and Setters might be incorrect.");
            } else {
                String actualJson = OBJECT_MAPPER.writeValueAsString(actual);
                String expectedJson = OBJECT_MAPPER.writeValueAsString(expected);
                Assertions.assertEquals(
                        expectedJson,
                        actualJson,
                        "The JSON representations of the objects do not match. Getters and Setters might be incorrect.");
            }
        } catch (JsonProcessingException e) {
            Assertions.fail("Failed to compare JSON representations: %s, %s".formatted(actual, expected));
        }
    }

    /**
     * Verifies the correctness of equals() and hashCode() implementations for the given objects.
     * Performs checks for equality and inequality between objects of the same type,
     * non-null constraints, and field-by-field equality using reflection.
     *
     * @param o1  the first object to be tested
     * @param o2  the second object, which should be equal to o1
     * @param o3  the third object, which should not be equal to o1
     * @param <T> the type of the objects being validated
     */
    public static <T> void verifyHashAndEquals(T o1, T o2, T o3) {
        // Verify against null
        Assertions.assertNotNull(o1, "o1 should have not been null.");
        Assertions.assertNotNull(o2, "o2 should have not been null.");
        Assertions.assertNotNull(o3, "o3 should have not been null.");

        Assertions.assertNotEquals(null, o1, "o1 should have not been equal to null.");
        Assertions.assertNotEquals(null, o2, "o2 should have not been equal to null.");
        Assertions.assertNotEquals(null, o3, "o3 should have not been equal to null.");

        // Verify same reference
        Assertions.assertEquals(o1, o1, "o1 should have been equal to itself.");
        Assertions.assertEquals(o2, o2, "o2 should have been equal to itself.");
        Assertions.assertEquals(o3, o3, "o3 should have been equal to itself.");

        // Verify against different type of object
        Assertions.assertNotEquals("Other Type Object", o1, "o1 should not have been equal to other types.");
        Assertions.assertNotEquals("Other Type Object", o2, "o2 should not have been equal to other types.");
        Assertions.assertNotEquals("Other Type Object", o3, "o3 should not have been equal to other types.");

        // Verify equality between o1 and o2
        Assertions.assertEquals(o1, o2, "o1 and o2 should have been equal.");
        Assertions.assertEquals(o1.hashCode(), o2.hashCode(), "Hash codes of o1 and o2 should have been equal.");

        // Verify inequality between o1 and o3
        Assertions.assertNotEquals(o1, o3, "o1 and o3 objects should not have been equal.");
        Assertions.assertNotEquals(o2, o3, "o2 and o3 objects should not have been equal.");
        Assertions.assertNotEquals(o1.hashCode(), o3.hashCode(), "Hash codes of o1 and o3 should not have been equal.");
        Assertions.assertNotEquals(o2.hashCode(), o3.hashCode(), "Hash codes of o2 and o3 should not have been equal.");

        // Get declared fields in the class
        Field[] o2Fields = o2.getClass().getDeclaredFields();
        Field[] o3Fields = o3.getClass().getDeclaredFields();
        int fieldCount = o2Fields.length;

        // Make fields equal in the order they are declared
        for (int index = 0; index < fieldCount; index++) {
            try {
                o2Fields[index].setAccessible(true);
                o3Fields[index].setAccessible(true);
                o3Fields[index].set(o3, o2Fields[index].get(o2));
                if (index < fieldCount - 1) {
                    Assertions.assertNotEquals(
                            o2,
                            o3,
                            "o2 and o3 objects should not have been equal. "
                                    + "This occurred while comparing field "
                                    + o2Fields[index].getName());
                } else {
                    Assertions.assertEquals(o2, o3, "o2 and o3 objects should have been equal.");
                }
            } catch (IllegalArgumentException | IllegalAccessException e) {
                Assertions.fail(
                        "Error encountered while setting the field '"
                                + o2Fields[index].getName()
                                + "' using reflection",
                        e);
            }
        }
    }

    /**
     * Generates the JSON representation of the given object using {@link ObjectMapper}.
     *
     * @param o   the object to be serialized to JSON
     * @param <T> the type of the object
     * @return the JSON string representation of the object
     * @throws JsonProcessingException if there is an error during JSON serialization
     */
    public static <T> String getJsonRepresentation(T o) throws JsonProcessingException {
        return OBJECT_MAPPER.writeValueAsString(o);
    }

    /**
     * Generates a string representation of an object by retrieving its field values
     * using reflection.
     *
     * <p>Format: ClassName(field1=value1, field2=value2, ...)</p>
     *
     * @param <T> The type of the object.
     * @param obj The object to be converted to string representation.
     * @return A string representation of the object or "null" if the object is null.
     * @throws RuntimeException If there is an error while accessing the object's fields.
     */
    public static <T> String getToString(T obj) {
        if (obj == null) {
            return "null";
        }

        Class<?> clazz = obj.getClass();
        StringBuilder sb = new StringBuilder(clazz.getSimpleName()).append('(');

        try {
            // Find a constructor with parameters (assuming it's the all-args constructor)
            Constructor<?> allArgsConstructor = null;
            for (Constructor<?> constructor : clazz.getDeclaredConstructors()) {
                if (constructor.getParameterCount() > 0) {
                    allArgsConstructor = constructor;
                    break;
                }
            }

            // If no all-args constructor is found, fallback to the default toString() method
            if (allArgsConstructor == null) {
                return obj.toString();
            }

            // Retrieve all declared fields of the class
            Field[] fields = clazz.getDeclaredFields();
            for (int i = 0; i < fields.length; i++) {
                fields[i].setAccessible(true); // Allow access to private fields
                sb.append(fields[i].getName()).append('=').append(fields[i].get(obj));

                // Append a comma if more fields exist
                if (i < fields.length - 1) {
                    sb.append(", ");
                }
            }
        } catch (SecurityException | IllegalAccessException e) {
            throw new RuntimeException("Error converting object to string", e);
        }

        sb.append(')');
        return sb.toString();
    }

}
