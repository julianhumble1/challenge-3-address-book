package com.addressbook.app;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

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
        class US1AddressBookTests{

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
                assertEquals(testAddressBook.getContactList().size(), expected);
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
                assertEquals(testAddressBook.getContactList().size(), expected);
            }
        }

    }
}
