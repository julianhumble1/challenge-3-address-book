package com.addressbook.app.utils;

public abstract class InputValidator {

    public static void validateIntInRange(int userInt, int minChoice, int maxChoice) {
        if (userInt < minChoice || userInt > maxChoice) {
            throw new IllegalArgumentException("Number inputted must be in the range specified");
        }
    }
}
