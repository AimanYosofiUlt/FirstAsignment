package com.example.firstdayjava.ui.views.ProductView;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.firstdayjava.R;
import com.example.firstdayjava.databinding.ViewProductBinding;
import com.example.firstdayjava.pojo.local.entities.setting.ProductPageFilter;

public class ProductViewHolder extends RecyclerView.ViewHolder {
    ViewProductBinding bd;
    ProductViewData data;

    ProductViewListener listener;

    public ProductViewHolder(@NonNull View itemView, ProductViewListener listener) {
        super(itemView);
        bd = ViewProductBinding.bind(itemView);
        this.listener = listener;
        initEvent();
    }

    private void initEvent() {
        bd.mainCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onProductShowReq(data);

                Animation animation = AnimationUtils.loadAnimation(itemView.getContext(), com.example.firstdayjava.R.anim.bounce);
                bd.mainCard.startAnimation(animation);
            }
        });
        bd.addBtn.setOnClickListener(view -> {
            data.amount++;
            bd.amoutTV.setText(String.valueOf(data.amount));

            bd.addBtn.setVisibility(View.INVISIBLE);
            bd.counterCL.setVisibility(View.VISIBLE);
            listener.onAmountChange(data);
        });

        bd.plusBtn.setOnClickListener(view -> {
            data.amount++;
            bd.amoutTV.setText(String.valueOf(data.amount));
            listener.onAmountChange(data);
        });

        bd.minusBtn.setOnClickListener(view -> {
            data.amount--;
            bd.amoutTV.setText(String.valueOf(data.amount));
            if (data.amount == 0) {
                bd.addBtn.setVisibility(View.VISIBLE);
                bd.counterCL.setVisibility(View.INVISIBLE);
                listener.onAmountEmpty(data.getProduct());
            } else {
                listener.onAmountChange(data);
            }
        });
    }

    public void bind(ProductViewData data, String showType) {
        this.data = data;
        changeLayout(showType);
        initLayout(data);
    }

    private void initLayout(ProductViewData data) {
        String imageUrl = data.getImageUrl();
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
        bd.nameTV.setText(data.getName());
        String price = String.valueOf(data.getPrice());
        bd.priceTV.setText(price);

        if (data.getAmount() > 0) {
            bd.addBtn.setVisibility(View.INVISIBLE);
            bd.counterCL.setVisibility(View.VISIBLE);
            bd.amoutTV.setText(String.valueOf(data.getAmount()));
        } else {
            bd.addBtn.setVisibility(View.VISIBLE);
            bd.counterCL.setVisibility(View.INVISIBLE);
            bd.amoutTV.setText("0");
        }
    }

    public void changeLayout(String showType) {
        if (showType.equals(ProductPageFilter.GRID_SHOW)) {
            bd.addBtn.setBackgroundDrawable(ContextCompat.getDrawable(itemView.getContext(), R.drawable.back_product_bottom));
            bd.addBtn.setTextColor(ContextCompat.getColor(itemView.getContext(), R.color.white));
            bd.motionLayout
                    .transitionToStart();
            bd.counterCL.setBackground(ContextCompat.getDrawable(itemView.getContext(),R.drawable.back_product_bottom_solid));
            bd.amoutTV.setTextColor(ContextCompat.getColor(itemView.getContext(),R.color.white));
            bd.plusBtn.setColorFilter(ContextCompat.getColor(itemView.getContext(),R.color.white));
            bd.minusBtn.setColorFilter(ContextCompat.getColor(itemView.getContext(),R.color.white));
        } else {
            bd.addBtn.setBackgroundDrawable(ContextCompat.getDrawable(itemView.getContext(),R.drawable.back_product_bottom2));
            bd.addBtn.setTextColor(ContextCompat.getColor(itemView.getContext(),R.color.blue));
            bd.counterCL.setBackground(ContextCompat.getDrawable(itemView.getContext(),R.drawable.back_product_bottom2_solid));
            bd.amoutTV.setTextColor(ContextCompat.getColor(itemView.getContext(),R.color.blue));
            bd.plusBtn.setColorFilter(ContextCompat.getColor(itemView.getContext(),R.color.blue));
            bd.minusBtn.setColorFilter(ContextCompat.getColor(itemView.getContext(),R.color.blue));
            bd.motionLayout.transitionToEnd();
        }
    }
}
