package com.addressbook.app.utils;

public class DetailsValidator {

    public static boolean validateName(String name) {
        return (name != null && !name.trim().isEmpty());
    }
}
