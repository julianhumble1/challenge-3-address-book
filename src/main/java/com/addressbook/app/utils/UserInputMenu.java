package com.addressbook.app.utils;

import com.addressbook.app.AddressBook;

import java.sql.SQLOutput;
import java.util.InputMismatchException;
import java.util.Scanner;

public abstract class UserInputMenu {

    public static void printMenuToUser() {
        String mainMenu = "";
        mainMenu += "------------------";
        mainMenu += "\nAddress Book - Main Menu";
        mainMenu += "\n------------------";
        mainMenu += "\n\n1: Add Contact";
        mainMenu += "\n2: Remove Contact";
        mainMenu += "\n3: Edit Contact";
        mainMenu += "\n4: Search Contacts";
        mainMenu += "\n5: Display All Contacts";

        System.out.println(mainMenu);
    }

    public static int takeUserNumberChoice(int minChoice, int maxChoice) {
        Scanner scanner = new Scanner(System.in);
        int userChoice = 0;
        boolean validInput = false;
        while (!validInput) {
            System.out.printf("Please enter a number %d-%d here: ", minChoice, maxChoice);
            try{
                userChoice = scanner.nextInt();
                InputValidator.validateIntInRange(userChoice, minChoice, maxChoice);
                validInput = true;
            } catch (Exception e) {
                System.out.printf("Input must be a number in the range %d - %d\n", minChoice, maxChoice);
                scanner.nextLine();
            }
        }
        return userChoice;
    }

    public static String[] takeContactDetailsFromUser() {
        Scanner scanner = new Scanner(System.in);
        String[] contactDetails = new String[3];
        System.out.println("Please enter a name for the contact: ");
        contactDetails[0] = scanner.nextLine();
        System.out.println("Please enter a phone number for the contact: ");
        contactDetails[1] = scanner.nextLine();
        System.out.println("Please enter an email address for the contact: ");
        contactDetails[2] = scanner.nextLine();
        return contactDetails;
    }

    public static String takeNameForSearch() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter a search term: ");
        return scanner.nextLine();
    }
}

