package com.fineos.idam.petInsurance.commons;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

/**
 * Utility class for performing JSON-based assertions in JUnit tests.
 *
 * @param <T> the type parameter for the objects being compared
 */
public final class AssertionUtil<T> {

    /**
     * A singleton instance of {@link ObjectMapper} used for JSON serialization and
     * deserialization tasks.
     */
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    /**
     * Private constructor to prevent instantiation of this utility class. Asserting
     * utility methods are meant to be accessed statically. Invoking this
     * constructor will throw an {@link UnsupportedOperationException}.
     */
    private AssertionUtil() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    /**
     * Asserts that two objects are equal by comparing their JSON representations.
     *
     * @param actual   the actual object
     * @param expected the expected object
     * @param <T>      the type of the objects being compared
     */
    public static <T> void assertEquals(T actual, T expected) {
        try {
            String actualJson = OBJECT_MAPPER.writeValueAsString(actual);
            String expectedJson = OBJECT_MAPPER.writeValueAsString(expected);
            Assertions.assertEquals(expectedJson, actualJson, "JSON representations of the objects do not match");
        } catch (JsonProcessingException e) {
            Assertions.fail("Invalid JSON representations: " + e.getMessage());
        }
    }

    /**
     * Asserts that two lists of objects are equal by comparing their JSON
     * representations.
     *
     * @param actual   the actual list of objects
     * @param expected the expected list of objects
     * @param <T>      the type of the objects in the lists being compared
     */
    public static <T> void assertEquals(List<T> actual, List<T> expected) {
        try {
            String actualJson = OBJECT_MAPPER.writeValueAsString(actual);
            String expectedJson = OBJECT_MAPPER.writeValueAsString(expected);
            Assertions.assertEquals(expectedJson, actualJson, "JSON representations of the lists do not match");
        } catch (JsonProcessingException e) {
            Assertions.fail("Invalid JSON representations: " + e.getMessage());
        }
    }

    /**
     * Asserts that a Page object matches expected pagination properties.
     *
     * @param result           the Page object to check
     * @param totalElements    the expected total number of elements
     * @param pageNumber       the expected current page number
     * @param pageSize         the expected size of each page
     * @param numberOfElements the expected number of elements on the current page
     * @param pageable         the expected Pageable object
     * @param expected         a parameter object encapsulating remaining expected
     *                         values for clarity
     */
    public static void assertPageEquals(Page<?> result, long totalElements, int pageNumber, int pageSize,
            int numberOfElements, Pageable pageable, PageAssertOptions expected) {

        Assertions.assertNotNull(result, "Page result should not be null");
        Assertions.assertEquals(totalElements, result.getTotalElements(), "Total elements do not match");
        Assertions.assertEquals(pageNumber, result.getNumber(), "Current page number does not match");
        Assertions.assertEquals(pageSize, result.getSize(), "Page size does not match");
        Assertions.assertEquals(numberOfElements, result.getNumberOfElements(), "Number of elements does not match");
        Assertions.assertEquals(pageable, result.getPageable(), "Pageable does not match");
        Assertions.assertEquals(expected.hasNext(), result.hasNext(), "hasNext status does not match");
        Assertions.assertEquals(expected.hasPrevious(), result.hasPrevious(), "hasPrevious status does not match");
        Assertions.assertEquals(numberOfElements > 0, result.hasContent(), "hasContent status does not match");
        Assertions.assertEquals(pageNumber == 0, result.isFirst(), "isFirst status does not match");
        Assertions.assertEquals(totalElements <= (long) pageSize * (pageNumber + 1), result.isLast(),
                "isLast status does not match");
        Assertions.assertEquals(expected.sort(), result.getSort(), "Sort order does not match");

        Pageable actualNextPageable = result.nextPageable();
        if (expected.hasNext()) {
            Assertions.assertEquals(expected.nextPageable(), actualNextPageable, "Next pageable does not match");
        } else {
            Assertions.assertEquals(expected.nextOrLastPageable(), result.getPageable(),
                    "Next or last pageable mismatch");
        }

        Pageable actualPreviousPageable = result.previousPageable();
        if (expected.hasPrevious()) {
            Assertions.assertEquals(expected.previousPageable(), actualPreviousPageable,
                    "Previous pageable does not match");
        } else {
            Assertions.assertEquals(expected.previousOrFirstPageable(), result.getPageable(),
                    "Previous or first pageable mismatch");
        }
    }

    /**
     * Represents options for asserting properties of a {@link Page} object.
     *
     * @param hasNext                 indicates if there is a next page
     * @param hasPrevious             indicates if there is a previous page
     * @param nextOrLastPageable      the {@link Pageable} object for the next or
     *                                last page
     * @param nextPageable            the {@link Pageable} object for the next page
     * @param previousOrFirstPageable the {@link Pageable} object for the previous
     *                                or first page
     * @param previousPageable        the {@link Pageable} object for the previous
     *                                page
     * @param sort                    the {@link Sort} object indicating the sort
     *                                order
     */
    public record PageAssertOptions(boolean hasNext, boolean hasPrevious, Pageable nextOrLastPageable,
            Pageable nextPageable, Pageable previousOrFirstPageable, Pageable previousPageable, Sort sort) {
        // Empty block
    }
}
