package com.addressbook.app;

import com.addressbook.app.utils.ResultsPrinter;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ResultsPrinterTest {

    @Nested
    @DisplayName("Results Printer Tests")
    class ResultsPrinterTests{

        @Nested
        @DisplayName("US-5: I want to be able to search my address book by name and see the results")
        class US5ResultsPrinterTests{

            @Test
            @DisplayName("Test that displayContactsToUser() returns the desired string")
            void testDisplayContactsToUserReturnsDesiredString() {
                // Arrange
                Contact testContact1 = mock(Contact.class), testContact2 = mock(Contact.class);
                when(testContact1.displayContact()).thenReturn("Name: Test1 Test1\nPhone: 07123456789\nEmail: test@test.test");
                when(testContact2.displayContact()).thenReturn("Name: Test2 Test2\nPhone: 07123456789\nEmail: test@test.test");

                ArrayList<Contact> testArrayList = new ArrayList<>(Arrays.asList(testContact1, testContact2));

                String expectedString = "Contact 1:\n\n"+
                        "Name: Test1 Test1\nPhone: 07123456789\nEmail: test@test.test"+
                        "\n\nContact 2:\n\n"+
                        "Name: Test2 Test2\nPhone: 07123456789\nEmail: test@test.test"
                        + "\n\n";

                // Act
                String returnedString = ResultsPrinter.displayContactsToUser(testArrayList);
                // Assert
                assertEquals(expectedString, returnedString);
            }

        }
    }
}
