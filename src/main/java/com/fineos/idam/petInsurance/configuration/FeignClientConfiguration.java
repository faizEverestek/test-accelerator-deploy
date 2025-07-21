/*---------------------------------------------------------------------------------------------
FINEOS

(c) Copyright FINEOS.
ALL RIGHTS RESERVED

---------------------------------------------------------------------------------------------*/
package com.fineos.idam.petInsurance.configuration;

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
