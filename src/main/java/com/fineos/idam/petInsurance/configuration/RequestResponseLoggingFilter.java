/*---------------------------------------------------------------------------------------------
FINEOS

(c) Copyright FINEOS.
ALL RIGHTS RESERVED

---------------------------------------------------------------------------------------------*/
package com.fineos.idam.petInsurance.configuration;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

/**
 * Filter to log HTTP request and response details, including headers and bodies. This filter wraps the request and
 * response to enable content caching for logging purposes. Logging can be enabled or disabled through a configuration
 * property.
 */
@Component
public class RequestResponseLoggingFilter extends OncePerRequestFilter {

    /** Flag to enable or disable HTTP payload logging. Defaults to false if not set in the configuration. */
    @Value("${logging.http-payload.enabled:false}")
    private boolean loggingEnabled;

    /** Correlation ID for tracking requests across services. */
    public static final String X_CORRELATION_ID = "X-Correlation-ID";

    /** Client ID identifying the client making the request. */
    public static final String X_CLIENT_ID = "X-Client-ID";

    /** Client version of the requesting application. */
    public static final String X_CLIENT_VERSION = "X-Client-Version";

    /** Default value for X-Client-ID and X-Client-Version headers. */
    public static final String UNKNOWN = "Unknown";

    /** Logger for logging request and response details. */
    private static final Logger LOGGER = LoggerFactory.getLogger(RequestResponseLoggingFilter.class);

    /**
     * Filters the incoming HTTP request and outgoing HTTP response. If logging is enabled, it caches the content for
     * logging purposes.
     *
     * @param request the HTTP request
     * @param response the HTTP response
     * @param filterChain the filter chain
     * @throws ServletException if there is a servlet exception
     * @throws IOException if there is an I/O exception
     */
    @Override
    protected void doFilterInternal(
            final HttpServletRequest request, final HttpServletResponse response, final FilterChain filterChain)
            throws ServletException, IOException {

        // Get headers value from request
        String correlationId =
                getHeaderOrDefault(request, X_CORRELATION_ID, UUID.randomUUID().toString());
        String clientId = getHeaderOrDefault(request, X_CLIENT_ID, UNKNOWN);
        String clientVersion = getHeaderOrDefault(request, X_CLIENT_VERSION, UNKNOWN);

        // Setting the headers in the response
        response.setHeader(X_CORRELATION_ID, correlationId);
        response.setHeader(X_CLIENT_ID, clientId);
        response.setHeader(X_CLIENT_VERSION, clientVersion);

        if (!loggingEnabled) {
            // If logging is disabled, proceed without wrapping
            filterChain.doFilter(request, response);
            return;
        }

        // Wrap both request and response to enable caching
        final ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper(request);
        final ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(response);

        try {
            // Proceed with the next filter in the chain
            filterChain.doFilter(requestWrapper, responseWrapper);
        } finally {
            // Log the request and response details
            logRequest(requestWrapper);
            logResponse(responseWrapper);

            // Ensure the response is correctly written back to the client
            responseWrapper.copyBodyToResponse();
        }
    }

    /**
     * Logs the details of the incoming HTTP request, including its method, URI, and body.
     *
     * @param request the cached HTTP request
     */
    private void logRequest(final ContentCachingRequestWrapper request) {
        final String requestBody = new String(request.getContentAsByteArray(), StandardCharsets.UTF_8);

        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("Incoming Request: '{}' '{}'", request.getMethod(), request.getRequestURI());
        }
        if (!requestBody.isEmpty() && LOGGER.isInfoEnabled()) {
            LOGGER.info("Request Payload {body = '{}'}", requestBody);
        }
    }

    /**
     * Logs the details of the outgoing HTTP response, including its status and body.
     *
     * @param response the cached HTTP response
     */
    private void logResponse(final ContentCachingResponseWrapper response) {
        final String responseBody = new String(response.getContentAsByteArray(), StandardCharsets.UTF_8);
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("Response Payload {status = '{}', body = '{}'}", response.getStatus(), responseBody);
        }
    }

    /**
     * Retrieves the value of the specified header from the request. If the header is not present, returns the provided
     * default value.
     *
     * @param request the HttpServletRequest object containing client request information
     * @param headerName the name of the header to retrieve
     * @param defaultValue the default value to return if the header is not present
     * @return the value of the specified header, or the default value if the header is not present
     */
    private String getHeaderOrDefault(HttpServletRequest request, String headerName, String defaultValue) {
        return Optional.ofNullable(request.getHeader(headerName)).orElse(defaultValue);
    }
}
