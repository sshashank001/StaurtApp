
package com.example.chetan.staurtapp.ui.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class Item implements Serializable {

    @SerializedName("item_count")
    @Expose
    private String itemCount;
    @SerializedName("item_id")
    @Expose
    private String itemId;
    @SerializedName("item_image")
    @Expose
    private String itemImage;
    @SerializedName("item_name")
    @Expose
    private String itemName;
    @SerializedName("item_price")
    @Expose
    private String itemPrice;

    /**
     * 
     * @return
     *     The itemCount
     */
    public String getItemCount() {
        return itemCount;
    }

    /**
     * 
     * @param itemCount
     *     The item_count
     */
    public void setItemCount(String itemCount) {
        this.itemCount = itemCount;
    }

    /**
     * 
     * @return
     *     The itemId
     */
    public String getItemId() {
        return itemId;
    }

    /**
     * 
     * @param itemId
     *     The item_id
     */
    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    /**
     * 
     * @return
     *     The itemImage
     */
    public String getItemImage() {
        return itemImage;
    }

    /**
     * 
     * @param itemImage
     *     The item_image
     */
    public void setItemImage(String itemImage) {
        this.itemImage = itemImage;
    }

    /**
     * 
     * @return
     *     The itemName
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * 
     * @param itemName
     *     The item_name
     */
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    /**
     * 
     * @return
     *     The itemPrice
     */
    public String getItemPrice() {
        return itemPrice;
    }

    /**
     * 
     * @param itemPrice
     *     The item_price
     */
    public void setItemPrice(String itemPrice) {
        this.itemPrice = itemPrice;
    }



}
