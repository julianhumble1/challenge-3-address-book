package com.addressbook.app;

import com.addressbook.app.utils.DetailsValidator;
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



    }
}
