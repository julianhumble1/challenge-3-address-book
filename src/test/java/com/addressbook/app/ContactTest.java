package com.addressbook.app;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.DisabledIfSystemProperties;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ContactTest {

    @Nested
    @DisplayName("Contact Tests")
    class ContactTests{

        @Nested
        @DisplayName("US-2: I want my contact to have a name record")
        class US2ContactTests{

            @Test
            @DisplayName("Test that passing a name into the Contact constructor results in the same name being assigned")
            void testCorrectNameIsAssigned() {
                // Arrange
                String testName = "test2";
                // Act
                Contact testContact = new Contact(testName);
                // Assert
                assertEquals(testContact.getName(), testName);
            }

        }
    }
}
