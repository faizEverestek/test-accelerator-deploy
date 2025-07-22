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

package com.customer.product.microservice.configuration;

import static org.springframework.http.HttpStatus.GATEWAY_TIMEOUT;
import static org.springframework.http.HttpStatus.SERVICE_UNAVAILABLE;

import feign.FeignException;
import feign.Response;
import feign.RetryableException;
import feign.codec.ErrorDecoder;
import org.springframework.http.HttpStatus;

/** Custom implementation of Feign's ErrorDecoder. */
public final class CustomErrorDecoder implements ErrorDecoder {

    /** Retry interval in milliseconds. */
    private static final long RETRY_INTERVAL_IN_MS = 50L;

    /**
     * Decodes the HTTP response and returns an appropriate exception.
     *
     * @param method the HTTP method being called
     * @param response the HTTP response from the Feign client
     * @return the exception to be thrown based on the response status
     */
    @Override
    public Exception decode(String method, Response response) {
        FeignException exception = FeignException.errorStatus(method, response);
        HttpStatus status = HttpStatus.resolve(response.status());

        if (status == GATEWAY_TIMEOUT || status == SERVICE_UNAVAILABLE) {
            return createRetryException(exception, response);
        } else {
            return exception;
        }
    }

    /**
     * Creates a retryable exception for retryable error statuses.
     *
     * @param exception the original FeignException
     * @param response the HTTP response
     * @return a RetryableException for retryable statuses (503, 504)
     */
    private FeignException createRetryException(FeignException exception, Response response) {
        return new RetryableException(
                response.status(),
                exception.getMessage(),
                response.request().httpMethod(),
                exception,
                RETRY_INTERVAL_IN_MS,
                response.request());
    }
}
