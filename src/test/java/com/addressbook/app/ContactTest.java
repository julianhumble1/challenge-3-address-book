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

            @Test
            @DisplayName("Test that passing an invalid email returns IllegalArgumentException")
            void checkInvalidEmailThrowsIllegalArgumentException() {
                // Arrange
                testEmail = "bademail";
                // Act
                // Assert
                assertThrows(IllegalArgumentException.class, () -> new Contact(testName, testPhone, testEmail));
            }
        }

        @Nested
        @DisplayName("US-5: I want to be able to search my address book by name and see the results")
        class US5ContactTests{

            @Test
            @DisplayName("Test that displayContact returns the desired string")
            void testDisplayContactReturnsDesiredString() {
                // Arrange
                String testName = "Test Test";
                String testPhone = "07123456789";
                String testEmail = "test@test.test";
                Contact testContact = new Contact(testName, testPhone, testEmail);
                String expected = "Name: Test Test\nPhone: 07123456789\nEmail: test@test.test";
                // Act
                String returnedString = testContact.displayContact();
                // Assert
                assertEquals(expected, returnedString);
            }
        }

        @Nested
        @DisplayName("US-7: I want to be able to edit a contact's details")
        class US7ContactTests{

            String initialName = "Test Test";
            String testPhone = "07123456789";
            String testEmail = "test@test.test";
            Contact testContact = new Contact(initialName, testPhone, testEmail);

            @Test
            @DisplayName("Test that when changing the contact's name to a valid name sets the expected resulting name")
            void testCorrectNewNameIsAssigned() {
                // Arrange
                // Act
                testContact.setName("New Name");
                // Assert
                assertEquals("New Name", testContact.getName());
            }

            @Test
            @DisplayName("Test that setting an invalid name throws IllegalArgumentException")
            void testInvalidNameThrowsIllegalArgumentException() {
                // Arrange
                // Act
                // Assert
                assertThrows(IllegalArgumentException.class, () -> testContact.setName(""));
            }

            @Test
            @DisplayName("Test that when changing the contact's phone to a valid number sets the expected resulting phone")
            void testCorrectNewPhoneIsAssigned() {
                // Arrange
                // Act
                testContact.setPhone("07987654321");
                // Assert
                assertEquals("07987654321", testContact.getPhone());
            }

            @Test
            @DisplayName("Test that setting an invalid phone throws IllegalArgumentException")
            void testInvalidPhoneThrowsIllegalArgumentException() {
                // Arrange
                // Act
                // Assert
                assertThrows(IllegalArgumentException.class, () -> testContact.setPhone("00000000000"));
            }

            @Test
            @DisplayName("Test that when changing the contact's email to a valid email sets the expected resulting email")
            void testCorrectNewEmailIsAssigned() {
                // Arrange
                // Act
                testContact.setEmail("new@new.new");
                // Assert
                assertEquals("new@new.new", testContact.getEmail());
            }

            @Test
            @DisplayName("Test that setting an invalid email throws IllegalArgumentException")
            void testInvalidEmailThrowsIllegalArgumentException() {
                // Arrange
                // Act
                // Assert
                assertThrows(IllegalArgumentException.class, () -> testContact.setEmail("New Email"));
            }
        }

    }
}
