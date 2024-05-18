package com.addressbook.app;

import java.util.ArrayList;
import java.util.Arrays;

public class AddressBook {

    private ArrayList<Contact> contactList = new ArrayList<Contact>();

    public ArrayList<Contact> getContactList() {
        return contactList;
    }

    public void addContact(Contact newContact) {
        if (newContact == null) {
            throw new IllegalArgumentException("Null value is not valid");
        } else {
            this.contactList.add(newContact);
        };
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

}
