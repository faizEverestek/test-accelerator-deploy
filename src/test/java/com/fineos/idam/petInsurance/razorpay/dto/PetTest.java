/*---------------------------------------------------------------------------------------------
FINEOS

(c) Copyright FINEOS.
ALL RIGHTS RESERVED

---------------------------------------------------------------------------------------------*/
package com.fineos.idam.petInsurance.razorpay.dto;

import com.fineos.idam.petInsurance.commons.AssertionUtilDto;
import com.fineos.idam.petInsurance.commons.TestDataGenerator;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import java.util.Set;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/** Test class for testing the functionality of {@link Pet} class. */
class PetTest {

    /** Object 1 for testing. */
    private Pet obj1;

    /** Object 2 for testing. */
    private Pet obj2;

    /** Object 3 for testing. */
    private Pet obj3;

    /** Set up the required test data before starting the test case. */
    @BeforeEach
    void setUpTestData() {
        obj1 = this.createTestObject();
        obj2 = this.createTestObject();
        obj3 = new Pet();
    }

    /**
     * Test for the getters and setters of the {@link Pet} class. This ensures that the getters and setters are
     * functioning correctly.
     */
    @Test
    void testGettersAndSetters() {
        // Verify Getters and Setters
        AssertionUtilDto.verifySetterGetters(obj1, obj2);
    }

    /**
     * Test for the equals and hashCode methods of the {@link Pet} class. This test verifies that the equals and
     * hashCode methods work as expected for both equal and non-equal objects.
     */
    @Test
    void testHashCodeAndEquals() {
        // Verify Equals and Hashcode
        AssertionUtilDto.verifyHashAndEquals(obj1, obj2, obj3);
    }

    /**
     * Test for the toString method of the {@link Pet} class. This ensures that the toString method returns the expected
     * string representation.
     */
    @Test
    void testToString() {
        // Verify string representation of the object
        String expected = AssertionUtilDto.getToString(obj1);
        Assertions.assertEquals(expected, obj1.toString(), "Expected and actual string representation should match");
    }

    /**
     * Test for the validation constraints of the {@link Pet} class. This test ensures that the validation annotations
     * on the fields of the DTO are working correctly.
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
     * <p>This method initializes a {@link Pet} object with predefined values to be used in unit tests.
     *
     * @return A {@link Pet} object with sample values.
     */
    private Pet createTestObject() {
        return TestDataGenerator.createTestInstance(Pet.class);
    }
}
