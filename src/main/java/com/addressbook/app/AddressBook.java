package com.addressbook.app;

import com.addressbook.app.utils.UserInputMenu;

import java.util.ArrayList;

public class AddressBook {

    private ContactListManager contactListManager = new ContactListManager();

    public ContactListManager getContactListManager() {
        return contactListManager;
    }

    public String displayContactsToUser(ArrayList<Contact> listOfContacts) {
        if (listOfContacts == null || listOfContacts.isEmpty()) return "No contacts available";
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
            case 3:
                userEditContact();
                break;
            case 4:
                userRemoveContact();
                break;
            case 5:
                System.out.println(this.displayContactsToUser(this.getContactListManager().getContactList()));
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
        String searchTerm = UserInputMenu.takeStringWithPrompt("Please enter a search term: ");
        ArrayList<Contact> searchResults = this.getContactListManager().findSearchResults(searchTerm);
        System.out.println(displayContactsToUser(searchResults));
    }

    private void userEditContact() {
        String searchTerm = UserInputMenu.takeStringWithPrompt("Please enter a search term: ");
        ArrayList<Contact> searchResults = this.getContactListManager().findSearchResults(searchTerm);
        System.out.println(displayContactsToUser(searchResults));
        if (searchResults.isEmpty()) {
            System.out.println("Returning To Main Menu");
        } else {
            System.out.println("Please select a result to edit: ");
            int indexToEdit = UserInputMenu.takeUserNumberChoice(1, searchResults.size()) - 1;
            Contact contactToEdit = searchResults.get(indexToEdit);
            UserInputMenu.printEditSelection();
            int editSelection = UserInputMenu.takeUserNumberChoice(1, 3);
            editBasedOnSelection(editSelection, contactToEdit);
        }
    }

    private void editBasedOnSelection(int editSelection, Contact contactToEdit) {
        switch (editSelection) {
            case 1:
                userEditName(contactToEdit);
                break;
            case 2:
                userEditPhone(contactToEdit);
                break;
            case 3:
                userEditEmail(contactToEdit);
                break;
        }
    }

    private void userRemoveContact() {
        String searchTerm = UserInputMenu.takeStringWithPrompt("Please enter a search term: ");
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

    private void userEditName(Contact contact) {
        String newName = UserInputMenu.takeStringWithPrompt("Please enter a new name: ");
        try{
            this.getContactListManager().editContactName(contact, newName);
            System.out.println("Contact Name Changed Successfully");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    private void userEditPhone(Contact contact) {
        String newPhone = UserInputMenu.takeStringWithPrompt("Please enter a new phone number: ");
        try{
            this.getContactListManager().editContactPhone(contact, newPhone);
            System.out.println("Contact Phone Number Changed Successfully");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


    }

    private void userEditEmail(Contact contact) {
        String newEmail = UserInputMenu.takeStringWithPrompt("Please enter a new email address: ");
        try{
            this.getContactListManager().editContactEmail(contact, newEmail);
            System.out.println("Contact Email Changed Successfully");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
