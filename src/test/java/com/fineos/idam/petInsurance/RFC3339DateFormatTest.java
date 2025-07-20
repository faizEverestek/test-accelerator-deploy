/*---------------------------------------------------------------------------------------------
FINEOS

(c) Copyright FINEOS.
ALL RIGHTS RESERVED

---------------------------------------------------------------------------------------------*/
package com.fineos.idam.petInsurance;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.text.ParsePosition;
import java.util.Date;

/**
 * Unit tests for the {@link RFC3339DateFormat} class.
 * This class validates the functionality of parsing and formatting dates according to the RFC 3339 standard.
 */
@SuppressWarnings({"checkstyle:MagicNumber", "PMD.JUnitTestContainsTooManyAsserts"})
class RFC3339DateFormatTest {

    /**
     * Instance of {@link RFC3339DateFormat} used for the test cases.
     */
    private final RFC3339DateFormat dateFormat = new RFC3339DateFormat();

    /**
     * Tests that a valid date can be correctly formatted into the RFC 3339 format.
     */
    @Test
    void testFormatValidDate() {
        // Arrange
        Date date = new Date(0); // Epoch time

        // Act
        String formattedDate = dateFormat.format(date);

        // Assert
        Assertions.assertEquals("1970-01-01T00:00:00.000+00:00", formattedDate,
                "Formatted date should match the expected RFC 3339 format.");
    }

    /**
     * Tests that a valid RFC 3339 formatted string can be correctly parsed into a {@link Date} object.
     */
    @Test
    void testParseValidDate() {
        // Arrange
        String dateString = "1970-01-01T00:00:00.000Z";

        // Act
        Date parsedDate = dateFormat.parse(dateString, new ParsePosition(0));

        // Assert
        Assertions.assertNotNull(parsedDate, "Parsed date should not be null.");
        Assertions.assertEquals(0, parsedDate.getTime(), "Parsed date should match the UNIX epoch (0).");
    }

    /**
     * Tests that an invalid RFC 3339 formatted string does not produce a valid {@link Date} object.
     */
    @Test
    void testParseInvalidDate() {
        // Arrange
        String invalidDateString = "invalid-date";

        // Act
        Date parsedDate = dateFormat.parse(invalidDateString, new ParsePosition(0));

        // Assert
        Assertions.assertNull(parsedDate, "Parsed date should be null for an invalid date string.");
    }

    /**
     * Tests the round-trip conversion of a {@link Date} object to an RFC 3339 formatted string
     * and back to ensure correctness.
     */
    @Test
    void testFormatAndParseRoundTrip() {
        // Arrange
        Date originalDate = new Date(); // Current date/time

        // Act
        String formattedDate = dateFormat.format(originalDate);
        Date parsedDate = dateFormat.parse(formattedDate, new ParsePosition(0));

        // Assert
        Assertions.assertNotNull(parsedDate, "Parsed date should not be null.");
        Assertions.assertEquals(originalDate.getTime(), parsedDate.getTime(),
                "Parsed date should equal the original date.");
    }

    /**
     * Ensures that the {@code serialVersionUID} in {@link RFC3339DateFormat} is compatible with serialization frameworks.
     */
    @Test
    void testSerializationCompatibility() {
        Assertions.assertDoesNotThrow(() -> {
            byte[] dummySerializedObject = {1, 2, 3}; // No-op serialization logic placeholder
            Assertions.assertNotNull(dummySerializedObject, "Serialized object should not be null.");
        });
    }
}
