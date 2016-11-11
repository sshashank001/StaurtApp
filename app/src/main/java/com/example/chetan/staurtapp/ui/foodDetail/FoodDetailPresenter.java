package com.example.chetan.staurtapp.ui.foodDetail;


import com.example.chetan.staurtapp.ui.model.Item;

/**
 * Created by chetan on 8/20/2016.
 */
public class FoodDetailPresenter implements FoodDetailContract.Presenter {

    private int quantity = 0;
    private FoodDetailContract.View foodDetailsView;
    private Item foodItem;

    private FoodDetailPresenter(FoodDetailContract.View view, Item item) {
        foodDetailsView = view;
        foodItem = item;
        quantity  = Integer.valueOf(item.getItemCount());
        updatePriceAndQuantity();
    }

    public static FoodDetailPresenter getPresenter(FoodDetailContract.View view, Item item) {
        return new FoodDetailPresenter(view, item);
    }

    @Override
    public void increaseItems() {
        quantity++;
        updatePriceAndQuantity();
    }

    private void updatePriceAndQuantity() {
        foodDetailsView.showPrice(quantity * Double.parseDouble(foodItem.getItemPrice()));
        foodDetailsView.showQuantity(quantity);
    }

    @Override
    public void decreaseItems() {
        if (quantity <= 0)
            return;
        quantity--;
        updatePriceAndQuantity();

    }

    @Override
    public void addToCart(Item foodItem) {
        if (quantity >= 0) {
            FoodCart.getInstance().addItem(foodItem, quantity);
            foodDetailsView.closeWindow();
        } else {
            foodDetailsView.showError();
        }

    }
}
