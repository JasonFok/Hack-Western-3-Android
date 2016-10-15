package com.example.jason.localdealsnotifier;

/**
 * Created by tim on 15/10/16.
 */

public class UserInput {
    //name and address string
    public String location;

    public UserInput() {
      /*Blank default constructor essential for Firebase*/
    }

    public void setLocation(String location) {
        this.location = location;
    }

}