package com.example.jason.localdealsnotifier;

/**
 * Created by tim on 15/10/16.
 */

public class Promotion {
    //name and address string
    private String company;
    private String address;

    public Promotion() {
      /*Blank default constructor essential for Firebase*/
    }
    //Getters and setters
    public String getCompany() {
        return company;
    }

    public void setName(String name) {
        this.company = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}