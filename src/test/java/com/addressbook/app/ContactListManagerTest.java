package com.addressbook.app;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class ContactListManagerTest {

    @Nested
    @DisplayName("ContactListManagerTests")
    class ContactListManagerTests {

        private ContactListManager testContactListManager;

        @BeforeEach
        void setUp() {
            testContactListManager = new ContactListManager();
        }

        @AfterEach
        void tearDown() {
            testContactListManager = null;
        }

        @Nested
        @DisplayName("US-1: I want to be able to add contacts")
        class US1ContactListManagerTests{

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
                int expected = testContactListManager.getContactList().size() + 1;
                // Act
                testContactListManager.addContact(testContact);
                // Assert
                assertEquals(expected, testContactListManager.getContactList().size());
            }

            @Test
            @DisplayName("Resulting ContactList includes the added contact")
            void resultingContactListContainsAddedContact() {
                // Arrange
                // Act
                testContactListManager.addContact(testContact);
                // Assert
                assertTrue(testContactListManager.getContactList().contains(testContact));
            }

            @Test
            @DisplayName("Throws IllegalArgumentException when Null is added")
            void throwsIllegalArgumentExceptionForNull() {
                // Arrange
                // Act
                // Assert
                assertThrows(IllegalArgumentException.class, () -> testContactListManager.addContact(null));
            }

        }
    }
}
