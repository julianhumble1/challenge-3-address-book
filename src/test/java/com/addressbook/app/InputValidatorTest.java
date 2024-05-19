package com.addressbook.app;

import com.addressbook.app.utils.InputValidator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class InputValidatorTest {

    @Nested
    @DisplayName("Input Validator Tests")
    class InputValidatorTests{

        @Nested
        @DisplayName("US-13: I want to be able to trigger adding a contact")
        class US13InputValidatorTests {

            @Test
            @DisplayName("Test validateIntInRange throws IllegalArgumentException if userChoice is above maxChoice")
            void testAboveBoundsThrowsIllegalArgumentException() {
                // Arrange
                // Act
                // Assert
                assertThrows(IllegalArgumentException.class, () -> InputValidator.validateIntInRange(10, 1, 5));
            }

            @Test
            @DisplayName("Test validateIntInRange throws IllegalArgumentException if userChoice is below minChoice")
            void testBelowBoundsThrowsIllegalArgumentException() {
                // Arrange
                // Act
                // Assert
                assertThrows(IllegalArgumentException.class, () -> InputValidator.validateIntInRange(3, 4, 5));
            }

            @Test
            @DisplayName("Test validateIntInRange doesn't throw exception if user choice is equal to max choice")
            void userChoiceEqualMaxChoiceNoException() {
                // Arrange
                // Act
                // Assert
                assertDoesNotThrow(() -> InputValidator.validateIntInRange(5, 1, 5));
            }

            @Test
            @DisplayName("Test validateIntInRange doesn't throw exception if user choice is equal to min choice")
            void userChoiceEqualMinChoiceNoException() {
                // Arrange
                // Act
                // Assert
                assertDoesNotThrow(() -> InputValidator.validateIntInRange(1, 1, 5));
            }
        }
    }
}
