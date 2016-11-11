package com.example.chetan.staurtapp.ui.foodDetail;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chetan.staurtapp.R;
import com.example.chetan.staurtapp.ui.home.HomeActivity;
import com.example.chetan.staurtapp.ui.model.Item;

import java.math.BigDecimal;
import java.text.Format;
import java.text.NumberFormat;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import util.Constants;

public class FoodDetailActivity extends AppCompatActivity implements FoodDetailContract.View {
    private int quantity = 0;
    private Item foodItem;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_details)
    TextView titleTextView;
    @BindView(R.id.tv_total)
    TextView tvPrice;
    @BindView(R.id.tv_integer_number)
    TextView tvQuantity;
    @BindView(R.id.tv_description)
    TextView tvDescription;
    @BindView(R.id.btn_decrease)
    Button btnDecrease;
    @BindView(R.id.btn_increase)
    Button btnIncrease;
    @BindView(R.id.btn_add_to_basket)
    Button btnConfirm;
    double price;
    private static final String TAG="FoodDetailActivity";
    private FoodDetailContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_detail);
        ButterKnife.bind(this);
        foodItem = (Item) getIntent().getSerializableExtra(Constants.EXTRA_KEY_FOOD_DETAIL);
        Format format = NumberFormat.getCurrencyInstance(new Locale("en", "in"));
        tvPrice.setText(format.format(new BigDecimal(foodItem.getItemPrice())));
        quantity=Integer.valueOf(foodItem.getItemCount());
        uiSetup();
        setToolBar();
        presenter = FoodDetailPresenter.getPresenter(this, foodItem);

    }
    private void uiSetup() {
        titleTextView.setText(foodItem.getItemName());
        tvDescription.setText(foodItem.getItemName());
        tvQuantity.setText(foodItem.getItemCount());
    }
    private void setToolBar(){
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
            foodItem = (Item) getIntent().getSerializableExtra(Constants.EXTRA_KEY_FOOD_DETAIL);
            String title=foodItem.getItemName();
            if (title != null || !title.isEmpty())
                getSupportActionBar().setTitle(title);
        }
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @OnClick(R.id.btn_decrease)
    public void decrease(){
        presenter.decreaseItems();
    }

    @OnClick(R.id.btn_increase)
    public void increase(){
        presenter.increaseItems();
    }

    @Override
    public void showQuantity(int quantity) {
        tvQuantity.setText("" + quantity);
    }

    @Override
    public void showPrice(Double price) {
        Format format = NumberFormat.getCurrencyInstance(new Locale("en", "in"));
        tvPrice.setText(format.format(new BigDecimal(price)));
    }

    @Override
    public void showError() {
        Toast.makeText(FoodDetailActivity.this, "No item selected", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void closeWindow() {
        finish();
    }

    @Override
    public Context getContext() {
        return this.getApplicationContext();
    }
    @OnClick(R.id.btn_add_to_basket)
    public void confirm(){
        FoodCart.getInstance().addItem(foodItem, quantity);
        finish();
    }
}
