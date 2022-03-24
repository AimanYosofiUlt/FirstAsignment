package com.example.firstdayjava.ui.fragments.category;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import com.example.firstdayjava.databinding.FragmentCategoryBinding;
import com.example.firstdayjava.ui.viewpagers.adsviewpager.AdsPagerAdapter;
import com.example.firstdayjava.ui.views.CategoryView.CategoryAdapter;

public class CategoryFragment extends Fragment {
    FragmentCategoryBinding bd;
    CategoryFragmentViewModel viewModel;

    CategoryAdapter adapter;

    AdsPagerAdapter pagerAdapter;
    private final Handler sliderHandler = new Handler();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        bd = FragmentCategoryBinding.inflate(inflater, container, false);
        initViewModel();
        return bd.getRoot();
    }

    private void initViewModel() {
        viewModel = new ViewModelProvider(requireActivity()).get(CategoryFragmentViewModel.class);
        initModelView();
    }

    private void initModelView() {
        initViewPager();
        initEvent();
    }

    private void initViewPager() {
        adapter = new CategoryAdapter(null);
        bd.recyclerView.setAdapter(adapter);
        bd.recyclerView.setLayoutManager(new GridLayoutManager(
                requireActivity(), 3
        ));

        pagerAdapter = new AdsPagerAdapter();
        bd.adsVP.setAdapter(pagerAdapter);

        bd.adsVP.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_ALWAYS);

        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(40));
        compositePageTransformer.addTransformer((page, position) -> {
            float r = 1 - Math.abs(position);
            page.setScaleY(0.85f + r * 0.15f);
        });

        bd.adsVP.setPageTransformer(compositePageTransformer);

        bd.adsVP.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                sliderHandler.removeCallbacks(sliderRunnable);
                sliderHandler.postDelayed(sliderRunnable, 2000); // slide duration 2 seconds
            }
        });
    }


    private void initEvent() {

    }

    private void init() {

    }


    int d = 1;
    private Runnable sliderRunnable = new Runnable() {
        @Override
        public void run() {
            if (bd.adsVP.getCurrentItem() == pagerAdapter.getList().size() - 1) {
                d = -1;
            } else if (bd.adsVP.getCurrentItem() == 0) {
                d = 1;
            }

            bd.adsVP.setCurrentItem(bd.adsVP.getCurrentItem() + 1 * d);
        }
    };

}
