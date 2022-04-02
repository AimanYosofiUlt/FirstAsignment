package com.example.firstdayjava.ui.fragments.order;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.example.firstdayjava.databinding.FragmentOrderPageBinding;
public class OrderPageFragment extends Fragment {
    FragmentOrderPageBinding bd;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        bd = FragmentOrderPageBinding.inflate(inflater, container, false);
        return bd.getRoot();
    }
}
