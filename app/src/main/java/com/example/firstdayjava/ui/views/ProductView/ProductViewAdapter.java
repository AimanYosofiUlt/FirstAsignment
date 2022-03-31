package com.example.firstdayjava.ui.views.ProductView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firstdayjava.R;
import com.example.firstdayjava.pojo.local.entities.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductViewAdapter extends RecyclerView.Adapter<ProductViewHolder> {
    List<ProductViewData> list = new ArrayList<>();
    String showType;

    ProductViewListener listener;


    public ProductViewAdapter(String showType, ProductViewListener listener) {
        this.showType = showType;
        this.listener = listener;
    }

    public void setList(List<ProductViewData> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_product, parent, false);
        ProductViewHolder productViewHolder = new ProductViewHolder(itemView, listener);
        return productViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        holder.bind(list.get(position), showType);
    }


    @Override
    public int getItemCount() {
        return list.size();
    }
}
