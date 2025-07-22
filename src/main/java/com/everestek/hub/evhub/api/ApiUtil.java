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
import org.springframework.web.context.request.NativeWebRequest;

/**
 * Utility class for API-related functionality.
 *
 * <p>This class provides helper methods for handling API responses and includes nested exception classes.
 */
public final class ApiUtil {

    /**
     * Private constructor to prevent instantiation of this utility class.
     *
     * <p>Calling this constructor will throw an {@link UnsupportedOperationException}.
     */
    private ApiUtil() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    /** Exception thrown when there is an error processing the HTTP response. */
    public static class ResponseProcessingException extends RuntimeException {

        /** The serialization version UID. */
        private static final long serialVersionUID = 6266371315601252655L;

        /**
         * Constructs a new {@code ResponseProcessingException} with the specified detail message and cause.
         *
         * @param message The detail message, which is saved for later retrieval by the {@link #getMessage()} method
         * @param cause The cause of the exception, which is saved for later retrieval by the {@link #getCause()} method
         */
        public ResponseProcessingException(final String message, final Throwable cause) {
            super(message, cause);
        }
    }

    /**
     * Sets an example response in the provided {@link NativeWebRequest}.
     *
     * @param req The {@link NativeWebRequest} from which to obtain the {@link HttpServletResponse}
     * @param contentType The content type to be set in the response header
     * @param example The example content to be written to the response
     * @throws IllegalStateException if the {@link HttpServletResponse} is null
     * @throws ResponseProcessingException if an {@link IOException} occurs while writing to the response
     */
    public static void setExampleResponse(final NativeWebRequest req, final String contentType, final String example) {
        try {
            final HttpServletResponse res = req.getNativeResponse(HttpServletResponse.class);
            if (res != null) {
                res.setCharacterEncoding("UTF-8");
                res.addHeader("Content-Type", contentType);
                res.getWriter().print(example);
            } else {
                throw new IllegalStateException("HttpServletResponse is null");
            }
        } catch (IOException e) {
            throw new ResponseProcessingException("Error writing response", e);
        }
    }
}
