package com.example.chetan.staurtapp.ui.orderFood;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.chetan.staurtapp.R;
import com.example.chetan.staurtapp.ui.foodDetail.FoodCart;
import com.example.chetan.staurtapp.ui.model.Item;
import com.squareup.picasso.Picasso;

import java.text.Format;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

/**
 * Created by chetan on 11/9/2016.
 */
public class OrderFoodAdapter extends RecyclerView.Adapter<OrderFoodAdapter.ContactViewHolder> {
    List<Item> itemList= FoodCart.getInstance().getKeysAsArrayList();
    OrderFoodActivity activity;
    public OrderFoodAdapter(OrderFoodActivity orderDetailActivity) {
        this.activity = orderDetailActivity;

    }

    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).
                inflate(R.layout.item_food_list, viewGroup, false);
        return new ContactViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ContactViewHolder holder, int position) {
        holder.tvItemName.setText(itemList.get(position).getItemName());
        holder.tvQuantity.setText(itemList.get(position).getItemCount());
        Context context = holder.productImage.getContext();
        if(!itemList.get(position).getItemImage().isEmpty())
            Picasso.with(context).load(itemList.get(position).getItemImage()).into(holder.productImage);
        else
            holder.productImage.setImageResource(R.drawable.logo);
        Double price= Double.parseDouble(itemList.get(position).getItemPrice());
        Format format = NumberFormat.getCurrencyInstance(new Locale("en", "in"));
        holder.tvPrice.setText(format.format(price));
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public static class ContactViewHolder extends RecyclerView.ViewHolder {
        protected ImageView productImage;
        protected TextView tvItemName;
        protected TextView tvPrice;
        protected TextView tvQuantity;

        public ContactViewHolder(View v) {
            super(v);
            productImage = (ImageView) v.findViewById(R.id.img_product);
            tvItemName = (TextView) v.findViewById(R.id.tv_item_name);
            tvPrice = (TextView) v.findViewById(R.id.tv_price);
            tvQuantity=(TextView)v.findViewById(R.id.tv_quantity);
        }
    }
}