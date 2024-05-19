package com.addressbook.app;

import java.util.ArrayList;

public class AddressBook {

    private ContactListManager contactListManager = new ContactListManager();

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
