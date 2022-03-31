package com.example.firstdayjava.ui.views.cart_info;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firstdayjava.databinding.ViewCartInfoBinding;
import com.example.firstdayjava.ui.views.ProductView.ProductViewData;
import com.example.firstdayjava.ui.views.ProductView.ProductViewListener;

public class CartInfoViewHolder extends RecyclerView.ViewHolder {
    ViewCartInfoBinding bd;
    ProductViewData data;
    ProductViewListener listener;

    public CartInfoViewHolder(@NonNull View itemView, ProductViewListener listener) {
        super(itemView);
        bd = ViewCartInfoBinding.bind(itemView);
        this.listener = listener;
        initEvent();
    }

    void bind(ProductViewData productData) {
        this.data = productData;
        String amountStr = String.valueOf(productData.getAmount());
        bd.amoutTV.setText(amountStr);
        bd.nameTV.setText(productData.getName());
        String priceStr = String.valueOf(productData.getPrice());
        bd.priceTV.setText(priceStr);
    }

    private void initEvent() {
        bd.plusBtn.setOnClickListener(view -> {
            data.amount++;
            bd.amoutTV.setText(String.valueOf(data.amount));
            listener.onAmountChange(data);
        });

        bd.minusBtn.setOnClickListener(view -> {
            data.amount--;
            bd.amoutTV.setText(String.valueOf(data.amount));
            if (data.amount == 0) {
                listener.onAmountEmpty(data.getProduct());
            } else {
                listener.onAmountChange(data);
            }
        });

        bd.removeBtn.setOnClickListener(view -> listener.onAmountEmpty(data.getProduct()));
    }
}
