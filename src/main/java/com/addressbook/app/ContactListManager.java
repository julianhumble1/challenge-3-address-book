package com.addressbook.app;

import java.util.ArrayList;

public class ContactListManager {

    private ArrayList<Contact> contactList = new ArrayList<Contact>();

    public ArrayList<Contact> getContactList() {
        return contactList;
    }

    // ADD AND REMOVE CONTACTS

    public void addContact(Contact newContact) {
        checkEmailNotTaken(newContact.getEmail());
        if (newContact == null) {
            throw new IllegalArgumentException("Null value is not valid");
        } else if (!checkPhoneNotTaken(newContact.getPhone())) {
            throw new IllegalArgumentException("Phone number inputted already matches contact in contact list.");
        } else {
            this.contactList.add(newContact);
        }
    }

    public void removeContact(Contact contactToRemove) {
        if (contactToRemove == null) {
            throw new IllegalArgumentException("Null value is not valid");
        } else {
            this.contactList.remove(contactToRemove);
        }
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
        if (checkPhoneNotTaken(newPhone)) {
            contact.setPhone(newPhone);
        } else {
            throw new IllegalArgumentException("New phone number passed in matches another contact's phone number");
        }
    }

    public void editContactEmail(Contact contact, String newEmail) {
        checkContactInContactList(contact);
        contact.setEmail(newEmail);
    }

    // CHECK DETAILS NOT ALREADY IN EXISTING CONTACTS

    public boolean checkPhoneNotTaken(String phone) {
        for (Contact contact: this.contactList) {
            if (contact.getPhone().equals(phone)) return false;
        }
        return true;
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
