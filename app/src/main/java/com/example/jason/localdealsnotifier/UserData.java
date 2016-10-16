package com.example.jason.localdealsnotifier;

/**
 * Created by tim on 15/10/16.
 */

public class UserData {
    //name and address string
    public double latitude;
    public double longitude;

    public UserData() {
      /*Blank default constructor essential for Firebase*/
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

}
