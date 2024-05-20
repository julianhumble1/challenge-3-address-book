package com.addressbook.app;

import com.addressbook.app.utils.UserInputMenu;
import org.junit.jupiter.api.*;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AddressBookTest {

    @Nested
    @DisplayName("Address Book Tests")
    class AddressBookTests {

        private AddressBook testAddressBook;

        @BeforeEach
        public void setUp() { testAddressBook = new AddressBook(); }

        @AfterEach
        public void tearDown() {
            testAddressBook = null;
        }

        @Nested
        @DisplayName("US-5: I want to be able to search my address book by name and see the results")
        class US5AddressBookTests {

            @Test
            @DisplayName("Test that displayContactsToUser() of a list of contacts returns the desired string")
            void testDisplayContactsToUserWithListOfContactsReturnsDesiredString() {
                // Arrange
                Contact testContact1 = mock(Contact.class), testContact2 = mock(Contact.class);
                when(testContact1.displayContact()).thenReturn("Name: Test1 Test1\nPhone: 07123456789\nEmail: test@test.test");
                when(testContact2.displayContact()).thenReturn("Name: Test2 Test2\nPhone: 07123456789\nEmail: test@test.test");

                ArrayList<Contact> testArrayList = new ArrayList<>(Arrays.asList(testContact1, testContact2));

                String expectedString = "Contact 1:\n\n"+
                        "Name: Test1 Test1\nPhone: 07123456789\nEmail: test@test.test"+
                        "\n\nContact 2:\n\n"+
                        "Name: Test2 Test2\nPhone: 07123456789\nEmail: test@test.test"
                        + "\n\n";

                // Act
                String returnedString = testAddressBook.displayContactsToUser(testArrayList);
                // Assert
                assertEquals(expectedString, returnedString);
            }

            @Test
            @DisplayName("Test that displayContactsToUser() of an empty list returns the desired string")
            void testDisplayContactsToUserWithEmptyListsReturnsDesiredString() {
                // Arrange
                String expectedString = "No contacts available";
                ArrayList<Contact> emptyArrayList = new ArrayList<>();
                // Act
                String returnedString = testAddressBook.displayContactsToUser(emptyArrayList);
                // Assert
                assertEquals(expectedString, returnedString);
            }

        }

        @Nested
        @DisplayName("US-13: I want to be able to trigger adding a contact")
        class US13AddressBookTests{

            @Test
            @DisplayName("Test after performing actOnUserChoice with valid contact details, AB's CLM increases length by 1")
            void actOnUserChoiceValidContactDetailsIncreaseCLMLengthByOne() {
                // Arrange
                String input = "Test\n07123456789\ntest@test.test\n";
                ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
                System.setIn(in);

                int expected = testAddressBook.getContactListManager().getContactList().size() + 1;
                // Act
                testAddressBook.actOnUserChoice(1);
                int actual = testAddressBook.getContactListManager().getContactList().size();
                // Assert
                assertEquals(expected, actual);
                System.setIn(System.in);
            }

            @Test
            @DisplayName("Test actOnUserChoice with invalid details, AB's CLM length stays the same")
            void actOnUserChoiceInvalidDetailsCLMLengthStaysSame() {
                // Arrange
                String input = "Test\n07123456789\ntestbademail\n";
                ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
                System.setIn(in);
                int expected = testAddressBook.getContactListManager().getContactList().size();
                // Act
                testAddressBook.actOnUserChoice(1);
                int actual = testAddressBook.getContactListManager().getContactList().size();
                // Assert
                assertEquals(expected, actual);
                System.setIn(System.in);
            }
        }

        @Nested
        @DisplayName("US-14: I want to be able to trigger searching by name")
        class US14AddressBookTests {

            @Test
            @DisplayName("Test actOnUserChoice with a valid search calls displayContactsToUser")
            void actOnUserChoiceCallsDisplayContactsToUser() {
                // Arrange
                String input = "Search Term\n";
                ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
                System.setIn(in);

                AddressBook spyAddressBook = spy(testAddressBook);
                // Act
                spyAddressBook.actOnUserChoice(2);
                // Assert
                verify(spyAddressBook).displayContactsToUser(any());
            }
        }

        @Nested
        @DisplayName("US-15: I want to be able to trigger removing a contact")
        class US15AddressBookTests {

            @Test
            @DisplayName("test that removing a contact using userRemoveContact successfully reduces the contact list's length by one")
            void actOnUserChoiceReducesContactListLengthByOne() {

                try(MockedStatic<UserInputMenu> userInputMenuMockedStatic = Mockito.mockStatic(UserInputMenu.class)) {
                    // Arrange
                    Contact testContact = new Contact("Test", "07123456789", "test@test.test");
                    testAddressBook.getContactListManager().addContact(testContact);

                    userInputMenuMockedStatic.when(() -> UserInputMenu.takeStringWithPrompt(anyString())).thenReturn("Test");
                    userInputMenuMockedStatic.when(() -> UserInputMenu.takeUserNumberChoice(anyInt(), anyInt())).thenReturn(1);
                    int expected = testAddressBook.getContactListManager().getContactList().size() - 1;

                    // Act
                    testAddressBook.actOnUserChoice(4);
                    int actual = testAddressBook.getContactListManager().getContactList().size();
                    // Assert
                    assertEquals(expected, actual);
                }
            }
        }

        @Nested
        @DisplayName("US-16: I want to be able to trigger editing a contact's name")
        class US16AddressBookTests {

            @Test
            @DisplayName("Test that actOnUserInput, with correct inputs and a valid new name, results in the contact having the expected name")
            void actOnUserInputLeadsToContactHavingCorrectNewName() {

                try(MockedStatic<UserInputMenu> userInputMenuMockedStatic = Mockito.mockStatic(UserInputMenu.class)) {
                    // Arrange
                    Contact testContact = new Contact("Test", "07123456789", "test@test.test");
                    testAddressBook.getContactListManager().addContact(testContact);

                    userInputMenuMockedStatic.when(() -> UserInputMenu.takeStringWithPrompt("Please enter a search term: ")).thenReturn("Test");
                    userInputMenuMockedStatic.when(() -> UserInputMenu.takeUserNumberChoice(1, 1)).thenReturn(1);
                    userInputMenuMockedStatic.when(() -> UserInputMenu.takeUserNumberChoice(1, 3)).thenReturn(1);
                    userInputMenuMockedStatic.when(() -> UserInputMenu.takeStringWithPrompt("Please enter a new name: ")).thenReturn("New Name");
                    // Act
                    testAddressBook.actOnUserChoice(3);
                    String actual = testAddressBook.getContactListManager().getContactList().get(0).getName();
                    // Assert
                    assertEquals("New Name", actual);
                }
            }
        }

        @Nested
        @DisplayName("US-17: I want to be able to trigger editing a contact's email")
        class US17AddressBookTests {

            @Test
            @Disabled
            @DisplayName("Test that actOnUserInput, with correct inputs and a valid new email, results in the contact having the expected email")
            void actOnUserInputLeadsToContactHavingCorrectNewEmail() {
                try(MockedStatic<UserInputMenu> userInputMenuMockedStatic = Mockito.mockStatic(UserInputMenu.class)) {
                    // Arrange
                    Contact testContact = new Contact("Test", "07123456789", "test@test.test");
                    testAddressBook.getContactListManager().addContact(testContact);

                    userInputMenuMockedStatic.when(() -> UserInputMenu.takeStringWithPrompt("Please enter a search term: ")).thenReturn("Test");
                    userInputMenuMockedStatic.when(() -> UserInputMenu.takeUserNumberChoice(1, 1)).thenReturn(1);
                    userInputMenuMockedStatic.when(() -> UserInputMenu.takeUserNumberChoice(1, 3)).thenReturn(3);
                    userInputMenuMockedStatic.when(() -> UserInputMenu.takeStringWithPrompt("Please enter a new email address: ")).thenReturn("new@new.new");
                    // Act
                    testAddressBook.actOnUserChoice(3);
                    String actual = testAddressBook.getContactListManager().getContactList().get(0).getEmail();
                    // Assert
                    assertEquals("new@new.new", actual);
                }
            }
        }

        @Nested
        @DisplayName("US-18: I want to be able to trigger editing a contact's phone number")
        class US18AddressBookTests {

            @Test
            @Disabled
            @DisplayName("Test that actOnUserInput, with correct inputs and a valid new phone number, results in the contact having the expected phone number")
            void actOnUserInputLeadsToContactHavingCorrectNewPhone() {

            }
        }
    }
}
