package com.example.firstdayjava.ui.fragments.product_info;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;

import com.bumptech.glide.Glide;
import com.example.firstdayjava.R;
import com.example.firstdayjava.databinding.FragmentProductInfoBinding;
import com.example.firstdayjava.pojo.local.entities.Product;
import com.example.firstdayjava.pojo.local.entities.setting.ProductPageFilter;
import com.example.firstdayjava.ui.fragments.base.BaseFragment;
import com.example.firstdayjava.ui.views.ProductView.ProductViewAdapter;
import com.example.firstdayjava.ui.views.ProductView.ProductViewData;
import com.example.firstdayjava.ui.views.ProductView.ProductViewListener;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class ProductInfoFragment extends BaseFragment {
    @Inject
    ProductsInfoFragmentViewModel viewModel;

    FragmentProductInfoBinding bd;
    ProductViewData data;

    ProductViewAdapter otherAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        bd = FragmentProductInfoBinding.inflate(inflater, container, false);

        initModelView();
        initViewModel();
        initEvent();
        return bd.getRoot();
    }

    @Override
    protected void initViewModel() {
        viewModel.otherProductDML.observe(getViewLifecycleOwner(), dataList -> otherAdapter.setList(dataList));

        viewModel.faveStateLiveData.observe(getViewLifecycleOwner(), favState -> {
            if (favState == Product.NOT_IN_FAV_STATE) {
                bd.favImage.setColorFilter(Color.LTGRAY);
            } else {
                bd.favImage.setColorFilter(ContextCompat.getColor(requireContext(), R.color.blue));
            }
        });
    }

    @Override
    protected void initModelView() {
        data = ProductInfoFragmentArgs.fromBundle(getArguments()).getProductData();
        viewModel.getFavState(data.getItemCode());

        String priceStr = data.getPrice() + " LE";
        bd.priceTV.setText(priceStr);
        bd.nameTV.setText(data.getName());
        bd.descriptionTV.setText(String.valueOf(data.getDescription()));

        if (data.getAmount() > 0) {
            bd.addBtn.setVisibility(View.INVISIBLE);
            bd.counterCL.setVisibility(View.VISIBLE);
            bd.amoutTV.setText(String.valueOf(data.getAmount()));
        } else {
            bd.addBtn.setVisibility(View.VISIBLE);
            bd.counterCL.setVisibility(View.INVISIBLE);
            bd.amoutTV.setText("0");
        }

        Glide.with(requireContext())
                .load(data.getImageUrl())
                .into(bd.productImage);

        otherAdapter = new ProductViewAdapter(ProductPageFilter.GRID_SHOW, new ProductViewListener() {
            @Override
            public void onAmountChange(ProductViewData data) {
                viewModel.addToCart(data.amount, data.getProduct());
            }

            @Override
            public void onAmountEmpty(Product product) {
                viewModel.deleteFromCart(product);
            }

            @Override
            public void onProductShowReq(ProductViewData data) {

            }
        });

        bd.otherProductRV.setLayoutManager(new GridLayoutManager(requireContext(), 2));
        bd.otherProductRV.setAdapter(otherAdapter);

        viewModel.getOtherProduct(data.getProduct().getItemCode(), data.getCategoryCode());
    }


    @Override
    protected void initEvent() {
        bd.addBtn.setOnClickListener(view -> {
            data.amount++;
            bd.amoutTV.setText(String.valueOf(data.amount));

            bd.addBtn.setVisibility(View.INVISIBLE);
            bd.counterCL.setVisibility(View.VISIBLE);
        });

        bd.plusBtn.setOnClickListener(view -> {
            data.amount++;
            bd.amoutTV.setText(String.valueOf(data.amount));
            viewModel.addToCart(data.amount, data.getProduct());
        });

        bd.minusBtn.setOnClickListener(view -> {
            data.amount--;
            bd.amoutTV.setText(String.valueOf(data.amount));
            if (data.amount == 0) {
                bd.addBtn.setVisibility(View.VISIBLE);
                bd.counterCL.setVisibility(View.INVISIBLE);
                viewModel.deleteFromCart(data.getProduct());
            } else {
                viewModel.addToCart(data.amount, data.getProduct());
            }
        });

        bd.favImage.setOnClickListener(view -> {
            if (data.getFavState() == Product.NOT_IN_FAV_STATE)
                data.setFavState(Product.IN_FAV_STATE);
            else
                data.setFavState(Product.NOT_IN_FAV_STATE);
            viewModel.setProductFavState(data.getProduct());

        });
    }
}