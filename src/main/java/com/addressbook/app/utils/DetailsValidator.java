package com.addressbook.app.utils;

public class DetailsValidator {

    // Purpose Validators

    public static boolean validateName(String name) {
        return (checkStringNotNull(name) && checkStringNotEmpty(name));
    }

    public static boolean validatePhone(String phone) {
        return (
                checkStringNotEmpty(phone)
                && checkStringContainsNoAlphabeticalChars(phone)
                && checkStringContainsNoPunctuationChars(phone)
                && checkStringContainsElevenNonWhiteSpaceCharacters(phone)
        );
    }

    // String Property Checkers

    private static boolean checkStringNotEmpty(String string) {
        return !string.trim().isEmpty();
    }

    private static boolean checkStringNotNull(String string) {
        return !(string == null);
    }

    private static boolean checkStringContainsNoAlphabeticalChars(String string) {
        return !string.matches(".*[a-zA-Z]+.*");
    }

    private static boolean checkStringContainsNoPunctuationChars(String string) {
        return !string.matches(".*[!\"#$%&'()*+,-./:;<=>?@^_`{|}~]+.*");
    }

    private static boolean checkStringContainsElevenNonWhiteSpaceCharacters(String string) {
        String strippedOfWhitespace = string.replaceAll("\\s", "");
        return strippedOfWhitespace.length() == 11;
    }

}
