
package com.example.chetan.staurtapp.ui.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;



public class OrderList implements Serializable{

    @SerializedName("items")
    @Expose
    private List<Item> items = new ArrayList<Item>();
    @SerializedName("order_no")
    @Expose
    private String orderNo;
    @SerializedName("room_number")
    @Expose
    private String roomNumber;
    @SerializedName("total_price")
    @Expose
    private String totalPrice;

    /**
     * 
     * @return
     *     The items
     */
    public List<Item> getItems() {
        return items;
    }

    /**
     * 
     * @param items
     *     The items
     */
    public void setItems(List<Item> items) {
        this.items = items;
    }

    /**
     * 
     * @return
     *     The orderNo
     */
    public String getOrderNo() {
        return orderNo;
    }

    /**
     * 
     * @param orderNo
     *     The order_no
     */
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    /**
     * 
     * @return
     *     The roomNumber
     */
    public String getRoomNumber() {
        return roomNumber;
    }

    /**
     * 
     * @param roomNumber
     *     The room_number
     */
    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    /**
     * 
     * @return
     *     The totalPrice
     */
    public String getTotalPrice() {
        return totalPrice;
    }

    /**
     * 
     * @param totalPrice
     *     The total_price
     */
    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }



}
