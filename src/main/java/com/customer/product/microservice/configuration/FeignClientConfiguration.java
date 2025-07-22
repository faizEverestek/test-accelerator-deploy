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

import feign.Retryer;
import feign.codec.ErrorDecoder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/** Configures the Feign client settings, including retry and error decoding behavior. */
@Configuration
public class FeignClientConfiguration {

    /** Feign client connection timeout in milliseconds. */
    @Value("${feign.client.config.default.connectTimeout}")
    private Long connectTimeout;

    /** Maximum number of retry attempts. */
    @Value("${retry.maxAttempts}")
    private Integer maxAttempts;

    /** Feign client delay period in milliseconds. */
    @Value("${feign.client.config.default.delayPeriod}")
    private Integer delayPeriod;

    /**
     * Provides the custom error decoder bean for handling Feign client errors.
     *
     * @return the custom error decoder
     */
    @Bean
    ErrorDecoder errorDecoder() {
        return new CustomErrorDecoder();
    }

    /**
     * Configures the Feign client's retry policy.
     *
     * @return the configured retryer with custom timeout and max attempts
     */
    @Bean
    Retryer retryer() {
        return new Retryer.Default(delayPeriod, connectTimeout, maxAttempts);
    }
}
