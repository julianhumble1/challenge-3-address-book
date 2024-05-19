package com.addressbook.app;

import org.junit.jupiter.api.*;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Arrays;

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
                String expectedString = "No matching search results";
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
            @Disabled
            @DisplayName("test that removing a contact using actOnUserChoice successfully reduces the contact list's length by one")
            void actOnUserChoiceReducesContactListLengthByOne() {
                // Arrange
                Contact testContact = new Contact("Test", "07123456789", "test@test.test");
                testAddressBook.getContactListManager().addContact(testContact);
                int expected = testAddressBook.getContactListManager().getContactList().size() - 1;

                String input = "Test\n1\n";
                ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
                System.setIn(in);
                // Act
                testAddressBook.actOnUserChoice(4);
                int actual = testAddressBook.getContactListManager().getContactList().size();
                // Assert
                assertEquals(expected, actual);
            }

        }

        @Nested
        @DisplayName("US-16: I want to be able to trigger editing a contact's name")
        class US16AddressBookTests {

            @Test
            @Disabled
            @DisplayName("Test that actOnUserInput, with correct inputs and a valid new name, results in the contact having the expected name")
            void actOnUserInputLeadsToContactHavingCorrectNewName() {

            }
        }

        @Nested
        @DisplayName("US-17: I want to be able to trigger editing a contact's email")
        class US17AddressBookTests {

            @Test
            @Disabled
            @DisplayName("Test that actOnUserInput, with correct inputs and a valid new email, results in the contact having the expected email")
            void actOnUserInputLeadsToContactHavingCorrectNewEmail() {

            }
        }
    }
}
