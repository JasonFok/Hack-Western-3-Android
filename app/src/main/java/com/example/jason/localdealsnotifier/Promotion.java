package com.example.jason.localdealsnotifier;

/**
 * Created by tim on 15/10/16.
 */

public class Promotion {
    //name and address string
    private String company;
    private String message;
    private String distance;

    public Promotion(String company, String message, String distance) {
      /*Blank default constructor essential for Firebase*/
        this.company = company;
        this.message = message;
        this.distance = distance;
    }
    //Getters and setters
    public String getCompany() {
        return company;
    }

    public void setCompany(String name) {
        this.company = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String message) {
        this.distance = distance;
    }
}