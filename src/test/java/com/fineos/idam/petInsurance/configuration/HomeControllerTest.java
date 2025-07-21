/*---------------------------------------------------------------------------------------------
FINEOS

(c) Copyright FINEOS.
ALL RIGHTS RESERVED

---------------------------------------------------------------------------------------------*/
package com.fineos.idam.petInsurance.configuration;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/**
 * Unit test class for {@link HomeController}.
 *
 * <p>This class tests the redirection behavior of the {@code HomeController}. It verifies that requests to the root URL
 * ("/") are correctly redirected to the Swagger UI documentation page.
 */
@SuppressWarnings({"PMD.JUnitTestsShouldIncludeAssert", "PMD.LawOfDemeter", "PMD.JUnitTestContainsTooManyAsserts"})
class HomeControllerTest {

    /** MockMvc instance used to simulate HTTP requests and verify responses. */
    private MockMvc mockMvc;

    /**
     * Sets up the test environment before each test case.
     *
     * <p>Initializes {@code MockMvc} with an instance of {@code HomeController} to enable unit testing of controller
     * methods without starting a full Spring application.
     */
    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(new HomeController()).build();
    }

    /**
     * Tests the redirection behavior of the {@code index} method in {@link HomeController}.
     *
     * <p>Ensures that a GET request to the root URL ("/") results in an HTTP 302 redirect to the Swagger UI
     * documentation page ("swagger-ui.html").
     *
     * @throws Exception if an error occurs during the request execution.
     */
    @Test
    void testIndexRedirectsToSwaggerUI() throws Exception {
        ////////////////////////////////////////////////////////////////////////
        // 1. Test data setup
        ////////////////////////////////////////////////////////////////////////
        final int expectedStatus = HttpStatus.FOUND.value();
        final String expectedRedirectUrl = "swagger-ui.html";

        ////////////////////////////////////////////////////////////////////////
        // 2. Call method that needs to be tested
        ////////////////////////////////////////////////////////////////////////
        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl(expectedRedirectUrl))
                .andReturn()
                .getResponse();

        ////////////////////////////////////////////////////////////////////////
        // 3. Verify result of the method to be tested
        ////////////////////////////////////////////////////////////////////////

        // Verify status
        Assertions.assertEquals(expectedStatus, response.getStatus(), "HTTP status not matched");

        // Verify redirection URL
        String actualRedirectUrl = response.getRedirectedUrl();
        Assertions.assertNotNull(actualRedirectUrl, "Redirect URL should not be null");
        Assertions.assertEquals(expectedRedirectUrl, actualRedirectUrl, "Unexpected redirect URL");
    }
}
