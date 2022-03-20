package com.example.firstdayjava.ui.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.firstdayjava.R;
import com.example.firstdayjava.databinding.FragmentPrudoctListBinding;
import com.example.firstdayjava.databinding.FragmentPrudoctShowBinding;
import com.example.firstdayjava.ui.views.ProductView.ProductViewAdapter;
import com.example.firstdayjava.ui.views.TypeCardView.TypeCardViewAdapter;

import java.util.Objects;
import java.util.Random;

public class PShowFragment extends Fragment {
    FragmentPrudoctShowBinding bd;

    TypeCardViewAdapter typeAdapter;
    ProductViewAdapter productAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        bd = FragmentPrudoctShowBinding.inflate(inflater, container, false);

        init();

        return bd.getRoot();
    }

    private void init() {
        typeAdapter = new TypeCardViewAdapter();
        bd.typeRV.setAdapter(typeAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(requireContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        bd.typeRV.setLayoutManager(linearLayoutManager);

        productAdapter = new ProductViewAdapter();
        bd.prudoctRV.setAdapter(productAdapter);
        bd.prudoctRV.setLayoutManager(new GridLayoutManager(requireContext(), 2));

        productAdapter.addProduct("Computer");
        productAdapter.addProduct("Laptop");
        productAdapter.addProduct("Phones");
        productAdapter.addProduct("Tablet");
        productAdapter.addProduct("Screens");
        productAdapter.addProduct("Chargers");

        addTypes();

    }

    private void addTypes() {
        typeAdapter.addTypeCard("Quartz");
        typeAdapter.addTypeCard("Digital");
        typeAdapter.addTypeCard("Smart");
        typeAdapter.addTypeCard("Analog");
        typeAdapter.addTypeCard("Quartz");
        typeAdapter.addTypeCard("Digital");
        typeAdapter.addTypeCard("Smart");
        typeAdapter.addTypeCard("Analog");
        typeAdapter.addTypeCard("Quartz");
        typeAdapter.addTypeCard("Digital");
        typeAdapter.addTypeCard("Smart");
        typeAdapter.addTypeCard("Analog");
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}