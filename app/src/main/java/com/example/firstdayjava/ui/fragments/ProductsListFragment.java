package com.example.firstdayjava.ui.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.firstdayjava.R;
import com.example.firstdayjava.databinding.FragmentPrudoctListBinding;
import com.example.firstdayjava.ui.viewpagers.prudoctlist.ProductListViewPagerAdapter;
import com.example.firstdayjava.ui.views.BottomSeets.ProductFilterBottomSheet;
import com.example.firstdayjava.ui.views.CategoryView.CategoryViewHolder;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.Random;

public class ProductsListFragment extends Fragment {
    FragmentPrudoctListBinding bd;
    ProductListViewPagerAdapter pagerAdapter;

    ProductFilterBottomSheet filterBottomSheet;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        bd = FragmentPrudoctListBinding.inflate(inflater, container, false);
        return bd.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        init();
    }

    private void init() {
        initPagerView();
        filterBottomSheet = new ProductFilterBottomSheet();

        initEvent();
    }

    private void initEvent() {
        bd.showFilterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                filterBottomSheet.show(getParentFragmentManager(),"HI");
            }
        });
    }

    private void initPagerView() {
        pagerAdapter = new ProductListViewPagerAdapter(this);
        bd.viewPager.setAdapter(pagerAdapter);
        bd.viewPager.setUserInputEnabled(false);

        pagerAdapter.addFragment(new PShowFragment());
        pagerAdapter.addFragment(new PShowFragment());
        pagerAdapter.addFragment(new PShowFragment());
        pagerAdapter.addFragment(new PShowFragment());
        pagerAdapter.addFragment(new PShowFragment());


        TabLayoutMediator layoutMediator = new TabLayoutMediator(bd.categoryTabL, bd.viewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                View view = LayoutInflater.from(bd.getRoot().getContext()).inflate(R.layout.view_categoryfortab, null);
                ((TextView) view.findViewById(R.id.categoryNameTV)).setText("Pruduct");
                tab.setCustomView(view);
            }
        });
        layoutMediator.attach();
    }
}