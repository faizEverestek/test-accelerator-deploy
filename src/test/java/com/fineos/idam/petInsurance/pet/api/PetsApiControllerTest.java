/*---------------------------------------------------------------------------------------------
FINEOS

(c) Copyright FINEOS.
ALL RIGHTS RESERVED

---------------------------------------------------------------------------------------------*/
package com.fineos.idam.petInsurance.pet.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

/**
 * Test class forControllerTest controller layer testing.
 */
@SuppressWarnings("checkstyle:MethodName")
@WebMvcTest(controllers = PetsApi.class)
class PetsApiControllerTest {

    /**
     * MockMvc instance for performing HTTP requests in unit tests.
     *
     * <p>
     * This instance is automatically injected and allows testing of Spring MVC
     * controllers without starting a full web server.
     * </p>
     */
    @Autowired
    private MockMvc mockMvc;

    /**
     * ObjectMapper is used for converting Java objects to and from JSON. It is
     * automatically injected by Spring's dependency injection.
     */
    private final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Template test case for testing the createPets method using the MockMVC
     * framework.
     *
     * <p>
     * <b>Instructions:</b>
     * </p>
     * <ul>
     * <li>Refer to the commented code inside the method body for a sample test
     * structure.</li>
     * <li>Uncomment and adapt it to your controller logic and expected
     * outcomes.</li>
     * <li>Update variable names, assertions, HTTP method, and URL accordingly.</li>
     * <li>Update this javadoc according to the test method description.</li>
     * </ul>
     */
    @Test
    void test_createPets() throws Exception {

        String defaultBasePath = "/v1";

        // /////////////////////////////////////////////////////////////////////////
        // // EXAMPLE METHOD BODY
        // /////////////////////////////////////////////////////////////////////////

        // ////////////////////////////////////////////////////////////////////////
        // // 1. Test data setup
        // ////////////////////////////////////////////////////////////////////////
        // RequestDTO requestDTO = new RequestDTO();
        // Mockito.when(businessService.testMethod(requestDTO)).thenReturn(requestDTO);

        // ////////////////////////////////////////////////////////////////////////
        // // 2. Call method that needs to be tested
        // ////////////////////////////////////////////////////////////////////////
        // MockHttpServletResponse response = mockMvc
        // .perform(MockMvcRequestBuilders.post(defaultBasePath + "/pets")
        // .contentType(MediaType.APPLICATION_JSON)
        // .content(objectMapper.writeValueAsString(requestDTO)))
        // .andExpect(MockMvcResultMatchers.status().isNotImplemented()).andReturn().getResponse();

        // ////////////////////////////////////////////////////////////////////////
        // // 3. Verify result of the method to be tested
        // ////////////////////////////////////////////////////////////////////////

        // // Verify status
        // Assertions.assertEquals(501, response.getStatus(), "HTTP status not
        // matched");

        // // Verify response headers
        // Map<String, List<String>> headers = response.getHeaderNames().stream()
        // .collect(Collectors.toMap(
        // h -> h,
        // response::getHeaders,
        // (existing, replacement) -> replacement,
        // TreeMap::new
        // ));
        // Map<String, List<String>> expectedHeaders =
        // TestHelper.getHeadersForTesting();
        // Assertions.assertEquals(expectedHeaders, headers);

        // // Verify response body
        // String responseContent = response.getContentAsString();
        // Request actual = objectMapper.readValue(responseContent, Request.class);
        // Assertions.assertEquals(request, actual, "Request details do not match
        // expected request");
    }

