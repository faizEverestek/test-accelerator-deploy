/*---------------------------------------------------------------------------------------------
FINEOS

(c) Copyright FINEOS.
ALL RIGHTS RESERVED

---------------------------------------------------------------------------------------------*/
package com.fineos.idam.petInsurance.commons;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.OptionalLong;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * A utility class that generates test instances for any given class using reflection.
 * This is particularly useful for testing purposes where default instances of classes are required.
 */
@SuppressWarnings("all")
public final class TestDataGenerator {

    /**
     * Private constructor to prevent instantiation of the utility class.
     *
     * @throws UnsupportedOperationException Always throws an exception since this is a utility class.
     */
    private TestDataGenerator() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    /**
     * Creates a test instance of the specified class by populating its fields with default values
     * and using the constructor that matches the field types.
     *
     * @param testClass The class for which a test instance is to be created.
     * @param <T>       The type of the class for which the test instance is generated.
     * @return An instance of the specified class with default values set for its fields.
     * @throws RuntimeException If the instance creation fails (e.g., due to missing constructor or reflection issues).
     */
    public static <T> T createTestInstance(Class<T> testClass) {
        try {
            // Get all declared fields of the class
            Field[] fields = testClass.getDeclaredFields();
            List<Class<?>> parameterTypes = new ArrayList<>();
            List<Object> parameterValues = new ArrayList<>();

            // Set default value for each declared field
            for (Field field : fields) {
                parameterTypes.add(field.getType());
                parameterValues.add(getDefaultValue(field.getType()));
            }

            // Find the constructor that matches the collected parameter types
            Constructor<T> constructor = testClass.getDeclaredConstructor(parameterTypes.toArray(new Class<?>[0]));
            constructor.setAccessible(true); // Allow access if it's private

            // Return an instance using the constructor
            return constructor.newInstance(parameterValues.toArray());

        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException |
                 InvocationTargetException e) {
            throw new RuntimeException("Failed to create test instance for: " + testClass.getSimpleName(), e);
        }
    }

    /**
     * A mapping of Java types to their default values for quick lookup.
     */
    private static final Map<Class<?>, Object> DEFAULT_VALUES = new HashMap<>();

    static {
        // Primitive types and their wrapper classes
        DEFAULT_VALUES.put(int.class, 1);
        DEFAULT_VALUES.put(Integer.class, 1);
        DEFAULT_VALUES.put(long.class, 1L);
        DEFAULT_VALUES.put(Long.class, 1L);
        DEFAULT_VALUES.put(float.class, 1.0f);
        DEFAULT_VALUES.put(Float.class, 1.0f);
        DEFAULT_VALUES.put(double.class, 1.0d);
        DEFAULT_VALUES.put(Double.class, 1.0d);
        DEFAULT_VALUES.put(boolean.class, true);
        DEFAULT_VALUES.put(Boolean.class, true);
        DEFAULT_VALUES.put(char.class, 'A');
        DEFAULT_VALUES.put(Character.class, 'A');
        DEFAULT_VALUES.put(short.class, (short) 1);
        DEFAULT_VALUES.put(Short.class, (short) 1);
        DEFAULT_VALUES.put(byte.class, (byte) 1);
        DEFAULT_VALUES.put(Byte.class, (byte) 1);

        // Common Java objects
        DEFAULT_VALUES.put(String.class, "String");
        DEFAULT_VALUES.put(UUID.class, UUID.randomUUID());

        // Collections and Maps
        DEFAULT_VALUES.put(List.class, Collections.emptyList());
        DEFAULT_VALUES.put(ArrayList.class, new ArrayList<>());
        DEFAULT_VALUES.put(LinkedList.class, new LinkedList<>());
        DEFAULT_VALUES.put(Set.class, Collections.emptySet());
        DEFAULT_VALUES.put(HashSet.class, new HashSet<>());
        DEFAULT_VALUES.put(TreeSet.class, new TreeSet<>());
        DEFAULT_VALUES.put(Map.class, Collections.emptyMap());
        DEFAULT_VALUES.put(HashMap.class, new HashMap<>());
        DEFAULT_VALUES.put(TreeMap.class, new TreeMap<>());

        // Date and Time
        DEFAULT_VALUES.put(Timestamp.class, new Timestamp(System.currentTimeMillis()));
        DEFAULT_VALUES.put(LocalDate.class, LocalDate.now());
        DEFAULT_VALUES.put(LocalTime.class, LocalTime.now());
        DEFAULT_VALUES.put(LocalDateTime.class, LocalDateTime.now());
        DEFAULT_VALUES.put(ZonedDateTime.class, ZonedDateTime.now());
        DEFAULT_VALUES.put(Instant.class, Instant.now());
        DEFAULT_VALUES.put(Duration.class, Duration.ofSeconds(60));
        DEFAULT_VALUES.put(Period.class, Period.ofDays(1));
        DEFAULT_VALUES.put(OffsetDateTime.class, OffsetDateTime.now());

        // Atomic types
        DEFAULT_VALUES.put(AtomicInteger.class, new AtomicInteger(1));
        DEFAULT_VALUES.put(AtomicLong.class, new AtomicLong(1L));
        DEFAULT_VALUES.put(AtomicBoolean.class, new AtomicBoolean(true));

        // Streams and Optionals
        DEFAULT_VALUES.put(Stream.class, Stream.empty());
        DEFAULT_VALUES.put(IntStream.class, IntStream.of(1, 2, 3));
        DEFAULT_VALUES.put(LongStream.class, LongStream.of(1L, 2L, 3L));
        DEFAULT_VALUES.put(DoubleStream.class, DoubleStream.of(1.0, 2.0, 3.0));
        DEFAULT_VALUES.put(Optional.class, Optional.empty());
        DEFAULT_VALUES.put(OptionalInt.class, OptionalInt.empty());
        DEFAULT_VALUES.put(OptionalLong.class, OptionalLong.empty());
        DEFAULT_VALUES.put(OptionalDouble.class, OptionalDouble.empty());

        // Arrays
        DEFAULT_VALUES.put(int[].class, new int[] { 1, 2, 3 });
        DEFAULT_VALUES.put(double[].class, new double[] { 1.0, 2.0, 3.0 });
        DEFAULT_VALUES.put(String[].class, new String[] { "A", "B", "C" });
        DEFAULT_VALUES.put(Object[].class, new Object[] {});

        // BigDecimal & BigInteger
        DEFAULT_VALUES.put(BigInteger.class, BigInteger.ONE);
        DEFAULT_VALUES.put(BigDecimal.class, BigDecimal.ONE);
    }

    /**
     * Retrieves the default value for the specified class type.
     * <p>
     * If the given class is an {@code Enum}, the first available constant is
     * returned. If the type is not found in the predefined defaults, {@code null}
     * is returned.
     * </p>
     *
     * @param type The class type of the field for which a default value is required.
     * @return A default value corresponding to the input type, or {@code null} if no specific value is defined.
     */
    private static Object getDefaultValue(Class<?> type) {
        // Handle Enum types by returning the first constant
        if (type.isEnum()) {
            return type.getEnumConstants().length > 0 ? type.getEnumConstants()[0] : null;
        }
        // Retrieve from the precomputed default values map
        return DEFAULT_VALUES.getOrDefault(type, null);
    }
}
