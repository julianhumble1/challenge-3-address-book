package com.addressbook.app;

import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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
                when(testContact.getPhone()).thenReturn("07987654321");
                when(testContact.getEmail()).thenReturn("test@test.test");
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
                when(testContact1.getPhone()).thenReturn("07123456789");
                when(testContact1.getEmail()).thenReturn("test1@test1.test1");
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

            @BeforeEach
            void setUp() {
                when(testContact1.getPhone()).thenReturn("07123456789");
                when(testContact1.getEmail()).thenReturn("test1@test1.test1");

                when(testContact2.getPhone()).thenReturn("07987654321");
                when(testContact2.getEmail()).thenReturn("test2@test2.test2");
            }

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
                when(testContact1.getPhone()).thenReturn("07123456789");
                when(testContact1.getEmail()).thenReturn("test1@test1.test1");

                when(testContact2.getName()).thenReturn("Test2");
                when(testContact2.getPhone()).thenReturn("07987654321");
                when(testContact2.getEmail()).thenReturn("test2@test2.test2");

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

            @Test
            @DisplayName("Test that when there are multiple contacts in the list, only one contact is removed")
            void onlyOneContactRemovedWhenMultipleContactsInTheList() {
                // Arrange
                when(testContact.getPhone()).thenReturn("07987654321");
                when(testContact.getEmail()).thenReturn("test@test.test");

                Contact testContact1 = mock(Contact.class);
                when(testContact1.getPhone()).thenReturn("07123456789");
                when(testContact1.getEmail()).thenReturn("test1@test1.test1");

                testContactListManager.addContact(testContact);
                testContactListManager.addContact(testContact1);

                int expected = testContactListManager.getContactList().size() - 1;
                // Act
                testContactListManager.removeContact(testContact1);
                // Assert
                assertEquals(expected, testContactListManager.getContactList().size());
            }

            @Test
            @DisplayName("Test that passing in a null contact throws IllegalArgumentException")
            void nullContactThrowsIllegalArgumentException() {
                // Arrange
                testContactListManager.addContact(testContact);
                // Act
                // Assert
                assertThrows(IllegalArgumentException.class, () -> testContactListManager.removeContact(null));
            }
        }

        @Nested
        @DisplayName("US-7: I want to be able to edit a contact's details")
        class US7ContactListManagerTests {

            @Test
            @DisplayName("Test that when editContactName is called, setName is called on the contact with the given name")
            void editContactNameCallsSetNameWithCorrectName() {
                // Arrange
                Contact testContact = new Contact("Test", "07123456789", "test@test.com");
                Contact spyContact = spy(testContact);
                testContactListManager.addContact(spyContact);
                String newName = "New Name";
                // Act
                testContactListManager.editContactName(spyContact, newName);
                // Assert
                verify(spyContact).setName(newName);
            }

            @Test
            @DisplayName("Test editContactName throws IllegalArgumentException if Contact passed in is not" +
                    "in CLM's contact list")
            void contactNotInContactListInEditContactNameThrowsIllegalArgumentException() {
                // Arrange
                Contact testContact = mock(Contact.class);
                when(testContact.getPhone()).thenReturn("07123456789");
                // Act
                // Assert
                assertThrows(IllegalArgumentException.class, () -> testContactListManager.editContactName(testContact, "New Name"));
            }

            @Test
            @DisplayName("Test that when editContactPhone is called, setPhone is called on the contact with the given phone")
            void editContactPhoneCallsSetPhoneWithCorrectPhone() {
                // Arrange
                Contact testContact = new Contact("Test", "07123456789", "test@test.com");
                Contact spyContact = spy(testContact);
                testContactListManager.addContact(spyContact);
                String newPhone = "07987654321";
                // Act
                testContactListManager.editContactPhone(spyContact, newPhone);
                // Assert
                verify(spyContact).setPhone(newPhone);
            }

            @Test
            @DisplayName("Test editContactPhone throws IllegalArgumentException if Contact passed in is not" +
                    "in CLM's contact list")
            void contactNotInContactListInEditContactPhoneThrowsIllegalArgumentException() {
                // Arrange
                Contact testContact = mock(Contact.class);
                when(testContact.getPhone()).thenReturn("07123456789");
                // Act
                // Assert
                assertThrows(IllegalArgumentException.class, () -> testContactListManager.editContactPhone(testContact, "07987654321"));
            }

            @Test
            @DisplayName("Test that when editContactEmail is called, setEmail is called on the contact with the given email")
            void editContactEmailCallsSetEmailWithCorrectEmail() {
                // Arrange
                Contact testContact = new Contact("Test", "07123456789", "test@test.com");
                Contact spyContact = spy(testContact);
                testContactListManager.addContact(spyContact);
                String newEmail = "new@new.new";
                // Act
                testContactListManager.editContactEmail(spyContact, newEmail);
                // Assert
                verify(spyContact).setEmail(newEmail);
            }

            @Test
            @DisplayName("Test editContactEmail throws IllegalArgumentException if Contact passed in is not" +
                    "in CLM's contact list")
            void contactNotInContactListInEditContactEmailThrowsIllegalArgumentException() {
                // Arrange
                Contact testContact = mock(Contact.class);
                when(testContact.getPhone()).thenReturn("07123456789");
                // Act
                // Assert
                assertThrows(IllegalArgumentException.class, () -> testContactListManager.editContactEmail(testContact, "new@new.new"));
            }
        }

        @Nested
        @DisplayName("US-8: I want to be unable to add a contact with the same phone number as another contact")
        class US8ContactListManagerTests {

            @Test
            @DisplayName("Test that when a phone number in checkPhoneNotTaken matches a contact already in the" +
                    " contactList throws IllegalArgumentException")
            void phoneNumberAlreadyMatchingReturnsFalse() {
                // Arrange
                Contact existingContact = mock(Contact.class);
                when(existingContact.getPhone()).thenReturn("07123456789");
                when(existingContact.getEmail()).thenReturn("test@test.test");
                testContactListManager.addContact(existingContact);

                String newContactPhone = "07123456789";
                // Act
                // Assert
                assertThrows(IllegalArgumentException.class, () -> testContactListManager.checkPhoneNotTaken(newContactPhone));
            }
            @Test
            @DisplayName("Test that when a phone number which doesn't match a contact already in the contactList," +
                    "doesn't throw IllegalArgumentException")
            void phoneNumberNotAlreadyMatchingDoesNotThrowException() {
                // Arrange
                Contact existingContact = mock(Contact.class);
                when(existingContact.getPhone()).thenReturn("07123456789");
                when(existingContact.getEmail()).thenReturn("test@test.test");
                testContactListManager.addContact(existingContact);
                String newContactPhone = "07123456781";
                // Act
                // Assert
                assertDoesNotThrow(() -> testContactListManager.checkPhoneNotTaken(newContactPhone));
            }

            @Test
            @DisplayName("Throws IllegalArgumentException adding a contact with phone number" +
                    "already taken by another contact")
            void alreadyTakenPhoneNumberThrowsIllegalArgumentException() {
                // Arrange
                Contact existingContact = mock(Contact.class);
                when(existingContact.getPhone()).thenReturn("07123456789");
                when(existingContact.getEmail()).thenReturn("existing@existing.existing");

                testContactListManager.addContact(existingContact);

                Contact newContact = mock(Contact.class);
                when(newContact.getPhone()).thenReturn("07123456789");
                when(newContact.getEmail()).thenReturn("new@new.new");
                // Act

                // Assert
                assertThrows(IllegalArgumentException.class, () -> testContactListManager.addContact(newContact));
            }
        }

        @Nested
        @DisplayName("US-9: I want to be unable to edit a contact to have the same phone number as another contact")
        class US9ContactListManagerTests {

            @Test
            @DisplayName("Test IllegalArgumentException thrown if new phone in editContactPhone matches already" +
                    "existing contact's phone")
            void matchingPhoneInEditContactPhoneThrowsIllegalArgumentException() {
                // Arrange
                Contact testContact1 = mock(Contact.class);
                when(testContact1.getPhone()).thenReturn("07123456789");
                when(testContact1.getEmail()).thenReturn("test@test.test");
                testContactListManager.addContact(testContact1);

                Contact testContact2 = mock(Contact.class);
                when(testContact2.getPhone()).thenReturn("07987654321");
                testContactListManager.addContact(testContact2);
                // Act
                // Assert
                assertThrows(IllegalArgumentException.class, () -> testContactListManager.editContactPhone(testContact2, "07123456789"));
            }
        }

        @Nested
        @DisplayName("US-10: I want to be unable to add a contact with the same email as another contact")
        class US10ContactListManagerTests {

            @Test
            @DisplayName("Test that when an email in checkEmailNotTaken matches contact in contactList,"
                        + "throws IllegalArgumentException")
            void testAlreadyTakenEmailThrowsIllegalArgumentException() {
                // Arrange
                Contact testContact = mock(Contact.class);
                when(testContact.getEmail()).thenReturn("test@test.test");
                when(testContact.getPhone()).thenReturn("07123456789");
                testContactListManager.addContact(testContact);
                // Act
                // Assert
                assertThrows(IllegalArgumentException.class, () -> testContactListManager.checkEmailNotTaken("test@test.test"));
            }

            @Test
            @DisplayName("Test that when an email in checkEmailNotTaken doesn't match contact in contact list, doesn't" +
                    "throw IllegalArgumentException")
            void newEmailInCheckEmailNotTakenDoesNotThrowIllegalArgumentException() {
                // Arrange
                // Act
                // Assert
                assertDoesNotThrow(() -> testContactListManager.checkEmailNotTaken("test@test.com"));
            }

            @Test
            @DisplayName("Test that a contact in addContact with an email matchign another contact throws IllegalArgumentException")
            void addContactWithDuplicateEmailThrowsIllegalArgumentException() {
                // Arrange
                Contact testContact1 = mock(Contact.class);
                when(testContact1.getPhone()).thenReturn("07123456789");
                when(testContact1.getEmail()).thenReturn("test@test.test");

                testContactListManager.addContact(testContact1);

                Contact testContact2 = mock(Contact.class);
                when(testContact2.getPhone()).thenReturn("07987654321");
                when(testContact2.getEmail()).thenReturn("test@test.test");


                // Act
                // Assert
                assertThrows(IllegalArgumentException.class, () -> testContactListManager.addContact(testContact2));
            }
        }

        @Nested
        @DisplayName("US-11: I want to be unable to edit a contact to have the same email as another contact")
        class US11ContactListManagerTests {

            @Test
            @DisplayName("Test IllegalArgumentException thrown if new email in editContactPhone matches already" +
                    "existing contact's email")
            void matchingEmailInEditContactEmailThrowsIllegalArgumentException() {
                // Arrange
                Contact testContact1 = mock(Contact.class);
                when(testContact1.getPhone()).thenReturn("07123456789");
                when(testContact1.getEmail()).thenReturn("test@test.test");
                testContactListManager.addContact(testContact1);

                Contact testContact2 = mock(Contact.class);
                when(testContact2.getPhone()).thenReturn("07987654321");
                when(testContact2.getEmail()).thenReturn("test2@test2.test2");
                testContactListManager.addContact(testContact2);
                // Act
                // Assert
                assertThrows(IllegalArgumentException.class, () -> testContactListManager.editContactEmail(testContact2, "test@test.test"));
            }
        }
    }
}
