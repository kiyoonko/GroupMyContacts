package com.example.kiyoon.groupmycontacts;

/**
 * Created by mzwee on 9/12/15.
 */
public class Contact {
    String name;
    String phoneNum;
    int category;

    public Contact() {
    }

    public Contact(String name, String phoneNum, int category) {
        this.name = name;
        this.phoneNum = phoneNum;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public int getCategory() {
        return category;
    }
}
