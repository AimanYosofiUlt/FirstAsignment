package com.example.firstdayjava.ui.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import com.example.firstdayjava.R;
import com.example.firstdayjava.databinding.FragmentCategoryBinding;
import com.example.firstdayjava.ui.viewpagers.adsviewpager.AdsPagerAdapter;
import com.example.firstdayjava.ui.views.CategoryView.Category;
import com.example.firstdayjava.ui.views.CategoryView.CategoryAdapter;

public class CategoryFragment extends Fragment {
    FragmentCategoryBinding bd;

    CategoryAdapter adapter;

    AdsPagerAdapter pagerAdapter;
    private Handler sliderHandler = new Handler();

    private int[] drawables = {R.drawable.image, R.drawable.image2, R.drawable.image3};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        bd = FragmentCategoryBinding.inflate(inflater,container,false);
        init();
        return bd.getRoot();
    }

    private void init() {
        initViewPager();
    }

    private void initViewPager() {
        adapter = new CategoryAdapter(null);
        bd.recyclerView.setAdapter(adapter);
        bd.recyclerView.setLayoutManager(new GridLayoutManager(
                requireActivity(), 3
        ));

        addCategory();

        pagerAdapter = new AdsPagerAdapter();
        bd.adsVP.setAdapter(pagerAdapter);

        for (int drawable : drawables) {
            pagerAdapter.getList().add(drawable);
        }

        bd.adsVP.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_ALWAYS);

        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(40));
        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float r = 1 - Math.abs(position);
                page.setScaleY(0.85f + r * 0.15f);
            }
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

    private void addCategory() {
        adapter.getList().add(
                new Category("Books", Color.RED)
        );
        adapter.getList().add(
                new Category("Books", Color.RED)
        );
        adapter.getList().add(
                new Category("Books", Color.RED)
        );
        adapter.getList().add(
                new Category("Books", Color.RED)
        );
        adapter.getList().add(
                new Category("Books", Color.RED)
        );
        adapter.getList().add(
                new Category("Books", Color.RED)
        );
        adapter.getList().add(
                new Category("Books", Color.RED)
        );
        adapter.getList().add(
                new Category("Books", Color.RED)
        );
        adapter.getList().add(
                new Category("Books", Color.RED)
        );
        adapter.getList().add(
                new Category("Books", Color.RED)
        );
        adapter.getList().add(
                new Category("Books", Color.RED)
        );
        adapter.getList().add(
                new Category("Books", Color.RED)
        );
        adapter.getList().add(
                new Category("Books", Color.RED)
        );
        adapter.getList().add(
                new Category("Books", Color.RED)
        );
        adapter.getList().add(
                new Category("Books", Color.RED)
        );
        adapter.getList().add(
                new Category("Books", Color.RED)
        );
        adapter.getList().add(
                new Category("Books", Color.RED)
        );
        adapter.getList().add(
                new Category("Books", Color.RED)
        );

    }
}
