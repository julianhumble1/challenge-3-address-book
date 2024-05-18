package com.addressbook.app;

import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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

            @Test
            @DisplayName("Can add a contact when there is already a contact in the list")
            void canAddContactsWhenThereAreAlreadyContacts() {
                // Arrange
                testContactListManager.addContact(testContact);
                int expected = testContactListManager.getContactList().size() + 1;
                // Act
                Contact testContact1 = mock(Contact.class);
                testContactListManager.addContact(testContact1);
                // Assert
                assertEquals(expected, testContactListManager.getContactList().size());
            }

        }

        @Nested
        @DisplayName("US-5: I want to be able to search my contact list by name and see the results")
        class US5ContactListManagerTests {

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
                testContactListManager.addContact(testContact1);
                ArrayList<Contact> expected = new ArrayList<>(Arrays.asList(testContact1));
                // Act
                ArrayList<Contact> actual = testContactListManager.findSearchResults("Test Test");
                // Assert
                assertEquals(expected, actual);
            }

            @Test
            @DisplayName("Test that searching a name match to exactly one of multiple contacts in the address," +
                    " findSearchResults() returns an arrayList containing only that element")
            void matchExactlyOneContactReturnsOnlyThatContact() {
                // Arrange
                when(testContact1.getName()).thenReturn("Test Test");
                when(testContact2.getName()).thenReturn("Test2 Test2");
                testContactListManager.addContact(testContact1);
                testContactListManager.addContact(testContact2);
                ArrayList<Contact> expected = new ArrayList<>(Arrays.asList(testContact1));
                // Act
                ArrayList<Contact> actual = testContactListManager.findSearchResults("Test Test");
                // Assert
                assertEquals(expected, actual);

            }

            @Test
            @DisplayName("Test that searching a partial match to two contacts, findSearchResults() returns an arrayList" +
                    "containing both matches")
            void partialMatchTwoContactReturnsBothContacts() {
                // Arrange
                when(testContact1.getName()).thenReturn("Test1");
                when(testContact2.getName()).thenReturn("Test2");
                testContactListManager.addContact(testContact1);
                testContactListManager.addContact(testContact2);
                ArrayList<Contact> expected = new ArrayList<>(Arrays.asList(testContact1, testContact2));
                // Act
                ArrayList<Contact> actual = testContactListManager.findSearchResults("Test");
                // Assert
                assertEquals(expected, actual);
            }
        }

        @Nested
        @DisplayName("US-6: I want to be able to remove a contact from the contact list")
        class US6ContactListManagerTests {
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
            @DisplayName("Test that when a contact is removed, the contactList length reduces by 1")
            void removeContactReducesContactListLengthByOne() {
                // Arrange
                testContactListManager.addContact(testContact);
                int expected = testContactListManager.getContactList().size() - 1;
                // Act
                testContactListManager.removeContact(testContact);
                // Assert
                assertEquals(expected, testContactListManager.getContactList().size());
            }

            @Test
            @DisplayName("Test that when a contact is removed, the contactList no longer contains that contact")
            void removeContactRemovesThatContactFromList() {
                // Arrange
                testContactListManager.addContact(testContact);
                // Act
                testContactListManager.removeContact(testContact);
                // Assert
                assertFalse(testContactListManager.getContactList().contains(testContact));
            }
        }
    }
}
