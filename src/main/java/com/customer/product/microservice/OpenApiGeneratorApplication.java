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

package com.customer.product.microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FullyQualifiedAnnotationBeanNameGenerator;

/**
 * The main entry point for the OpenAPI Generator Application.
 *
 * <p>This class initializes and configures the Spring Boot application for CUSTOMER. It includes configuration for
 * component scanning, enabling Feign clients, and Hystrix for fault tolerance. It uses fully qualified annotation bean
 * name generation for resolving bean conflicts.
 *
 * <ul>
 *   <li>@SpringBootApplication: Marks this as a Spring Boot application.
 *   <li>@ComponentScan: Specifies the base packages to scan for Spring components.
 *   <li>@EnableFeignClients: Enables Feign clients for declarative REST client implementation.
 *   <li>@EnableHystrix: Enables Hystrix for circuit breaker and fault tolerance functionality.
 * </ul>
 */
@SpringBootApplication(nameGenerator = FullyQualifiedAnnotationBeanNameGenerator.class)
@ComponentScan(
        basePackages = {
            "com.customer.product.microservice",
            "com.customer.product.microservice",
            "com.customer.product.microservice.api",
            "com.customer.product.microservice.configuration"
        },
        nameGenerator = FullyQualifiedAnnotationBeanNameGenerator.class)
@EnableFeignClients
@EnableHystrix
@SuppressWarnings({"checkstyle:hideutilityclassconstructor", "PMD.UseUtilityClass"})
public class OpenApiGeneratorApplication extends SpringBootServletInitializer {

    /**
     * Main method to start the Spring Boot application.
     *
     * <p>This method uses the SpringApplication.run() method to launch the application. It serves as the entry point
     * for the Spring Boot application.
     *
     * @param args command-line arguments passed to the application
     */
    public static void main(final String[] args) {
        setApplicationProfile();
        SpringApplication.run(OpenApiGeneratorApplication.class, args);
    }

    /** Sets application profile. eg:- String profile = System.getProperty("spring.profiles.active"); */
    public static void setApplicationProfile() {
        // Empty method
    }

    /**
     * Configures the Spring Boot application when deployed in a servlet container. This method overrides the
     * `configure` method of `SpringBootServletInitializer` to set the active Spring profile before the application is
     * launched. After setting the profile, it proceeds with the normal configuration using the
     * `super.configure(builder)` method.
     *
     * @param builder the SpringApplicationBuilder used to configure the application
     * @return the configured SpringApplicationBuilder
     */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        setApplicationProfile();
        return super.configure(builder);
    }
}
