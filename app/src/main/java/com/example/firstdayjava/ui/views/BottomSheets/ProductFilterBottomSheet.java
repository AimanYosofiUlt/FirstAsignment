package com.example.firstdayjava.ui.views.BottomSheets;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.firstdayjava.databinding.BottomsheetFilterBinding;
import com.example.firstdayjava.pojo.local.entities.setting.ProductPageFilter;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class ProductFilterBottomSheet extends BottomSheetDialogFragment {
    BottomsheetFilterBinding bd;
    FilterBottomSheetListener listener;
    ProductPageFilter filter;

    public ProductFilterBottomSheet(ProductPageFilter filter, FilterBottomSheetListener listener) {
        this.filter = filter;
        this.listener = listener;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        bd = BottomsheetFilterBinding.inflate(LayoutInflater.from(requireContext()));

        init();
        initEvent();

        return bd.getRoot();
    }

    private void init() {
        Integer maxRange = filter.getMaxRange();
        Integer minRange = filter.getMinRange();
        bd.rangeSeekbar.setRangeValues(maxRange, maxRange);

        String maxStr = String.valueOf(maxRange);
        String minStr = String.valueOf(minRange);
        bd.maxTV.setText(maxStr);
        bd.minTV.setText(minStr);
    }


    private void initEvent() {
        bd.rangeSeekbar.setOnRangeSeekBarChangeListener((bar, minValue, maxValue) -> {
            bd.maxTV.setText(String.valueOf(maxValue));
            bd.minTV.setText(String.valueOf(minValue));
        });

        bd.applyBtn.setOnClickListener(view -> {
            Integer max = Integer.parseInt(bd.maxTV.getText().toString());
            Integer min = Integer.parseInt(bd.minTV.getText().toString());
            filter.setMaxRange(max);
            filter.setMinRange(min);
            listener.onSortOptionChange(filter);
        });

        bd.restartBtn.setOnClickListener(view -> bd.rangeSeekbar.setRangeValues(0, 1000));
    }
}
