package com.example.firstdayjava.ui.views.BottomSheets;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.firstdayjava.databinding.BottomsheetSortBinding;
import com.example.firstdayjava.pojo.local.entities.setting.ProductPageFilter;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class ProductSortBottomSheet extends BottomSheetDialogFragment {
    BottomsheetSortBinding bd;
    FilterBottomSheetListener listener;
    ProductPageFilter filter;

    public ProductSortBottomSheet(FilterBottomSheetListener listener) {
        this.listener = listener;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        bd = BottomsheetSortBinding.inflate(LayoutInflater.from(requireContext()));
        initEvent();
        init();
        return bd.getRoot();
    }

    public void setFilter(ProductPageFilter filter) {
        this.filter = filter;
    }



    private void init() {
        if (filter != null) {
            setType();
            setSortOrder();
        }
    }

    private void initEvent() {
        bd.gridOptBtn.setOnClickListener(view -> {
            filter.setSortingType(ProductPageFilter.GRID_SHOW);
            setType();
        });

        bd.linearOptBtn.setOnClickListener(view -> {
            filter.setSortingType(ProductPageFilter.LINEAR_SHOW);
            setType();
        });

        bd.itemNameRB.setOnClickListener(view -> filter.setOrderBy(ProductPageFilter.SORT_BY_NAME));

        bd.priceHigherRB.setOnClickListener(view -> filter.setOrderBy(ProductPageFilter.SORT_BY_PRICE_HIGHER));

        bd.priceLowerRB.setOnClickListener(view -> filter.setOrderBy(ProductPageFilter.SORT_BY_PRICE_LOWER));

        bd.applyBtn.setOnClickListener(view -> listener.onSortOptionChange(filter));
    }

    private void setSortOrder() {
        String order = filter.getOrderBy();

        if (order.equals(ProductPageFilter.SORT_BY_NAME))
            bd.radioGroup.check(bd.itemNameRB.getId());
        else if (order.equals(ProductPageFilter.SORT_BY_PRICE_HIGHER))
            bd.radioGroup.check(bd.priceHigherRB.getId());
        else
            bd.radioGroup.check(bd.priceLowerRB.getId());
    }

    private void setType() {
        String type = filter.getSortingType();
        boolean isGridType = type.equals(ProductPageFilter.GRID_SHOW);
        if (isGridType) {
            bd.gridOptBtn.setColorFilter(Color.BLACK);
            bd.linearOptBtn.setColorFilter(Color.GRAY);
        } else {
            bd.gridOptBtn.setColorFilter(Color.GRAY);
            bd.linearOptBtn.setColorFilter(Color.BLACK);
        }
        filter.setSortingType(type);
    }


}
