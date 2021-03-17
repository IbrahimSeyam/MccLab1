package com.example.mcclab1;

import com.google.firebase.firestore.PropertyName;

public class Contact {

    @PropertyName("contact_name")
    private String contact_name;

    @PropertyName("contact_number")
    private int contact_number;

    @PropertyName("contact_address")
    private String contact_address;

    public Contact() {
    }

    public String getContact_name() {
        return contact_name;
    }

    public void setContact_name(String contact_name) {
        this.contact_name = contact_name;
    }

    public int getContact_number() {
        return contact_number;
    }

    public void setContact_number(int contact_number) {
        this.contact_number = contact_number;
    }

    public String getContact_address() {
        return contact_address;
    }

    public void setContact_address(String contact_address) {
        this.contact_address = contact_address;
    }
}
