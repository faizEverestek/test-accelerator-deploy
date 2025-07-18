/*---------------------------------------------------------------------------------------------
FINEOS

(c) Copyright FINEOS.
ALL RIGHTS RESERVED

---------------------------------------------------------------------------------------------*/
package com.fineos.idam.petInsurance.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.security.SecurityScheme;

/**
* Configuration class for SpringDoc.
*
* <p>If you are subclassing this class, be sure to invoke the {@code apiInfo()} method safely,
* ensuring proper usage of the OpenAPI configuration.
*/
@Configuration
public class SpringDocConfiguration {

    /**
     * Provides the OpenAPI configuration for the application.
     *
     * <p>This method can be overridden in subclasses to provide custom OpenAPI configurations.
     * Ensure that any overridden method retains the necessary configuration settings required
     * for the OpenAPI documentation to function properly.</p>
     *
     * @return the OpenAPI object containing the configuration.
     */
     /* package */ @Bean(name = "com.fineos.idam.petInsurance.configuration.SpringDocConfiguration.apiInfo")
    public OpenAPI apiInfo() {
        return new OpenAPI()
                .info(
                        new Info()
                                .title("Custom APIs")
                                .description("Customs APIs developed as micro-service extension.")
                                .version("1.0")
                )
        ;
    }
}
