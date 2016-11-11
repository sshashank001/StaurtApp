package com.example.chetan.staurtapp.ui.orderFood;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.example.chetan.staurtapp.R;
import com.example.chetan.staurtapp.ui.foodDetail.FoodCart;
import com.example.chetan.staurtapp.ui.foodDetail.FoodDetailActivity;
import com.example.chetan.staurtapp.ui.model.Item;
import com.example.chetan.staurtapp.ui.model.OrderList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import util.Constants;
import util.RecyclerItemClickListener;

public class OrderFoodActivity extends AppCompatActivity {
    OrderList orderList;
    @BindView(R.id.list)
    RecyclerView orderDetailList;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    Context context;
    List<Item> foodList;
    FoodCart foodCart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_food_activty);
        ButterKnife.bind(this);
        setupToolbar();
        Intent intent = getIntent();
        orderList = (OrderList) intent.getSerializableExtra("OrderList");
        orderDetailList.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 1);
        orderDetailList.setLayoutManager(gridLayoutManager);
        foodList = orderList.getItems();
        for (Item item : foodList) {
            FoodCart.getInstance().addItem(item, Integer.valueOf(item.getItemCount()));
        }
        OrderFoodAdapter orderFoodAdapter = new OrderFoodAdapter(this);
        orderDetailList.setAdapter(orderFoodAdapter);
        orderDetailList.addOnItemTouchListener(
                new RecyclerItemClickListener(context, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        foodList = FoodCart.getInstance().getKeysAsArrayList();
                        Item foodItem = foodList.get(position);
                        HashMap<Item, Integer> cart = FoodCart.getInstance().getFoodItems();
                        int quantity = cart.get(foodItem);
                        Intent intent = new Intent(OrderFoodActivity.this, FoodDetailActivity.class);
                        intent.putExtra(Constants.EXTRA_KEY_FOOD_DETAIL, foodItem);
                        startActivity(intent);
                    }
                })
        );



    }
    private void setupToolbar() {
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);

        }
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FoodCart.destroy();
                finish();
            }
        });
    }

}
