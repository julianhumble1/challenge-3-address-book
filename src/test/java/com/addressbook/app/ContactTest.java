package com.addressbook.app;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.DisabledIfSystemProperties;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ContactTest {

    @Nested
    @DisplayName("Contact Tests")
    class ContactTests{

        @Nested
        @DisplayName("US-2: I want my contact to have a name record")
        class US2ContactTests{

            private Contact testContact;
            private String testName;

            String testPhone = "07123456789";

            @AfterEach
            void tearDown() {
                testContact = null;
                testName = null;
            }

            @Test
            @DisplayName("Test that passing a name into the Contact constructor results in the same name being assigned")
            void testCorrectNameIsAssigned() {
                // Arrange
                testName = "test";
                // Act
                testContact = new Contact(testName, testPhone);
                // Assert
                assertEquals(testName, testContact.getName());
            }

            @Test
            @DisplayName("Test that passing an empty string as the name throws IllegalArgumentException")
            void emptyStringThrowsIllegalArgumentException() {
                // Arrange
                testName = "";
                // Act
                // Assert
                assertThrows(IllegalArgumentException.class, () -> new Contact(testName, testPhone));
            }

            @Test
            @DisplayName("Test that passing an empty string as the name throws IllegalArgumentException")
            void nullStringThrowsIllegalArgumentException() {
                // Arrange
                testName = null;
                // Act
                // Assert
                assertThrows(IllegalArgumentException.class, () -> new Contact(testName, testPhone));
            }

        }

        @Nested
        @DisplayName("US-3: I want my contact to have a phone number")
        class US3ContactTests{

            Contact testContact;
            String testPhone;

            String testName = "placeholder";

            @BeforeEach
            void setUp() {
                testPhone = null;
                testContact = null;
            }

            @Test
            @DisplayName("Test that passing a valid phone into the constructor results in the same number being assigned")
            void testCorrectPhoneIsAssigned() {
                // Arrange
                testPhone = "07123456789";
                // Act
                testContact = new Contact(testName, testPhone);
                // Assert
                assertEquals(testPhone, testContact.getPhone());
            }

            @Test
            @DisplayName("Test that passing an empty string as the phone throws IllegalArgumentException")
            void emptyStringThrowsIllegalArgumentException() {
                // Arrange
                testPhone = "";
                // Act
                // Assert
                assertThrows(IllegalArgumentException.class, () -> new Contact(testName, testPhone));
            }

            @Test
            @DisplayName("Test that passing a string with alphabetical characters throw IllegalArgumentException")
            void alphabeticStringThrowsIllegalArgumentException() {
                // Arrange
                testPhone = "testtesttes";
                // Act
                // Assert
                assertThrows(IllegalArgumentException.class, () -> new Contact(testName, testPhone));
            }

            @Test
            @DisplayName("Test that passing a string with punctuation characters throw IllegalArgumentException")
            void punctuationStringThrowsIllegalArgumentException() {
                // Arrange
                testPhone = "!£$%^&*()[]";
                // Act
                // Assert
                assertThrows(IllegalArgumentException.class, () -> new Contact(testName, testPhone));
            }

        }
    }
}
