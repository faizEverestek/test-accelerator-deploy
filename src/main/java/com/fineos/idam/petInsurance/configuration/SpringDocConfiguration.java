/*---------------------------------------------------------------------------------------------
FINEOS

(c) Copyright FINEOS.
ALL RIGHTS RESERVED

---------------------------------------------------------------------------------------------*/
package com.fineos.idam.petInsurance.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for SpringDoc.
 *
 * <p>
 * If you are subclassing this class, be sure to invoke the {@code apiInfo()}
 * method safely, ensuring proper usage of the OpenAPI configuration.
 */
@Configuration
public class SpringDocConfiguration {

    /**
     * Provides the OpenAPI configuration for the application.
     *
     * <p>
     * This method can be overridden in subclasses to provide custom OpenAPI
     * configurations. Ensure that any overridden method retains the necessary
     * configuration settings required for the OpenAPI documentation to function
     * properly.
     * </p>
     *
     * @return the OpenAPI object containing the configuration.
     */
    /* package */ @Bean(name = "com.fineos.idam.petInsurance.configuration.SpringDocConfiguration.apiInfo")
    OpenAPI apiInfo() {
        return new OpenAPI().info(new Info().title("Custom APIs")
                .description("Customs APIs developed as micro-service extension.").version("1.0"));
    }
}
