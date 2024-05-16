package com.addressbook.app;

import com.addressbook.app.utils.DetailsValidator;

public class Contact {

    private String name;
    private String phone;

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public Contact(String name, String phone) {
        if (DetailsValidator.validateName(name)) {
            this.name = name;
        } else {
            throw new IllegalArgumentException("Name entered can't be empty or null");
        }
        this.phone = phone;
    }


}
