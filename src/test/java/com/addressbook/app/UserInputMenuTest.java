package com.addressbook.app;

import com.addressbook.app.utils.UserInputMenu;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.*;

public class UserInputMenuTest {

    @Nested
    @DisplayName("User Input Menu Tests")
    class UserInputMenuTests {

        @Nested
        @DisplayName("US13: I want to be able to trigger adding a contact")
        class US13UIMTests {

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
                System.setIn(System.in);
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
                System.setIn(System.in);
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
                System.setIn(System.in);

            }

            @Test
            @DisplayName("takeContactDetailsFromUser returns expected Array")
            void takeContactDetailsFromUserReturnsExpectedArray() {
                // Arrange
                String input = "Test\n07123456789\ntest@test.test";
                ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
                System.setIn(in);
                String[] expected = new String[] {"Test", "07123456789", "test@test.test"};
                // Act
                String[] actual = UserInputMenu.takeContactDetailsFromUser();
                // Assert
                assertArrayEquals(expected, actual);
                System.setIn(System.in);
            }
        }

        @Nested
        @DisplayName("US14: I want to be able to trigger searching by name")
        class US14UIMTests {

            @Test
            @DisplayName("Test that takeNameForSearch returns passed in string")
            void takeNameForSearchReturnsPassedInString() {
                // Arrange
                String input = "Search Term\n";
                ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
                System.setIn(in);
                // Act
                String actual = UserInputMenu.takeNameForSearch();
                // Assert
                assertEquals("Search Term", actual);
            }
        }

        @Nested
        @DisplayName("US16: I want to be able to trigger editing a contact's name")
        class US16UIMTests {

            @Test
            @DisplayName("Test that takeNewName() returns the same string as inputted")
            void takeNewNameReturnsInputtedString() {
                // Arrange
                String input = "New Name\n";
                ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
                System.setIn(in);
                // Act
                String actual = UserInputMenu.takeNewName();
                // Assert
                assertEquals("New Name", actual);
            }
        }
    }
}
