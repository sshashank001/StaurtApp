package com.example.chetan.staurtapp.ui.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by chetan on 11/8/2016.
 */
public class StaurtDetail implements Serializable {
    @SerializedName("restaurant_id")
    String restaurantID;
    @SerializedName("rest_company_id")
    String companyID;

    @Override
    public String toString() {
        return "StaurtDetail{" +
                "restaurantID='" + restaurantID + '\'' +
                ", companyID='" + companyID + '\'' +
                '}';
    }

    public String getRestaurantID() {
        return restaurantID;
    }

    public void setRestaurantID(String restaurantID) {
        this.restaurantID = restaurantID;
    }

    public String getCompanyID() {
        return companyID;
    }

    public void setCompanyID(String companyID) {
        this.companyID = companyID;
    }
}
