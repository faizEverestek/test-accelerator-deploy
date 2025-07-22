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

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.test.context.SpringBootTest;

/** Test class for the OpenApiGeneratorApplication. It ensures that the Spring application context loads properly. */
@SpringBootTest
class OpenApiGeneratorApplicationTests {

    /**
     * Test method to verify that the Spring application context loads successfully. This is a basic sanity check to
     * ensure the application configuration is correct.
     */
    @Test
    void contextLoads() {
        Assertions.assertDoesNotThrow(
                () -> SpringApplication.run(OpenApiGeneratorApplication.class),
                "Spring application context should load without throwing exceptions.");
    }

    /**
     * This test verifies that the {@code SpringApplication.run()} method is called when the main method of
     * {@link OpenApiGeneratorApplication} is executed.
     */
    @Test
    void testSpringApplicationRunCalled() {
        try (MockedStatic<SpringApplication> mockedSpringApplication = Mockito.mockStatic(SpringApplication.class)) {

            String[] args = {};
            mockedSpringApplication
                    .when(() -> SpringApplication.run(OpenApiGeneratorApplication.class, args))
                    .thenReturn(null);

            // Call the main method
            OpenApiGeneratorApplication.main(args);

            // Verify SpringApplication.run() was called
            mockedSpringApplication.verify(() -> SpringApplication.run(OpenApiGeneratorApplication.class, args));
        }
    }

    /**
     * This test verifies that the {@code setApplicationProfile()} method is called when the main method of
     * {@link OpenApiGeneratorApplication} is executed.
     *
     * <p>It ensures that the necessary application profile setup is invoked before {@code SpringApplication.run()} is
     * executed.
     */
    @Test
    void testSetApplicationProfileCalled() {
        try (MockedStatic<SpringApplication> mockedSpringApplication = Mockito.mockStatic(SpringApplication.class);
                MockedStatic<OpenApiGeneratorApplication> mockedApplication =
                        Mockito.mockStatic(OpenApiGeneratorApplication.class, Mockito.CALLS_REAL_METHODS)) {

            String[] args = {};
            mockedSpringApplication
                    .when(() -> SpringApplication.run(OpenApiGeneratorApplication.class, args))
                    .thenReturn(null);

            // Call the main method
            OpenApiGeneratorApplication.main(args);

            // Verify setApplicationProfile() was called
            mockedApplication.verify(OpenApiGeneratorApplication::setApplicationProfile);
        }
    }

    /** Test case for the configure method to ensure the application is configured correctly. */
    @Test
    void testConfigure() {
        OpenApiGeneratorApplication application = new OpenApiGeneratorApplication();
        SpringApplicationBuilder builder = Mockito.mock(SpringApplicationBuilder.class);
        SpringApplicationBuilder result = application.configure(builder);

        Assertions.assertNotNull(result, "SpringApplicationBuilder should not be null");
    }
}
