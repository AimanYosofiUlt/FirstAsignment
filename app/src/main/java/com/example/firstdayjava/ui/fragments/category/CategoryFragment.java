package com.example.firstdayjava.ui.fragments.category;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import com.example.firstdayjava.databinding.FragmentCategoryBinding;
import com.example.firstdayjava.ui.fragments.ResponseState;
import com.example.firstdayjava.ui.viewpagers.adsviewpager.AdsPagerAdapter;
import com.example.firstdayjava.ui.views.CategoryView.CategoryAdapter;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class CategoryFragment extends Fragment {
    FragmentCategoryBinding bd;
    @Inject
    CategoryFragmentViewModel viewModel;

    CategoryAdapter adapter;

    AdsPagerAdapter adsPagerAdapter;
    private final Handler sliderHandler = new Handler();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        bd = FragmentCategoryBinding.inflate(inflater, container, false);

        initViewModel();
        initModelView();
        initEvent();
        viewModel.getCategories();
        return bd.getRoot();
    }

    private void initViewModel() {
        viewModel.categoriesMDL.observe(getViewLifecycleOwner(),
                categories -> adapter.setList(categories));

        viewModel.adsMDL.observe(getViewLifecycleOwner(),
                adsSliders -> adsPagerAdapter.setList(adsSliders));

        viewModel.responseStateMDL.observe(getViewLifecycleOwner(), new Observer<ResponseState>() {
            @Override
            public void onChanged(ResponseState responseState) {
                bd.waitPB.setVisibility(View.GONE);
                Toast.makeText(requireActivity(), responseState.getMessage(), Toast.LENGTH_SHORT).show();
                if (responseState.isSuccssful()) {
                    bd.offlineImg.setVisibility(View.GONE);
                } else {
                    bd.offlineImg.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    private void initModelView() {
        viewModel.getCategories();

        initViewPager();
    }

    private void initViewPager() {
        adapter = new CategoryAdapter(null);
        bd.recyclerView.setAdapter(adapter);
        bd.recyclerView.setLayoutManager(new GridLayoutManager(
                requireActivity(), 3
        ));

        adsPagerAdapter = new AdsPagerAdapter();
        bd.adsVP.setAdapter(adsPagerAdapter);

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
        bd.textView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bd.waitPB.setVisibility(View.GONE);
                Toast.makeText(requireContext(), "HI", Toast.LENGTH_SHORT).show();
                Log.d("CategoryFragment", "onChanged 5555: "+bd.waitPB.getVisibility());
                Log.d("CategoryFragment", "onChanged 6666: "+View.GONE);
                Log.d("CategoryFragment", "onChanged 7777: "+View.VISIBLE);
            }
        });
    }

    int d = 1;
    private final Runnable sliderRunnable = new Runnable() {
        @Override
        public void run() {
            if (bd.adsVP.getCurrentItem() == adsPagerAdapter.getItemCount() - 1) {
                d = -1;
            } else if (bd.adsVP.getCurrentItem() == 0) {
                d = 1;
            }

            bd.adsVP.setCurrentItem(bd.adsVP.getCurrentItem() + d);
        }
    };

}
