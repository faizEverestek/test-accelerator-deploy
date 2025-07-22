/*---------------------------------------------------------------------------------------------
FINEOS

(c) Copyright FINEOS.
ALL RIGHTS RESERVED

---------------------------------------------------------------------------------------------*/
package com.fineos.idam.petInsurance.razorpay.dto;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.AssertFalse;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Negative;
import jakarta.validation.constraints.NegativeOrZero;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import org.springframework.http.HttpStatus;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fineos.idam.petInsurance.commons.AssertionUtilDto;
import com.fineos.idam.petInsurance.commons.TestDataGenerator;

import java.util.Set;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.List;
import jakarta.annotation.Generated;

/**
 * Test class for testing the functionality of {@link Pet} class.
 */
class PetTest {

    /**
     * Object 1 for testing.
     */
    private Pet obj1;

    /**
     * Object 2 for testing.
     */
    private Pet obj2;

    /**
     * Object 3 for testing.
     */
    private Pet obj3;

    /**
     * Set up the required test data before starting the test case.
     */
    @BeforeEach
    void setUpTestData() {
        obj1 = this.createTestObject();
        obj2 = this.createTestObject();
        obj3 = new Pet();
    }

    /**
     * Test for the getters and setters of the {@link Pet} class.
     * This ensures that the getters and setters are functioning correctly.
     */
    @Test
    void testGettersAndSetters() {
        // Verify Getters and Setters
        AssertionUtilDto.verifySetterGetters(obj1, obj2);
    }

    /**
     * Test for the equals and hashCode methods of the {@link Pet} class.
     * This test verifies that the equals and hashCode methods work as expected for both
     * equal and non-equal objects.
     */
    @Test
    void testHashCodeAndEquals() {
        // Verify Equals and Hashcode
        AssertionUtilDto.verifyHashAndEquals(obj1, obj2, obj3);
    }

    /**
     * Test for the toString method of the {@link Pet} class.
     * This ensures that the toString method returns the expected string representation.
     */
    @Test
    void testToString() {
        // Verify string representation of the object
        String expected = AssertionUtilDto.getToString(obj1);
        Assertions.assertEquals(
                expected,
                obj1.toString(), "Expected and actual string representation should match");
    }

    /**
     * Test for the validation constraints of the {@link Pet} class.
     * This test ensures that the validation annotations on the fields of the DTO are working correctly.
     */
    @Test
    void testValidations() {
        try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
            Validator validator = factory.getValidator();
            Pet instance = new Pet();
            Set<ConstraintViolation<Pet>> violations = validator.validate(instance);
            Assertions.assertFalse(violations.isEmpty(), "Validation errors are expected");
        }
    }

    /**
     * Creates and returns a test instance of the {@link Pet} class.
     *
     * <p>This method initializes a {@link Pet} object with predefined values
     * to be used in unit tests.</p>
     *
     * @return A {@link Pet} object with sample values.
     */
    private Pet createTestObject() {
        return TestDataGenerator.createTestInstance(Pet.class);
    }
}