    /**
     * Template test case for testing the listPets method using the MockMVC
     * framework.
     *
     * <p>
     * <b>Instructions:</b>
     * </p>
     * <ul>
     * <li>Refer to the commented code inside the method body for a sample test
     * structure.</li>
     * <li>Uncomment and adapt it to your controller logic and expected
     * outcomes.</li>
     * <li>Update variable names, assertions, HTTP method, and URL accordingly.</li>
     * <li>Update this javadoc according to the test method description.</li>
     * </ul>
     */
    @Test
    void test_listPets() throws Exception {

        String defaultBasePath = "/v1";

        // /////////////////////////////////////////////////////////////////////////
        // // EXAMPLE METHOD BODY
        // /////////////////////////////////////////////////////////////////////////

        // ////////////////////////////////////////////////////////////////////////
        // // 1. Test data setup
        // ////////////////////////////////////////////////////////////////////////
        // RequestDTO requestDTO = new RequestDTO();
        // Mockito.when(businessService.testMethod(requestDTO)).thenReturn(requestDTO);

        // ////////////////////////////////////////////////////////////////////////
        // // 2. Call method that needs to be tested
        // ////////////////////////////////////////////////////////////////////////
        // MockHttpServletResponse response = mockMvc
        // .perform(MockMvcRequestBuilders.post(defaultBasePath + "/pets")
        // .contentType(MediaType.APPLICATION_JSON)
        // .content(objectMapper.writeValueAsString(requestDTO)))
        // .andExpect(MockMvcResultMatchers.status().isNotImplemented()).andReturn().getResponse();

        // ////////////////////////////////////////////////////////////////////////
        // // 3. Verify result of the method to be tested
        // ////////////////////////////////////////////////////////////////////////

        // // Verify status
        // Assertions.assertEquals(501, response.getStatus(), "HTTP status not
        // matched");

        // // Verify response headers
        // Map<String, List<String>> headers = response.getHeaderNames().stream()
        // .collect(Collectors.toMap(
        // h -> h,
        // response::getHeaders,
        // (existing, replacement) -> replacement,
        // TreeMap::new
        // ));
        // Map<String, List<String>> expectedHeaders =
        // TestHelper.getHeadersForTesting();
        // Assertions.assertEquals(expectedHeaders, headers);

        // // Verify response body
        // String responseContent = response.getContentAsString();
        // Request actual = objectMapper.readValue(responseContent, Request.class);
        // Assertions.assertEquals(request, actual, "Request details do not match
        // expected request");
    }

    /**
     * Template test case for testing the showPetById method using the MockMVC
     * framework.
     *
     * <p>
     * <b>Instructions:</b>
     * </p>
     * <ul>
     * <li>Refer to the commented code inside the method body for a sample test
     * structure.</li>
     * <li>Uncomment and adapt it to your controller logic and expected
     * outcomes.</li>
     * <li>Update variable names, assertions, HTTP method, and URL accordingly.</li>
     * <li>Update this javadoc according to the test method description.</li>
     * </ul>
     */
    @Test
    void test_showPetById() throws Exception {

        String defaultBasePath = "/v1";

        // /////////////////////////////////////////////////////////////////////////
        // // EXAMPLE METHOD BODY
        // /////////////////////////////////////////////////////////////////////////

        // ////////////////////////////////////////////////////////////////////////
        // // 1. Test data setup
        // ////////////////////////////////////////////////////////////////////////
        // RequestDTO requestDTO = new RequestDTO();
        // Mockito.when(businessService.testMethod(requestDTO)).thenReturn(requestDTO);

        // ////////////////////////////////////////////////////////////////////////
        // // 2. Call method that needs to be tested
        // ////////////////////////////////////////////////////////////////////////
        // MockHttpServletResponse response = mockMvc
        // .perform(MockMvcRequestBuilders.post(defaultBasePath + "/pets/{petId}")
        // .contentType(MediaType.APPLICATION_JSON)
        // .content(objectMapper.writeValueAsString(requestDTO)))
        // .andExpect(MockMvcResultMatchers.status().isNotImplemented()).andReturn().getResponse();

        // ////////////////////////////////////////////////////////////////////////
        // // 3. Verify result of the method to be tested
        // ////////////////////////////////////////////////////////////////////////

        // // Verify status
        // Assertions.assertEquals(501, response.getStatus(), "HTTP status not
        // matched");

        // // Verify response headers
        // Map<String, List<String>> headers = response.getHeaderNames().stream()
        // .collect(Collectors.toMap(
        // h -> h,
        // response::getHeaders,
        // (existing, replacement) -> replacement,
        // TreeMap::new
        // ));
        // Map<String, List<String>> expectedHeaders =
        // TestHelper.getHeadersForTesting();
        // Assertions.assertEquals(expectedHeaders, headers);

        // // Verify response body
        // String responseContent = response.getContentAsString();
        // Request actual = objectMapper.readValue(responseContent, Request.class);
        // Assertions.assertEquals(request, actual, "Request details do not match
        // expected request");
    }

}
