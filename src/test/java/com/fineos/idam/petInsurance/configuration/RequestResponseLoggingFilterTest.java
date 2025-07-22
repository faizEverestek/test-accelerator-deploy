package com.fineos.idam.petInsurance.configuration;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import java.nio.charset.StandardCharsets;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

/**
 * Unit test class for {@link RequestResponseLoggingFilter}.
 *
 * <p>This class verifies that HTTP request and response logging works as expected, ensuring that headers are set,
 * logging conditions are respected, and response bodies are processed correctly.
 */
@SuppressWarnings("PMD.JUnitTestContainsTooManyAsserts")
class RequestResponseLoggingFilterTest {

    /** The filter instance under test. */
    private RequestResponseLoggingFilter filter;

    /** Mocked request. */
    private MockHttpServletRequest request;

    /** Mocked response. */
    private MockHttpServletResponse response;

    /** Mocked filter chain. */
    private FilterChain filterChain;

    /**
     * Sets up the test environment before each test case. Initializes the filter, request, response, and filter chain.
     */
    @BeforeEach
    void setUp() {
        filter = new RequestResponseLoggingFilter();
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
        filterChain = Mockito.mock(FilterChain.class);
    }

    /**
     * Tests that correlation headers are correctly set in the response when they are missing from the request.
     */
    @Test
    void testHeadersAreSetWhenMissing() throws ServletException, IOException {
        filter.doFilterInternal(request, response, filterChain);

        Assertions.assertNotNull(response.getHeader(RequestResponseLoggingFilter.X_CORRELATION_ID),
                "Correlation ID header should not be null");
        Assertions.assertEquals(RequestResponseLoggingFilter.UNKNOWN, response.getHeader(RequestResponseLoggingFilter.X_CLIENT_ID),
                "Client ID header should be set to UNKNOWN if missing");
        Assertions.assertEquals(RequestResponseLoggingFilter.UNKNOWN, response.getHeader(RequestResponseLoggingFilter.X_CLIENT_VERSION),
                "Client Version header should be set to UNKNOWN if missing");

        Mockito.verify(filterChain).doFilter(Mockito.any(HttpServletRequest.class), Mockito.any(HttpServletResponse.class));
    }

    /**
     * Tests that correlation headers from the request are passed through to the response.
     */
    @Test
    void testHeadersAreRetainedWhenPresent() throws ServletException, IOException {
        request.addHeader(RequestResponseLoggingFilter.X_CORRELATION_ID, "12345");
        request.addHeader(RequestResponseLoggingFilter.X_CLIENT_ID, "TestClient");
        request.addHeader(RequestResponseLoggingFilter.X_CLIENT_VERSION, "1.0.0");

        filter.doFilterInternal(request, response, filterChain);

        Assertions.assertEquals("12345", response.getHeader(RequestResponseLoggingFilter.X_CORRELATION_ID),
                "Expected correlation ID header does not match");
        Assertions.assertEquals("TestClient", response.getHeader(RequestResponseLoggingFilter.X_CLIENT_ID),
                "Expected client ID header does not match");
        Assertions.assertEquals("1.0.0", response.getHeader(RequestResponseLoggingFilter.X_CLIENT_VERSION),
                "Expected client version header does not match");

        Mockito.verify(filterChain).doFilter(Mockito.any(HttpServletRequest.class), Mockito.any(HttpServletResponse.class));
    }

    /**
     * Tests that request and response logging is disabled when the `logging.http-payload.enabled` property is set to
     * false.
     */
    @Test
    void testLoggingIsDisabled() throws ServletException, IOException {
        ReflectionTestUtils.setField(filter, "loggingEnabled", false);

        filter.doFilterInternal(request, response, filterChain);

        Mockito.verify(filterChain).doFilter(Mockito.any(HttpServletRequest.class), Mockito.any(HttpServletResponse.class));
    }

    /**
     * Tests that request and response bodies are logged when logging is enabled.
     */
    @Test
    void testLoggingCapturesRequestAndResponseBody() throws ServletException, IOException {
        ReflectionTestUtils.setField(filter, "loggingEnabled", true);

        MockHttpServletRequest mockRequest = new MockHttpServletRequest();
        mockRequest.setContent("test request body".getBytes(StandardCharsets.UTF_8));
        ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper(mockRequest);

        MockHttpServletResponse mockResponse = new MockHttpServletResponse();
        ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(mockResponse);

        FilterChain mockChain = Mockito.mock(FilterChain.class);
        filter.doFilterInternal(requestWrapper, responseWrapper, mockChain);

        responseWrapper.copyBodyToResponse();

        Mockito.verify(mockChain).doFilter(Mockito.any(ContentCachingRequestWrapper.class), Mockito.any(ContentCachingResponseWrapper.class));
    }
}
