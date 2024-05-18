package com.addressbook.app;

import com.addressbook.app.utils.DetailsValidator;

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
        } else if (!DetailsValidator.checkPhoneNotTaken(newContact.getPhone(), this.getContactList())){
            throw new IllegalArgumentException("Can not add contact with same phone number as existing contact");
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

    public static String displayContactsToUser(ArrayList<Contact> listOfContacts) {
        String stringToReturn = "";
        for (int i = 0; i < listOfContacts.size(); i++) {
            stringToReturn += ("Contact " + (i+1) + ":\n\n");
            stringToReturn += listOfContacts.get(i).displayContact();
            stringToReturn += "\n\n";
        }
        return stringToReturn;
    }

}
