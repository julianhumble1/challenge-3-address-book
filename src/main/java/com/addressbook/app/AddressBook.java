package com.addressbook.app;

import com.addressbook.app.utils.UserInputMenu;

import java.util.ArrayList;

public class AddressBook {

    private ContactListManager contactListManager = new ContactListManager();

    public ContactListManager getContactListManager() {
        return contactListManager;
    }

    public String displayContactsToUser(ArrayList<Contact> listOfContacts) {
        if (listOfContacts == null || listOfContacts.isEmpty()) return "No matching search results";
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
               break;
            case 2:
                userSearchByName();
                break;
            case 4:
                userRemoveContact();
                break;
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

    private void userSearchByName() {
        String searchTerm = UserInputMenu.takeNameForSearch();
        ArrayList<Contact> searchResults = this.getContactListManager().findSearchResults(searchTerm);
        System.out.println(displayContactsToUser(searchResults));
    }

    private void userRemoveContact() {
        String searchTerm = UserInputMenu.takeNameForSearch();
        ArrayList<Contact> searchResults = this.getContactListManager().findSearchResults(searchTerm);
        System.out.println(displayContactsToUser(searchResults));
        if (searchResults.isEmpty()) {
            System.out.println("Returning To Main Menu");
        } else {
            int indexToRemove = UserInputMenu.takeUserNumberChoice(1, searchResults.size()) - 1;
            Contact contactToRemove = searchResults.get(indexToRemove);
            this.getContactListManager().removeContact(contactToRemove);
            System.out.println("Contact removed successfully");
        }
    }
}
