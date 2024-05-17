package com.addressbook.app;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.DisabledIfSystemProperties;

import static org.junit.jupiter.api.Assertions.*;

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
            String testEmail = "test@test.test";

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
                testContact = new Contact(testName, testPhone, testEmail);
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
                assertThrows(IllegalArgumentException.class, () -> new Contact(testName, testPhone, testEmail));
            }

            @Test
            @DisplayName("Test that passing an empty string as the name throws IllegalArgumentException")
            void nullStringThrowsIllegalArgumentException() {
                // Arrange
                testName = null;
                // Act
                // Assert
                assertThrows(IllegalArgumentException.class, () -> new Contact(testName, testPhone, testEmail));
            }

        }

        @Nested
        @DisplayName("US-3: I want my contact to have a phone number")
        class US3ContactTests{

            Contact testContact;
            String testPhone;

            String testName = "placeholder";
            String testEmail = "test@test.test";

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
                testContact = new Contact(testName, testPhone, testEmail);
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
                assertThrows(IllegalArgumentException.class, () -> new Contact(testName, testPhone, testEmail));
            }

            @Test
            @DisplayName("Test that passing a string with alphabetical characters throws IllegalArgumentException")
            void alphabeticStringThrowsIllegalArgumentException() {
                // Arrange
                testPhone = "testtesttes";
                // Act
                // Assert
                assertThrows(IllegalArgumentException.class, () -> new Contact(testName, testPhone, testEmail));
            }

            @Test
            @DisplayName("Test that passing a string with punctuation characters throws IllegalArgumentException")
            void punctuationStringThrowsIllegalArgumentException() {
                // Arrange
                testPhone = "!£$%^&*()[]";
                // Act
                // Assert
                assertThrows(IllegalArgumentException.class, () -> new Contact(testName, testPhone, testEmail));
            }

            @Test
            @DisplayName("Test that passing a string with not exactly 11 non-whitespace characters throws IllegalArgumentException")
            void notExactlyElevenNonWhitespaceCharsStringThrowsIllegalArgumentException() {
                // Arrange
                testPhone = "0712345678";
                // Act
                // Assert
                assertThrows(IllegalArgumentException.class, () -> new Contact(testName, testPhone, testEmail));
            }

            @Test
            @DisplayName("Test that passing a string with exactly 11 non-whitespace characters but also non-whitespace doesn't throw IllegalArgumentException")
            void exactlyElevenNonWhitespaceCharsAndWhitespaceStringDoesNotThrowIllegalArgumentException() {
                // Arrange
                testPhone = "07 123 456 789";
                // Act
                // Assert
                assertDoesNotThrow(() -> new Contact(testName, testPhone, testEmail));
            }

            @Test
            @DisplayName("Test that passing a string not starting 07 throws IllegalArgumentException")
            void notStartingWith07ThrowsIllegalArgumentException() {
                // Arrange
                testPhone = "12345678901";
                // Act
                // Assert
                assertThrows(IllegalArgumentException.class, () -> new Contact(testName, testPhone, testEmail));
            }

        }

        @Nested
        @DisplayName("US-4: I want my contact to have an email address")
        class US4ContactTests{

            private String testName = "placeholder";
            private String testPhone = "07123456789";

            private Contact testContact;
            private String testEmail;

            @AfterEach
            void tearDown() {
                testName = null;
                testContact = null;
            }

            @Test
            @DisplayName("Test that when I pass a valid email address into the contact, the resulting contact has the same")
            void checkValidEmailIsAssignedCorrectly() {
                // Arrange
                testEmail = "test@test.test";
                // Act
                testContact = new Contact(testName, testPhone, testEmail);
                // Assert
                assertEquals(testEmail, testContact.getEmail());
            }
        }
    }
}
