package com.addressbook.app;

import com.addressbook.app.utils.UserInputMenu;

import java.util.ArrayList;

public class AddressBook {

    private ContactListManager contactListManager = new ContactListManager();

    public ContactListManager getContactListManager() {
        return contactListManager;
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

    public void actOnUserChoice(int userChoice) {
        switch (userChoice) {
            case 1:
                userAddContact();
        }
    }

    private void userAddContact() {
        try {
            String[] contactDetails = UserInputMenu.takeContactDetailsFromUser();
            Contact newContact = new Contact(contactDetails[0], contactDetails[1], contactDetails[2]);
            this.getContactListManager().addContact(newContact);
            System.out.println("Contact added successfully.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to add contact.");
        }



    }
}
