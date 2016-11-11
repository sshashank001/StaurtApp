package com.example.chetan.staurtapp.ui.foodDetail;

import com.example.chetan.staurtapp.ui.model.Item;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by chetan on 11/10/2016.
 */
public class FoodCart implements Serializable {
    static volatile FoodCart INSTANCE;
    public static FoodCart getInstance() {
        if (INSTANCE == null)
            INSTANCE = new FoodCart();
        return INSTANCE;
    }

    public static void destroy() {
        INSTANCE = null;
    }


    public HashMap<Item, Integer> getFoodItems() {
        return foodItems;
    }

    private HashMap<Item, Integer> foodItems;
    private double price;

    private FoodCart() {
        foodItems = new HashMap<>(0);
        price = 0.0;
    }

    public void addItem(Item foodItem, int quantity) {
        foodItems.put(foodItem, quantity);
    }

    public void removeItem(Item foodItem) {
        if (foodItems.containsKey(foodItem)) {
            foodItems.put(foodItem, foodItems.get(foodItem) - 1);
        }
    }

    public double getPrice() {
        return price;
    }


    public ArrayList<Item> getKeysAsArrayList(){
        ArrayList<Item> keys = new ArrayList<>();
        for(Item key:foodItems.keySet() ){
            if(foodItems.get(key) >  0 )
                keys.add(key);
        }
        return keys;
    }


}
