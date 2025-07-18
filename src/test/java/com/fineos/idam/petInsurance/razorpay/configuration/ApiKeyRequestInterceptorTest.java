/*---------------------------------------------------------------------------------------------
FINEOS

(c) Copyright FINEOS.
ALL RIGHTS RESERVED

---------------------------------------------------------------------------------------------*/
package com.fineos.idam.petInsurance.razorpay.configuration;

import feign.RequestTemplate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link ApiKeyRequestInterceptor}.
 * <p>
 * This test class ensures that the API key is correctly added to the request
 * headers or query parameters based on the specified location.
 * </p>
 */
@SuppressWarnings("PMD.JUnitTestContainsTooManyAsserts")
class ApiKeyRequestInterceptorTest {

    /**
     * Constant representing the "header" location for API key injection.
     */
    private static final String HEADER = "header";

    /**
     * Constant representing the "query" location for API key injection.
     */
    private static final String QUERY = "query";

    /**
     * Name of the API key used in the request.
     */
    private static final String API_KEY_NAME = "x-api-key";

    /**
     * Value of the API key used in the request.
     */
    private static final String API_KEY_VALUE = "test-api-key";

    /**
     * Request template used for testing Feign requests.
     */
    private RequestTemplate requestTemplate;

    /**
     * Initializes a new {@link RequestTemplate} before each test execution.
     */
    @BeforeEach
    void setUp() {
        requestTemplate = new RequestTemplate();
    }

    /**
     * Tests that when the API key is set to be added in the request header, it is
     * correctly included in the headers of the request template.
     */
    @Test
    void testHeaderApiKeyInterceptor() {
        // Create an interceptor that adds the API key in the header
        ApiKeyRequestInterceptor interceptor = new ApiKeyRequestInterceptor(HEADER, API_KEY_NAME, API_KEY_VALUE);

        // Apply the interceptor to the request template
        interceptor.apply(requestTemplate);

        // Verify that the API key header is present in the request
        Assertions.assertTrue(requestTemplate.headers().containsKey(API_KEY_NAME), "Header should contain API key");

        // Verify that the API key value matches the expected value
        Assertions.assertEquals(API_KEY_VALUE, requestTemplate.headers().get(API_KEY_NAME).iterator().next(),
                "API key value should match");
    }

    /**
     * Tests that when the API key is set to be added as a query parameter, it is
     * correctly included in the query parameters of the request template.
     */
    @Test
    void testQueryApiKeyInterceptor() {
        // Create an interceptor that adds the API key as a query parameter
        ApiKeyRequestInterceptor interceptor = new ApiKeyRequestInterceptor(QUERY, API_KEY_NAME, API_KEY_VALUE);

        // Apply the interceptor to the request template
        interceptor.apply(requestTemplate);

        // Verify that the API key query parameter is present in the request
        Assertions.assertTrue(requestTemplate.queries().containsKey(API_KEY_NAME), "Query should contain API key");

        // Verify that the API key value matches the expected value
        Assertions.assertEquals(API_KEY_VALUE, requestTemplate.queries().get(API_KEY_NAME).iterator().next(),
                "API key value should match");
    }

    /**
     * Tests that the constructor of {@link ApiKeyRequestInterceptor} throws a
     * {@link NullPointerException} when provided with null arguments.
     */
    @Test
    void testNullParametersInConstructor() {
        // Expect NullPointerException when passing null for location
        Assertions.assertThrows(NullPointerException.class,
                () -> new ApiKeyRequestInterceptor(null, API_KEY_NAME, API_KEY_VALUE),
                "NullPointerException should be thrown for null location");

        // Expect NullPointerException when passing null for API key name
        Assertions.assertThrows(NullPointerException.class,
                () -> new ApiKeyRequestInterceptor(HEADER, null, API_KEY_VALUE),
                "NullPointerException should be thrown for null API key name");

        // Expect NullPointerException when passing null for API key value
        Assertions.assertThrows(NullPointerException.class,
                () -> new ApiKeyRequestInterceptor(HEADER, API_KEY_NAME, null),
                "NullPointerException should be thrown for null API key value");
    }
}
