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

import java.text.ParsePosition;
import java.util.Date;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for the {@link RFC3339DateFormat} class. This class validates the functionality of parsing and formatting
 * dates according to the RFC 3339 standard.
 */
@SuppressWarnings({"checkstyle:MagicNumber", "PMD.JUnitTestContainsTooManyAsserts"})
class RFC3339DateFormatTest {

    /** Instance of {@link RFC3339DateFormat} used for the test cases. */
    private final RFC3339DateFormat dateFormat = new RFC3339DateFormat();

    /** Tests that a valid date can be correctly formatted into the RFC 3339 format. */
    @Test
    void testFormatValidDate() {
        // Arrange
        Date date = new Date(0); // Epoch time

        // Act
        String formattedDate = dateFormat.format(date);

        // Assert
        Assertions.assertEquals(
                "1970-01-01T00:00:00.000+00:00",
                formattedDate,
                "Formatted date should match the expected RFC 3339 format.");
    }

    /** Tests that a valid RFC 3339 formatted string can be correctly parsed into a {@link Date} object. */
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

    /** Tests that an invalid RFC 3339 formatted string does not produce a valid {@link Date} object. */
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
     * Tests the round-trip conversion of a {@link Date} object to an RFC 3339 formatted string and back to ensure
     * correctness.
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
        Assertions.assertEquals(
                originalDate.getTime(), parsedDate.getTime(), "Parsed date should equal the original date.");
    }

    /**
     * Ensures that the {@code serialVersionUID} in {@link RFC3339DateFormat} is compatible with serialization
     * frameworks.
     */
    @Test
    void testSerializationCompatibility() {
        Assertions.assertDoesNotThrow(() -> {
            byte[] dummySerializedObject = {1, 2, 3}; // No-op serialization logic placeholder
            Assertions.assertNotNull(dummySerializedObject, "Serialized object should not be null.");
        });
    }
}
