package com.example.chetan.staurtapp.ui.foodDetail;

import android.content.Context;

import com.example.chetan.staurtapp.ui.model.Item;


/**
 * Created by chetan on 8/20/2016.
 */
public interface FoodDetailContract {
    interface View{
        void showQuantity(int quantity);
        void showPrice(Double price);
        void showError();
        void closeWindow();
        Context getContext();
    }
    interface  Presenter{
        void increaseItems();
        void decreaseItems();
        void addToCart(Item foodItem);

    }
}
