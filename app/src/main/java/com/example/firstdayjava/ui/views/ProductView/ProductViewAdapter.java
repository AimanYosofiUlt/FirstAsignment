package com.example.firstdayjava.ui.views.ProductView;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firstdayjava.R;

import java.util.ArrayList;
import java.util.List;

public class ProductViewAdapter extends RecyclerView.Adapter<ProductViewHolder> {
    List<ProductCardData> list = new ArrayList<>();
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

    public void addProduct(String name){
        list.add(new ProductCardData(
                name,150
        ));
    }
}
