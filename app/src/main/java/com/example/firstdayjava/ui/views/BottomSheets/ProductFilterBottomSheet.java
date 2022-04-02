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
import com.mohammedalaa.seekbar.DoubleValueSeekBarView;
import com.mohammedalaa.seekbar.OnDoubleValueSeekBarChangeListener;

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
        bd.rangeSeekbar.setCurrentMaxValue(1000);
        bd.rangeSeekbar.setCurrentMinValue(0);

        String maxStr = String.valueOf(maxRange);
        String minStr = String.valueOf(minRange);
        bd.maxTV.setText(maxStr);
        bd.minTV.setText(minStr);
    }


    private void initEvent() {
        bd.rangeSeekbar.setOnRangeSeekBarViewChangeListener(new OnDoubleValueSeekBarChangeListener() {
            @Override
            public void onValueChanged(@Nullable DoubleValueSeekBarView doubleValueSeekBarView, int i, int i1, boolean b) {
                bd.maxTV.setText(String.valueOf(bd.rangeSeekbar.getCurrentMaxValue()));
                bd.minTV.setText(String.valueOf(bd.rangeSeekbar.getCurrentMinValue()));
            }

            @Override
            public void onStartTrackingTouch(@Nullable DoubleValueSeekBarView doubleValueSeekBarView, int i, int i1) {

            }

            @Override
            public void onStopTrackingTouch(@Nullable DoubleValueSeekBarView doubleValueSeekBarView, int i, int i1) {

            }
        });

        bd.applyBtn.setOnClickListener(view -> {
            Integer max = Integer.parseInt(bd.maxTV.getText().toString());
            Integer min = Integer.parseInt(bd.minTV.getText().toString());
            filter.setMaxRange(max);
            filter.setMinRange(min);
            listener.onSortOptionChange(filter);
        });

        bd.restartBtn.setOnClickListener(view -> {
            bd.rangeSeekbar.setCurrentMaxValue(1000);
            bd.rangeSeekbar.setCurrentMinValue(0);
            bd.maxTV.setText(String.valueOf(1000));
            bd.minTV.setText(String.valueOf(0));
        });

        bd.cancelBtn.setOnClickListener(view -> ProductFilterBottomSheet.this.dismiss());
    }
}
