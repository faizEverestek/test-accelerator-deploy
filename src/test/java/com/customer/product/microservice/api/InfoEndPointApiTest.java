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

package com.customer.product.microservice.api;

import jakarta.servlet.ServletContext;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.actuate.info.Info;

/** Unit test class for {@link InfoEndPointApi}. */
@SuppressWarnings("PMD.JUnitTestContainsTooManyAsserts")
class InfoEndPointApiTest {

    /** Mock ServletContext used to simulate application context in tests. */
    private ServletContext servletContext;

    /** Instance of InfoEndPointApi under test. */
    private InfoEndPointApi infoEndPointApi;

    /**
     * Sets up mock objects before each test execution.
     *
     * <p>This method initializes the mock {@link ServletContext} and the {@link InfoEndPointApi} instance.
     */
    @BeforeEach
    void setUp() {
        servletContext = Mockito.mock(ServletContext.class);
        infoEndPointApi = new InfoEndPointApi(servletContext);
    }

    /**
     * Tests that the {@code contribute} method correctly extracts values from a valid MANIFEST file.
     *
     * @throws Exception If an error occurs during the test execution.
     */
    @Test
    void testContributeSuccess() throws Exception {
        String manifestContent = """
                Manifest-Version: 1.0
                Implementation-Version: 1.2.3
                Implementation-Title: microservice
                
                """;

        InputStream manifestStream = new ByteArrayInputStream(manifestContent.getBytes(StandardCharsets.UTF_8));

        Mockito.when(servletContext.getResourceAsStream("META-INF/MANIFEST.MF")).thenReturn(manifestStream);

        Info.Builder builder = new Info.Builder();
        infoEndPointApi.contribute(builder);
        Info info = builder.build();

        Assertions.assertEquals("1.2.3", info.get("version"), "Expected version does not match.");
        Assertions.assertEquals("microservice", info.get("serviceName"), "Expected service name does not match.");
        Assertions.assertEquals(
                "The microservice micro-service for CUSTOMER.",
                info.get("description"),
                "Expected description does not match.");
    }

    /** Tests that an {@code IllegalStateException} is thrown when the MANIFEST file is missing. */
    @Test
    void testContributeMissingManifest() {
        Mockito.when(servletContext.getResourceAsStream("META-INF/MANIFEST.MF")).thenReturn(null);

        Info.Builder builder = new Info.Builder();
        IllegalStateException exception = Assertions.assertThrows(
                IllegalStateException.class,
                () -> infoEndPointApi.contribute(builder),
                "Expected IllegalStateException when MANIFEST.MF is missing");

        Assertions.assertEquals(
                "MANIFEST.MF is missing in the war",
                exception.getMessage(),
                "Exception message did not match the expected value.");
    }
}
