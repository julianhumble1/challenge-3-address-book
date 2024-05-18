package com.addressbook.app;

import com.addressbook.app.utils.DetailsValidator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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

            @Test
            @DisplayName("Test that a string with no content before the @ symbol returns false")
            void stringWithNoContentBeforeAtSymbolReturnsFalse() {
                // Arrange
                testEmail = "@test.test";
                // Act
                // Assert
                assertFalse(DetailsValidator.validateEmail(testEmail));
            }

            @Test
            @DisplayName("Test that a string with whitespace anywhere other than the front or end returns false")
            void stringWithWhitespaceNotAtFrontOrEndReturnsFalse() {
                // Arrange
                testEmail = "test @test. test";
                // Act
                // Assert
                assertFalse(DetailsValidator.validateEmail(testEmail));
            }

            @Test
            @DisplayName("Test that a string without a '.' after the @ symbol returns false")
            void stringNoDotAfterAtSymbolReturnsFalse() {
                // Arrange
                testEmail = "test@test";
                // Act
                // Assert
                assertFalse(DetailsValidator.validateEmail(testEmail));
            }

            @Test
            @DisplayName("Test that a string with a '.' immediately after the @ symbol returns false")
            void stringDotImmediatelyAfterAtSymbolReturnsFalse() {
                // Arrange
                testEmail = "test@.test";
                // Act
                // Assert
                assertFalse(DetailsValidator.validateEmail(testEmail));
            }

        }

        @Nested
        @DisplayName("US-8: I want to be unable to add a contact with the same phone number as another contact")
        class US8DetailsValidatorTests {

            @Test
            @DisplayName("Test that when a phone number which matches a contact already in the arrayList passed in," +
                    "returns false")
            void phoneNumberAlreadyMatchingReturnsFalse() {
                // Arrange
                Contact existingContact = mock(Contact.class);
                when(existingContact.getPhone()).thenReturn("07123456789");
                ArrayList<Contact> contactArrayList = new ArrayList<>(Arrays.asList(existingContact));
                String newContactPhone = "07123456789";
                // Act
                // Assert
                assertFalse(DetailsValidator.checkPhoneNotTaken(newContactPhone, contactArrayList));
            }

            @Test
            @DisplayName("Test that when a phone number which doesn't match a contact already in the arrayList passed in," +
                    "returns true")
            void phoneNumberNotAlreadyMatchingReturnsTrue() {
                // Arrange
                Contact existingContact = mock(Contact.class);
                when(existingContact.getPhone()).thenReturn("07123456789");
                ArrayList<Contact> contactArrayList = new ArrayList<>(Arrays.asList(existingContact));
                String newContactPhone = "07123456781";
                // Act
                // Assert
                assertTrue(DetailsValidator.checkPhoneNotTaken(newContactPhone, contactArrayList));
            }

            @Test
            @DisplayName("Test that an empty array list passed in returns true")
            void emptyArrayListInCheckPhoneNotTakenReturnsTrue() {
                // Arrange
                ArrayList<Contact> contactArrayList = new ArrayList<>();
                String newContactPhone = "07123456781";
                // Act
                // Assert
                assertTrue(DetailsValidator.checkPhoneNotTaken(newContactPhone, contactArrayList));
            }
        }
    }
}
