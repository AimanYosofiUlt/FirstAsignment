package com.example.firstdayjava.ui.views.ProductView;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firstdayjava.R;
import com.example.firstdayjava.pojo.local.entities.Product;
import com.example.firstdayjava.pojo.local.entities.setting.ProductPageFilter;

import java.util.ArrayList;
import java.util.List;

public class ProductViewAdapter extends RecyclerView.Adapter<ProductViewHolder> {
    List<Product> list = new ArrayList<>();
    String showType;

    List<ProductViewHolder> viewList = new ArrayList<>();

    public ProductViewAdapter(String showType) {
        this.showType = showType;
    }

    public void setList(List<Product> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ProductViewHolder productViewHolder = new ProductViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.view_product, parent, false)
        );
        viewList.add(productViewHolder);
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
