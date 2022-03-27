package com.example.firstdayjava.ui.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.firstdayjava.R;
import com.example.firstdayjava.databinding.FragmentPrudoctPageBinding;
import com.example.firstdayjava.ui.viewpagers.prudoctlist.ProductListViewPagerAdapter;
import com.example.firstdayjava.ui.views.BottomSheets.ProductFilterBottomSheet;
import com.example.firstdayjava.ui.views.BottomSheets.ProductSortBottomSheet;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class ProductsPageFragment extends Fragment {
    FragmentPrudoctPageBinding bd;
    ProductListViewPagerAdapter pagerAdapter;

    ProductFilterBottomSheet filterBottomSheet;
    ProductSortBottomSheet sortBottomSheet;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        bd = FragmentPrudoctPageBinding.inflate(inflater, container, false);
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
        sortBottomSheet = new ProductSortBottomSheet();

        initEvent();
    }

    private void initEvent() {
        bd.showFilterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                filterBottomSheet.show(getParentFragmentManager(),"Filter");
            }
        });

        bd.showSortBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sortBottomSheet.show(getParentFragmentManager(),"Sort");
            }
        });
    }

    private void initPagerView() {
        pagerAdapter = new ProductListViewPagerAdapter(this);
        bd.viewPager.setAdapter(pagerAdapter);
        bd.viewPager.setUserInputEnabled(false);

        pagerAdapter.addFragment(new ProductListFragment());
        pagerAdapter.addFragment(new ProductListFragment());
        pagerAdapter.addFragment(new ProductListFragment());
        pagerAdapter.addFragment(new ProductListFragment());
        pagerAdapter.addFragment(new ProductListFragment());


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