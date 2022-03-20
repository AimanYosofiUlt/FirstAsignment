package com.example.firstdayjava.ui.views.BottomSeets;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.firstdayjava.databinding.BottomsheetFilterBinding;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class ProductFilterBottomSheet extends BottomSheetDialogFragment {
    BottomsheetFilterBinding bd;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        bd = BottomsheetFilterBinding.inflate(LayoutInflater.from(requireContext()));

        init();

        return bd.getRoot();
    }

    private void init() {

    }
}
