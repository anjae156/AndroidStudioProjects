package com.example.an.listpasing;

import android.app.Activity;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;


public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.CustomViewHolder> {

    private ArrayList<ListData> mList = null;
    private Activity context = null;


    public UsersAdapter(Activity context, ArrayList<ListData> list) {
        this.context = context;
        this.mList = list;
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {
        protected TextView restaurantNum;
        protected TextView restaurantName;
        protected TextView tel;
        protected TextView adress;



        public CustomViewHolder(View view) {
            super(view);
            this.restaurantNum = (TextView) view.findViewById(R.id.textView_list_num);
            this.restaurantName = (TextView)view.findViewById(R.id.textView_list_name);
            this.tel = (TextView)view.findViewById(R.id.textView_list_tel);
            this.adress = (TextView)view.findViewById(R.id.textView_list_ad);

        }
    }


    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_list, null);
        CustomViewHolder viewHolder = new CustomViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder viewholder, int position) {

        viewholder.restaurantNum.setText(mList.get(position).getRestaurantNum());
        viewholder.restaurantName.setText(mList.get(position).getRestaurantName());
        viewholder.tel.setText(mList.get(position).getTel());
        viewholder.adress.setText(mList.get(position).getAdress());

    }

    @Override
    public int getItemCount() {
        return (null != mList ? mList.size() : 0);
    }



}