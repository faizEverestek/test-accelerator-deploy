/*---------------------------------------------------------------------------------------------
FINEOS

(c) Copyright FINEOS.
ALL RIGHTS RESERVED

---------------------------------------------------------------------------------------------*/
package com.fineos.idam.petInsurance;

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
 * <p>This class initializes and configures the Spring Boot application for FINEOS. It includes configuration for
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
            "com.fineos.idam.petInsurance",
            "com.fineos.idam.petInsurance",
            "com.fineos.idam.petInsurance.api",
            "com.fineos.idam.petInsurance.configuration"
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
