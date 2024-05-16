package com.addressbook.app;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.DisabledIfSystemProperties;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ContactTest {

    @Nested
    @DisplayName("Contact Tests")
    class ContactTests{

        private Contact testContact;
        private String testName;

        @AfterEach
        void tearDown() {
            testContact = null;
            testName = null;
        }

        @Nested
        @DisplayName("US-2: I want my contact to have a name record")
        class US2ContactTests{

            @Test
            @DisplayName("Test that passing a name into the Contact constructor results in the same name being assigned")
            void testCorrectNameIsAssigned() {
                // Arrange
                testName = "test";
                // Act
                testContact = new Contact(testName);
                // Assert
                assertEquals(testContact.getName(), testName);
            }

            @Test
            @DisplayName("Test that passing an empty string as the name throws IllegalArgumentException")
            void emptyStringThrowsIllegalArgumentException() {
                // Arrange
                testName = "";
                // Act
                // Assert
                assertThrows(IllegalArgumentException.class, () -> new Contact(testName));
            }

        }
    }
}
