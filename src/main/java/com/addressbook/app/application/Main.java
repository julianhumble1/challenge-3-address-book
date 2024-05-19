package com.addressbook.app.application;

import com.addressbook.app.AddressBook;
import com.addressbook.app.Contact;
import com.addressbook.app.utils.UserInputMenu;

public class Main {

    public static void main(String[] args) {

        AddressBook addressBook = new AddressBook();

        UserInputMenu.printMenuToUser();
        int userChoice = UserInputMenu.takeUserNumberChoice(1, 5);
        addressBook.actOnUserChoice(userChoice);


    }
}
