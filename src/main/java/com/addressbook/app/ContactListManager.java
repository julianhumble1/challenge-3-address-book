package com.addressbook.app;

import com.addressbook.app.utils.DetailsValidator;

import java.util.ArrayList;

public class ContactListManager {

    private ArrayList<Contact> contactList = new ArrayList<Contact>();

    public ArrayList<Contact> getContactList() {
        return contactList;
    }

    // ADD AND REMOVE CONTACTS

    public void addContact(Contact newContact) {
        DetailsValidator.checkNotNull(newContact);
        checkEmailNotTaken(newContact.getEmail());
        checkPhoneNotTaken(newContact.getPhone());
        this.contactList.add(newContact);
    }

    public void removeContact(Contact contactToRemove) {
        DetailsValidator.checkNotNull(contactToRemove);
        this.contactList.remove(contactToRemove);

    }

    // NARROW DOWN CONTACT LIST

    public ArrayList<Contact> findSearchResults(String searchTerm) {
        ArrayList<Contact> searchResults = new ArrayList<>();
        for (Contact contact: contactList) {
            if ((contact.getName()).startsWith(searchTerm.trim())) {
                searchResults.add(contact);
            }
        }
        return searchResults;
    }

    // EDIT CONTACT DETAILS

    public void editContactName(Contact contact, String newName) {
        checkContactInContactList(contact);
        contact.setName(newName);
    }

    public void editContactPhone(Contact contact, String newPhone) {
        checkContactInContactList(contact);
        checkPhoneNotTaken(newPhone);
        contact.setPhone(newPhone);
    }

    public void editContactEmail(Contact contact, String newEmail) {
        checkContactInContactList(contact);
        contact.setEmail(newEmail);
    }

    // CHECK DETAILS NOT ALREADY IN EXISTING CONTACTS

    public void checkPhoneNotTaken(String phone) {
        for (Contact contact: this.contactList) {
            if (contact.getPhone().equals(phone)) {
                throw new IllegalArgumentException("This phone number is already taken by another contact in the contact list");
            }
        }
    }

    public void checkEmailNotTaken(String email) {
        for (Contact contact: this.contactList) {
            if(contact.getEmail().equals(email)) {
                throw new IllegalArgumentException("This email is already taken by another contact in the contact list");
            }
        }
    }

    // CHECK CONTACT PASSED IN IS IN THE CONTACT LIST

    private void checkContactInContactList(Contact contact) {
        if (!this.contactList.contains(contact)) {
            throw new IllegalArgumentException("Contact passed in must be in the contact list");
        }
    }

}
