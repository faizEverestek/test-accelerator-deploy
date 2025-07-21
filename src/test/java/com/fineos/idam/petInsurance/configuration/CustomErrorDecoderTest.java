/*---------------------------------------------------------------------------------------------
FINEOS

(c) Copyright FINEOS.
ALL RIGHTS RESERVED

---------------------------------------------------------------------------------------------*/
package com.fineos.idam.petInsurance.configuration;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import feign.FeignException;
import feign.Request;
import feign.Response;
import feign.RetryableException;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

/**
 * Unit test class for {@link CustomErrorDecoder}.
 *
 * <p>
 * This class tests the behavior of the {@code CustomErrorDecoder}, including
 * handling retryable and non-retryable HTTP status codes.
 */
@SuppressWarnings("PMD.AvoidDuplicateLiterals")
class CustomErrorDecoderTest {

    /** Instance of {@link CustomErrorDecoder} to be tested. */
    private CustomErrorDecoder errorDecoder;

    /**
     * Sets up the test environment before each test case.
     *
     * <p>
     * Initializes the {@code CustomErrorDecoder} instance.
     */
    @BeforeEach
    void setUp() {
        errorDecoder = new CustomErrorDecoder();
    }

    /**
     * Tests the decoder behavior for a retryable HTTP status (503 - Service
     * Unavailable).
     *
     * <p>
     * Ensures that the decoder returns a {@link RetryableException} when the
     * response status is {@code 503
     * SERVICE_UNAVAILABLE}.
     */
    @Test
    void testDecodeRetryableStatusServiceUnavailable() {
        try (Response response = createResponse(HttpStatus.SERVICE_UNAVAILABLE.value())) {
            Exception exception = errorDecoder.decode("testMethod", response);

            assertInstanceOf(RetryableException.class, exception, "Expected a RetryableException for HTTP 503");
        }
    }

    /**
     * Tests the decoder behavior for a retryable HTTP status (504 - Gateway
     * Timeout).
     *
     * <p>
     * Ensures that the decoder returns a {@link RetryableException} when the
     * response status is {@code 504
     * GATEWAY_TIMEOUT}.
     */
    @Test
    void testDecodeRetryableStatusGatewayTimeout() {
        try (Response response = createResponse(HttpStatus.GATEWAY_TIMEOUT.value())) {
            Exception exception = errorDecoder.decode("testMethod", response);

            assertInstanceOf(RetryableException.class, exception, "Expected a RetryableException for HTTP 504");
        }
    }

    /**
     * Tests the decoder behavior for a non-retryable HTTP status (400 - Bad
     * Request).
     *
     * <p>
     * Ensures that the decoder returns a {@link FeignException} instead of a
     * retryable exception when the response status is {@code 400 BAD_REQUEST}.
     */
    @Test
    void testDecodeNonRetryableStatusBadRequest() {
        try (Response response = createResponse(HttpStatus.BAD_REQUEST.value())) {
            Exception exception = errorDecoder.decode("testMethod", response);

            assertInstanceOf(FeignException.class, exception, "Expected a FeignException for HTTP 400");
        }
    }

    /**
     * Tests the decoder behavior for a non-retryable HTTP status (500 - Internal
     * Server Error).
     *
     * <p>
     * Ensures that the decoder returns a {@link FeignException} instead of a
     * retryable exception when the response status is
     * {@code 500 INTERNAL_SERVER_ERROR}.
     */
    @Test
    void testDecodeNonRetryableStatusInternalServerError() {
        try (Response response = createResponse(HttpStatus.INTERNAL_SERVER_ERROR.value())) {
            Exception exception = errorDecoder.decode("testMethod", response);

            assertInstanceOf(FeignException.class, exception, "Expected a FeignException for HTTP 500");
        }
    }

    /**
     * Creates a mock {@link Response} with a given HTTP status code.
     *
     * <p>
     * The response includes a basic request structure to simulate an actual Feign
     * request-response cycle.
     *
     * @param statusCode the HTTP status code to be used in the response
     * @return a mock {@link Response} instance with the specified status code
     */
    private Response createResponse(int statusCode) {
        Request request = Request.create(Request.HttpMethod.GET, "http://localhost/test", Collections.emptyMap(), null,
                StandardCharsets.UTF_8, null);

        return Response.builder().request(request).status(statusCode).reason("Mock Response").build();
    }
}
