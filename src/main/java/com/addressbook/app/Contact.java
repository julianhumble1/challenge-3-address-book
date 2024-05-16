package com.addressbook.app;

import com.addressbook.app.utils.DetailsValidator;

public class Contact {

    private String name;

    public String getName() {
        return name;
    }

    public Contact(String name) {
        if (DetailsValidator.validateName(name)) {
            this.name = name;
        } else {
            throw new IllegalArgumentException("Name entered can't be empty or null");
        }

    }
}
