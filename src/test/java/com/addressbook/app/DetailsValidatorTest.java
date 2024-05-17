package com.addressbook.app;

import com.addressbook.app.utils.DetailsValidator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DetailsValidatorTest {

    @Nested
    @DisplayName("Details Validator Tests")
    class DetailsValidatorTests{

        @Nested
        @DisplayName("US-2: I want my contact to have a name")
        class US2DetailsValidatorTests{

            @Test
            @DisplayName("Test that an empty string in validateName() returns false")
            void emptyStringInValidateNameReturnsFalse() {
                // Arrange
                // Act
                // Assert
                assertFalse(DetailsValidator.validateName(""));
            }

            @Test
            @DisplayName("Test that null in validateName() returns false")
            void nullStringInValidateNameReturnsFalse() {
                // Arrange
                // Act
                // Assert
                assertFalse(DetailsValidator.validateName(null));
            }

            @Test
            @DisplayName("Test that a string with characters in it in validateName() returns true")
            void validNameReturnsTrue() {
                // Arrange
                // Act
                // Assert
                assertTrue(DetailsValidator.validateName("test"));
            }
        }

        @Nested
        @DisplayName("US-3: I want my contact to have a phone number")
        class US3DetailsValidatorTests{

            String testPhone;

            @AfterEach
            void tearDown() {
                testPhone = null;
            }

            @Test
            @DisplayName("Test that an empty string in validatePhone() returns false")
            void emptyStringInValidatePhoneReturnsFalse() {
                // Arrange
                testPhone = "";
                // Act
                // Assert
                assertFalse(DetailsValidator.validatePhone(testPhone));
            }

            @Test
            @DisplayName("Test that a string with alphabetic characters in validatePhone() returns false")
            void stringWithAlphabetCharactersInValidatePhoneReturnsFalse() {
                // Arrange
                testPhone = "testtesttes";
                // Act
                // Assert
                assertFalse(DetailsValidator.validatePhone(testPhone));
            }

            @Test
            @DisplayName("Test that a string with punctuation characters in validatePhone() returns false")
            void stringWithPunctuationCharactersInValidatePhoneReturnsFalse() {
                // Arrange
                testPhone = "!£$%^&*()[]";
                // Act
                // Assert
                assertFalse(DetailsValidator.validatePhone(testPhone));
            }

            @Test
            @DisplayName("Test that a string not exactly 11 non-whitespace characters in validatePhone() returns false")
            void stringNotExactlyElevenNonWhitespaceCharactersInValidatePhoneReturnsFalse() {
                // Arrange
                testPhone = "0712345678";
                // Act
                // Assert
                assertFalse(DetailsValidator.validatePhone(testPhone));
            }

            @Test
            @DisplayName("Test that a string with 11 non-whitespaces digits but also whitespace digits returns true")
            void stringWithElevenNonWhitespaceAndSomeWhiteSpaceInValidatePhoneReturnsTrue() {
                // Arrange
                testPhone = "07 123 456 789";
                // Act
                // Assert
                assertTrue(DetailsValidator.validatePhone(testPhone));
            }

            @Test
            @DisplayName("Test that a string whose first two non-whitespace digits aren't 07 returns false")
            void stringNotStartingWith07ReturnsFalse() {
                // Arrange
                testPhone = "12345678901";
                // Act
                // Assert
                assertFalse(DetailsValidator.validatePhone(testPhone));
            }
        }

        @Nested
        @DisplayName("US-4: I want my contact to have an email address")
        class US4DetailsValidatorTests{

            String testEmail;

            @Test
            @DisplayName("Test that a string without an @ symbol returns false")
            void stringWithoutAtSymbolReturnsFalse() {
                // Arrange
                testEmail = "test.test.com";
                // Act
                // Assert
                assertFalse(DetailsValidator.validateEmail(testEmail));
            }

        }
    }
}
