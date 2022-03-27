package com.example.firstdayjava.ui.views.BottomSheets;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.example.firstdayjava.databinding.BottomsheetSortBinding;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class ProductSortBottomSheet extends BottomSheetDialogFragment {
    BottomsheetSortBinding bd;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        bd = BottomsheetSortBinding.inflate(LayoutInflater.from(requireContext()));

        init();

        return bd.getRoot();
    }

    private void init() {

    }
}
