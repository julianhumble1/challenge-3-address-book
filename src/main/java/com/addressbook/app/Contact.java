package com.addressbook.app;

import com.addressbook.app.utils.DetailsValidator;

public class Contact {

    private String name;
    private String phone;
    private String email;

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public Contact(String name, String phone, String email) {
        if (DetailsValidator.validateName(name)) {
            this.name = name;
        } else {
            throw new IllegalArgumentException("Name entered can't be empty or null");
        }
        if(DetailsValidator.validatePhone(phone)) {
            this.phone = phone;
        } else {
            throw new IllegalArgumentException("Phone number can't be empty or contain non-numeric characters" +
                    " and must be in form 07XXX XXXXXX");
        }
        if (DetailsValidator.validateEmail(email)) {
            this.email = email;
        } else {
            throw new IllegalArgumentException("Email address can't be empty and must be in the form XXX@XXX.XXX");
        }

    }


}
