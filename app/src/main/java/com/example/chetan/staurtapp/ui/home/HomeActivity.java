package com.example.chetan.staurtapp.ui.home;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.example.chetan.staurtapp.R;
import com.example.chetan.staurtapp.ui.foodDetail.FoodCart;
import com.example.chetan.staurtapp.ui.model.Item;
import com.example.chetan.staurtapp.ui.model.OrderList;
import com.example.chetan.staurtapp.ui.model.StaurtAppWebService;
import com.example.chetan.staurtapp.ui.model.StaurtDetail;
import com.example.chetan.staurtapp.ui.orderFood.OrderFoodActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import util.RecyclerItemClickListener;

public class HomeActivity extends AppCompatActivity {
    @BindView(R.id.list)
    RecyclerView foodItemsList;
    Context context;
    StaurtDetail mGuest;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private static final String TAG="HomeActivity";
    List<OrderList> orderList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        setupToolbar();
        Intent mIntent = getIntent();
        mGuest = (StaurtDetail) mIntent.getSerializableExtra("STUART");
        String restaurantID=mGuest.getRestaurantID();
        foodItemsList.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 1);
        foodItemsList.setLayoutManager(gridLayoutManager);
        StaurtAppWebService webService= StaurtAppWebService.getInstance(HomeActivity.this);
        webService.StewardOrder(restaurantID, new StaurtAppWebService.OrderCallBack() {
            @Override
            public void onSuccess(List<OrderList> orderLists) {
                HomeAdapter orderFoodAdapter = new HomeAdapter(HomeActivity.this,orderLists);
                foodItemsList.setAdapter(orderFoodAdapter);
                orderList=orderLists;
                foodItemsList.addOnItemTouchListener(
                        new RecyclerItemClickListener(context, new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                OrderList itemList = orderList.get(position);
                                Intent intent=new Intent(HomeActivity.this,OrderFoodActivity.class);
                                intent.putExtra("OrderList", itemList);
                                startActivity(intent);

                            }
                        })
                );
            }

            @Override
            public void onFail(String msg) {

            }
        });
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
                finish();
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();

    }
}
