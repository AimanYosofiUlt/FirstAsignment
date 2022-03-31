package com.example.firstdayjava.ui.views.ProductView;

import com.example.firstdayjava.pojo.local.entities.Product;

public interface ProductViewListener {
    void onAmountChange(ProductViewData data);

    void onAmountEmpty(Product product);
}
