package com.example.firstdayjava.ui.views.ProductView;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firstdayjava.R;
import com.example.firstdayjava.pojo.local.entities.Category;

import java.util.ArrayList;
import java.util.List;

public class ProductViewAdapter extends RecyclerView.Adapter<ProductViewHolder> {
    List<Category> list = new ArrayList<>();

    public void setList(List<Category> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ProductViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.view_product,parent,false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        holder.bind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
