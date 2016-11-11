package com.example.chetan.staurtapp.ui.home;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.chetan.staurtapp.R;
import com.example.chetan.staurtapp.ui.model.OrderList;

import java.text.Format;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by chetan on 11/7/2016.
 */
public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ContactViewHolder> {
    List<OrderList> orderList;
    HomeActivity activity;
    String restaurantId;

    public HomeAdapter(HomeActivity activity,List<OrderList> foodList) {
        this.orderList=foodList;
        this.activity=activity;
    }


    public static class ContactViewHolder extends RecyclerView.ViewHolder {
        protected TextView tvOrderId;
        protected TextView tvPrice;
        protected TextView tvRoomNo;

        public ContactViewHolder(View v) {
            super(v);
            tvOrderId=(TextView)v.findViewById(R.id.tv_order_id);
            tvPrice=(TextView)v.findViewById(R.id.tv_price);
            tvRoomNo=(TextView)v.findViewById(R.id.tv_room);



        }
    }

    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).
                inflate(R.layout.order_food_history_list, viewGroup, false);
        return new ContactViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(ContactViewHolder holder, int position) {
        holder.tvOrderId.setText(orderList.get(position).getOrderNo());
        holder.tvRoomNo.setText(orderList.get(position).getRoomNumber());
        Double price= Double.parseDouble(orderList.get(position).getTotalPrice());
        Format format = NumberFormat.getCurrencyInstance(new Locale("en", "in"));
        holder.tvPrice.setText(format.format(price));
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }
}

