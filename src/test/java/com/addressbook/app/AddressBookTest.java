package com.addressbook.app;

import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AddressBookTest {

    @Nested
    @DisplayName("Address Book Tests")
    class AddressBookTests {

        private AddressBook testAddressBook;

        @BeforeEach
        public void setUp() {
            testAddressBook = new AddressBook();

        }

        @AfterEach
        public void tearDown() {
            testAddressBook = null;
        }

        @Nested
        @DisplayName("US-1: I want to be able to add contacts")
        class US1AddressBookTests {

            Contact testContact;

            @BeforeEach
            public void setUp() {
                testContact = mock(Contact.class);
            }

            @AfterEach
            public void tearDown() {
                testContact = null;
            }

            @Test
            @DisplayName("Using addContact increases length of contactList by 1")
            void testContactListIncreasesBy1() {
                // Arrange
                int expected = testAddressBook.getContactList().size() + 1;
                // Act
                testAddressBook.addContact(testContact);
                // Assert
                assertEquals(expected, testAddressBook.getContactList().size());
            }

            @Test
            @DisplayName("Resulting Contact includes the added contact")
            void resultingContactListContainsAddedContact() {
                // Arrange
                // Act
                testAddressBook.addContact(testContact);
                // Assert
                assertTrue(testAddressBook.getContactList().contains(testContact));
            }

            @Test
            @DisplayName("Throws IllegalArgumentException when Null is added")
            void throwsIllegalArgumentExceptionForNull() {
                // Arrange
                // Act
                // Assert
                assertThrows(IllegalArgumentException.class, () -> testAddressBook.addContact(null));
            }

            @Test
            @DisplayName("Can add a contact when there is already a contact in the list")
            void canAddContactsWhenThereAreAlreadyContacts() {
                // Arrange
                testAddressBook.addContact(testContact);
                int expected = testAddressBook.getContactList().size() + 1;
                // Act
                Contact testContact1 = mock(Contact.class);
                testAddressBook.addContact(testContact1);
                // Assert
                assertEquals(expected, testAddressBook.getContactList().size());
            }
        }

        @Nested
        @DisplayName("US-5: I want to be able to search my address book by name and see the results")
        class US5AddressBookTests {

            private Contact testContact1 = mock(Contact.class);
            private Contact testContact2 = mock(Contact.class);

            @AfterEach
            void tearDown() {
                testContact1 = null;
                testContact2 = null;
            }

            @Test
            @DisplayName("Test that searching a name match to the only contact in the address book, findSearchResults" +
                    "returns that contact as the only element")
            void matchOnlyContactReturnsOnlyThatContact() {
                // Arrange
                when(testContact1.getName()).thenReturn("Test Test");
                testAddressBook.addContact(testContact1);
                ArrayList<Contact> expected = new ArrayList<>(Arrays.asList(testContact1));
                // Act
                ArrayList<Contact> actual = testAddressBook.findSearchResults("Test Test");
                // Assert
                assertEquals(expected, actual);
            }

            @Test
            @DisplayName("Test that searching a name match to exactly one of multiple contacts in the address," +
                    " findSearchResults() returns an arrayList of length 1")
            void matchExactlyOneContactReturnsOnlyThatContact() {
                // Arrange
                when(testContact1.getName()).thenReturn("Test Test");
                when(testContact2.getName()).thenReturn("Test2 Test2");
                testAddressBook.addContact(testContact1);
                testAddressBook.addContact(testContact2);
                ArrayList<Contact> expected = new ArrayList<>(Arrays.asList(testContact1));
                // Act
                ArrayList<Contact> actual = testAddressBook.findSearchResults("Test Test");
                // Assert
                assertEquals(expected, actual);

            }
        }
    }
}
