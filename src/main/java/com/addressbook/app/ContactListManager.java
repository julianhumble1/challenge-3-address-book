package com.addressbook.app;

import java.util.ArrayList;

public class ContactListManager {

    private ArrayList<Contact> contactList = new ArrayList<Contact>();

    public ArrayList<Contact> getContactList() {
        return contactList;
    }

    public void addContact(Contact newContact) {
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

    public ArrayList<Contact> findSearchResults(String searchTerm) {
        ArrayList<Contact> searchResults = new ArrayList<>();
        for (Contact contact: contactList) {
            if ((contact.getName()).startsWith(searchTerm.trim())) {
                searchResults.add(contact);
            }
        }
        return searchResults;
    }

    public void editContactName(Contact contact, String newName) {
        contact.setName(newName);
    }

    public void editContactPhone(Contact contact, String newPhone) {
        contact.setPhone(newPhone);
    }

    public void editContactEmail(Contact contact, String newEmail) {
        contact.setEmail(newEmail);
    }

    public boolean checkPhoneNotTaken(String phone) {
        for (Contact contact: this.contactList) {
            if (contact.getPhone().equals(phone)) return false;
        }
        return true;
    }

}
