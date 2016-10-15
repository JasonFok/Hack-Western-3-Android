package com.example.jason.localdealsnotifier;

/**
 * Created by tim on 15/10/16.
 */

public class FilteredPromotions {
    //name and address string
    private String companyName;
    private int location;

    public FilteredPromotions() {
      /*Blank default constructor essential for Firebase*/
    }
    //Getters and setters
    public String getCompanyName() {
        return companyName;
    }
    public int getLocation() {
        return location;
    }

}
