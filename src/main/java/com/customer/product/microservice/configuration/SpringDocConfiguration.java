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

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for SpringDoc.
 *
 * <p>If you are subclassing this class, be sure to invoke the {@code apiInfo()} method safely, ensuring proper usage of
 * the OpenAPI configuration.
 */
@Configuration
public class SpringDocConfiguration {

    /**
     * Provides the OpenAPI configuration for the application.
     *
     * <p>This method can be overridden in subclasses to provide custom OpenAPI configurations. Ensure that any
     * overridden method retains the necessary configuration settings required for the OpenAPI documentation to function
     * properly.
     *
     * @return the OpenAPI object containing the configuration.
     */
    /* package */ @Bean(name = "com.customer.product.microservice.configuration.SpringDocConfiguration.apiInfo")
    OpenAPI apiInfo() {
        return new OpenAPI()
                .info(new Info()
                        .title("Custom APIs")
                        .description("Customs APIs developed as micro-service extension.")
                        .version("1.0"));
    }
}
