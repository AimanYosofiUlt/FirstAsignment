package com.example.firstdayjava.ui.views.cart_info;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firstdayjava.R;
import com.example.firstdayjava.ui.views.ProductView.ProductViewData;
import com.example.firstdayjava.ui.views.ProductView.ProductViewListener;

import java.util.ArrayList;
import java.util.List;

public class CartInfoShowAdapter extends RecyclerView.Adapter<CartInfoViewHolder> {
    List<ProductViewData> list = new ArrayList<>();
    ProductViewListener listener;

    public CartInfoShowAdapter(ProductViewListener listener) {
        this.listener = listener;
    }

    public void setList(List<ProductViewData> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CartInfoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_cart_info, parent, false);
        CartInfoViewHolder cartInfoViewHolder = new CartInfoViewHolder(itemView, listener);
        return cartInfoViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CartInfoViewHolder holder, int position) {
        holder.bind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
