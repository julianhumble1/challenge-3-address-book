package com.addressbook.app;

import com.addressbook.app.utils.UserInputMenu;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UserInputMenuTest {

    @Nested
    @DisplayName("User Input Menu Tests")
    class UserInputMenuTests {

        @Nested
        @DisplayName("US13: I want to be able to trigger adding a contact")
        class US13UIMTests{

            @Test
            @DisplayName("takeUserNumberChoice returns the input when input is valid")
            void takeUserNumberChoiceReturnsInputWhenInputIsValid() {
                // Arrange
                String input = "3\n";
                ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
                System.setIn(in);
                // Act
                int result = UserInputMenu.takeUserNumberChoice(1, 5);
                // Assert
                assertEquals(3, result);
            }

            @Test
            @DisplayName("takeUserNumberChoice returns second input when first input is invalid but second is valid")
            void takeUserNumberChoiceReturnsSecondInputWhenFirstInputIsInvalidButSecondIsValid() {
                // Arrange
                String input = "10\n3\n";
                ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
                System.setIn(in);
                // Act
                int result = UserInputMenu.takeUserNumberChoice(1, 5);
                // Assert
                assertEquals(3, result);
            }

            @Test
            @DisplayName("takeUserNumberChoice throws Exception when input is a non-integer")
            void throwsExceptionForNonInteger() {
                // Arrange
                String input = "a\n";
                ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
                System.setIn(in);
                // Act
                // Assert
                assertThrows(Exception.class, () -> UserInputMenu.takeUserNumberChoice(1, 5));

            }
        }
    }
}
