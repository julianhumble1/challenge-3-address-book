package com.addressbook.app;

import com.addressbook.app.utils.InputValidator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class InputValidatorTest {

    @Nested
    @DisplayName("Input Validator Tests")
    class InputValidatorTests{

        @Nested
        @DisplayName("US-13: I want to be able to trigger adding a contact")
        class US13InputValidatorTests {

            @Test
            @DisplayName("Test validateIntInRange throws IllegalArgumentException if userChoice is not between" +
                    "min and max choice")
            void testOutOfBoundsThrowsIllegalArgumentException() {
                // Arrange
                // Act
                // Assert
                assertThrows(IllegalArgumentException.class, () -> InputValidator.validateIntInRange(10, 1, 5));
            }
        }

    }
}
