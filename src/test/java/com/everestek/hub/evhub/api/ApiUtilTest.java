/*
---------------------------------------------------------------------------------------------
 EVERESTEK
 Herndon, VA

 (c) Copyright EVERESTEK Corporation.
 ALL RIGHTS RESERVED

 The software and information contained herein are proprietary to, and comprise valuable
 trade secrets of, EVERESTEK Corporation, which intends to preserve as trade secrets such
 software and information. This software should only be furnished subject to a written
 license agreement and may only be used, copied, transmitted, and stored in accordance
 with the terms of such license and with the inclusion of the above copyright notice.
 If there is no written License Agreement between you and EVERESTEK Corporation, then you
 have received this software in error and should be returned to EVERESTEK Corporation or
 destroyed immediately, and you should also notify EVERESTEK Corporation. This software and
 information or any other copies thereof may not be provided or otherwise made available
 to any person who is not authorized to receive it pursuant to a written license Agreement
 executed with EVERESTEK Corporation.
---------------------------------------------------------------------------------------------
*/

package com.everestek.hub.evhub.api;

import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.web.context.request.NativeWebRequest;

/**
 * Unit tests for {@link ApiUtil} class.
 *
 * <p>This test class verifies the functionality of the {@code setExampleResponse} method from the {@link ApiUtil}
 * utility class. It ensures that the method behaves as expected under various conditions, including normal execution,
 * null response scenarios, and cases where an I/O exception occurs.
 *
 * <p>Mock objects and {@link Mockito} framework are used to simulate the behavior of {@link NativeWebRequest} and
 * {@link HttpServletResponse}.
 */
@SuppressWarnings("PMD.JUnitTestContainsTooManyAsserts")
class ApiUtilTest {

    /** Mock representation of a {@link NativeWebRequest} for testing purposes. */
    private NativeWebRequest nativeWebRequest;

    /** Mock representation of an {@link HttpServletResponse} for testing purposes. */
    private HttpServletResponse httpServletResponse;

    /** A {@link StringWriter} used to capture response output during tests. */
    private StringWriter responseWriter;

    /**
     * Sets up the mock objects before each test execution.
     *
     * <p>This method initializes the mock {@link NativeWebRequest} and {@link HttpServletResponse}, and prepares a
     * {@link StringWriter} to capture the response output.
     *
     * @throws IOException If an I/O error occurs while setting up the response writer.
     */
    @BeforeEach
    void setUp() throws IOException {
        nativeWebRequest = Mockito.mock(NativeWebRequest.class);
        httpServletResponse = Mockito.mock(HttpServletResponse.class);
        responseWriter = new StringWriter();

        Mockito.when(nativeWebRequest.getNativeResponse(HttpServletResponse.class))
                .thenReturn(httpServletResponse);
        Mockito.when(httpServletResponse.getWriter()).thenReturn(new PrintWriter(responseWriter));
    }

    /** Tests that the {@code setExampleResponse} method correctly sets headers and writes the response body. */
    @Test
    void testSetExampleResponseSuccess() {
        String contentType = "application/json";
        String exampleResponse = "{\"message\":\"Test response\"}";

        ApiUtil.setExampleResponse(nativeWebRequest, contentType, exampleResponse);

        Mockito.verify(httpServletResponse).setCharacterEncoding("UTF-8");
        Mockito.verify(httpServletResponse).addHeader("Content-Type", contentType);
        Assertions.assertEquals(
                exampleResponse,
                responseWriter.toString(),
                "The response body does not match the expected example response.");
    }

    /** Tests that an {@code IllegalStateException} is thrown when {@link HttpServletResponse} is null. */
    @Test
    void testSetExampleResponseNullResponse() {
        Mockito.when(nativeWebRequest.getNativeResponse(HttpServletResponse.class))
                .thenReturn(null);

        IllegalStateException exception = Assertions.assertThrows(
                IllegalStateException.class,
                () -> ApiUtil.setExampleResponse(nativeWebRequest, "application/json", "{}"),
                "Expected IllegalStateException due to null HttpServletResponse, but it was not thrown.");

        Assertions.assertEquals(
                "HttpServletResponse is null",
                exception.getMessage(),
                "The exception message does not match the expected message.");
    }

    /**
     * Tests that a {@code ResponseProcessingException} is thrown when an {@link IOException} occurs.
     *
     * <p>This test simulates an I/O error while writing to the response and verifies that
     * {@link ApiUtil.ResponseProcessingException} is thrown with the expected message.
     */
    @Test
    void testSetExampleResponseIOException() throws IOException {
        Mockito.when(httpServletResponse.getWriter()).thenThrow(new IOException("Test IO Exception"));

        ApiUtil.ResponseProcessingException exception = Assertions.assertThrows(
                ApiUtil.ResponseProcessingException.class,
                () -> ApiUtil.setExampleResponse(nativeWebRequest, "application/json", "{}"),
                "Expected ResponseProcessingException to be thrown, but it was not.");

        Assertions.assertEquals(
                "Error writing response",
                exception.getMessage(),
                "The exception message does not match the expected message.");
        Assertions.assertNotNull(exception.getCause(), "The cause of the exception should not be null.");
        Assertions.assertInstanceOf(
                IOException.class, exception.getCause(), "The cause of the exception should be of type IOException.");
        Assertions.assertEquals(
                "Test IO Exception",
                exception.getCause().getMessage(),
                "The cause message does not match the expected message.");
    }
}
