package com.addressbook.app;

import java.util.ArrayList;

public class AddressBook {

    private ArrayList<Contact> contactList = new ArrayList<Contact>();

    public ArrayList<Contact> getContactList() {
        return contactList;
    }

    public void addContact(Contact newContact) {
        if (newContact == null) throw new IllegalArgumentException("Null value is not valid");
        this.contactList.add(newContact);
    }
}
