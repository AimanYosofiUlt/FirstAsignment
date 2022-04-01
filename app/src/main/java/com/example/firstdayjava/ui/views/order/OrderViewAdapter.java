package com.example.firstdayjava.ui.views.order;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firstdayjava.R;
import com.example.firstdayjava.pojo.remote.models.getOrder.OrderMaster;

import java.util.ArrayList;
import java.util.List;

public class OrderViewAdapter extends RecyclerView.Adapter<OrderViewHolder> {
    List<OrderMaster> list = new ArrayList<>();

    public void setList(List<OrderMaster> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_order, parent, false);
        return new OrderViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        holder.bind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void clearData() {
        list.clear();
        notifyDataSetChanged();
    }
}
