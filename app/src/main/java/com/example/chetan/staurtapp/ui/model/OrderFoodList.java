
package com.example.chetan.staurtapp.ui.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;



public class OrderFoodList implements Serializable {

    @SerializedName("orderLists")
    @Expose
    private List<OrderList> orderLists = new ArrayList<OrderList>();
    @SerializedName("success")
    @Expose
    private boolean success;

    /**
     * 
     * @return
     *     The orderLists
     */
    public List<OrderList> getOrderLists() {
        return orderLists;
    }

    /**
     * 
     * @param orderLists
     *     The orderLists
     */
    public void setOrderLists(List<OrderList> orderLists) {
        this.orderLists = orderLists;
    }

    /**
     * 
     * @return
     *     The success
     */
    public boolean isSuccess() {
        return success;
    }

    /**
     * 
     * @param success
     *     The success
     */
    public void setSuccess(boolean success) {
        this.success = success;
    }



}
