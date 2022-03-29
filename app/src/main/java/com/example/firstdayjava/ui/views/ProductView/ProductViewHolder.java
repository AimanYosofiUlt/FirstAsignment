package com.example.firstdayjava.ui.views.ProductView;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.firstdayjava.R;
import com.example.firstdayjava.databinding.ViewProductBinding;
import com.example.firstdayjava.pojo.local.entities.Product;
import com.example.firstdayjava.pojo.local.entities.setting.ProductPageFilter;

public class ProductViewHolder extends RecyclerView.ViewHolder {
    ViewProductBinding bd;

    public ProductViewHolder(@NonNull View itemView) {
        super(itemView);
        bd = ViewProductBinding.bind(itemView);
        initEvent();
    }

    private void initEvent() {
        bd.addBtn.setOnClickListener(view -> {

        });
    }

    public void bind(Product product, String showType) {
        String imageUrl = product.getImageUrl();
        changeLayout(showType);
        if (imageUrl.equals("null")) {
            Glide.with(itemView.getContext())
                    .load(R.drawable.ic_baseline_wifi_off_24)
                    .error(R.drawable.ic_baseline_wifi_off_24)
                    .into(bd.productImg);
        } else {
            Glide.with(itemView.getContext())
                    .load(imageUrl)
                    .error(R.drawable.ic_baseline_wifi_off_24)
                    .into(bd.productImg);
        }


        bd.nameTV.setText(product.getName());

        String price = String.valueOf(product.getPrice());
        bd.priceTV.setText(price);
    }

    public void changeLayout(String showType) {
        if (showType.equals(ProductPageFilter.GRID_SHOW)) {
            bd.addBtn.setBackgroundDrawable(ContextCompat.getDrawable(itemView.getContext(),R.drawable.back_product_bottom));
            bd.addBtn.setTextColor(ContextCompat.getColor(itemView.getContext(),R.color.white));
            bd.motionLayout.transitionToStart();
        } else {
            bd.addBtn.setBackgroundDrawable(ContextCompat.getDrawable(itemView.getContext(),R.drawable.back_product_bottom2));
            bd.addBtn.setTextColor(ContextCompat.getColor(itemView.getContext(),R.color.blue));
            bd.motionLayout.transitionToEnd();
        }
    }
}
