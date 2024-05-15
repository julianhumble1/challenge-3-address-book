package com.addressbook.app;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
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

        @Test
        @DisplayName("Using addContact increases length of contactList by 1")
        void testContactListIncreasesBy1() {
            // Arrange
            int expected = testAddressBook.getContactList().size() + 1;
            Contact testContact = mock(Contact.class);
            // Act
            testAddressBook.addContact(testContact);
            // Assert
            assertEquals(testAddressBook.getContactList().size(), expected);
        }
    }
}
