package com.example.firstdayjava.ui.views.address;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firstdayjava.R;
import com.example.firstdayjava.pojo.remote.models.get_address.GetAddressData;

import java.util.ArrayList;
import java.util.List;

public class AddressViewAdapter extends RecyclerView.Adapter<AddressViewHolder> {
    List<GetAddressData> list = new ArrayList<>();
    AddressViewListener listener;

    public AddressViewAdapter(AddressViewListener listener) {
        this.listener = listener;
    }

    public void setList(List<GetAddressData> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AddressViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_address, parent, false);
        return new AddressViewHolder(itemView,listener);
    }

    @Override
    public void onBindViewHolder(@NonNull AddressViewHolder holder, int position) {
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
