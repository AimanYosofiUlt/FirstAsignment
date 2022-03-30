package com.example.firstdayjava.ui.fragments.product_page;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.firstdayjava.R;
import com.example.firstdayjava.databinding.FragmentPrudoctPageBinding;
import com.example.firstdayjava.ui.fragments.base.BaseFragment;
import com.example.firstdayjava.ui.viewpagers.prudoctlist.ProductListViewPagerAdapter;
import com.example.firstdayjava.ui.views.BottomSheets.ProductFilterBottomSheet;
import com.example.firstdayjava.ui.views.BottomSheets.ProductSortBottomSheet;
import com.google.android.material.tabs.TabLayoutMediator;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class ProductsPageFragment extends BaseFragment {
    FragmentPrudoctPageBinding bd;
    ProductListViewPagerAdapter pagerAdapter;

    ProductFilterBottomSheet filterBottomSheet;
    ProductSortBottomSheet sortBottomSheet;

    @Inject
    ProductsPageFragmentViewModel viewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        bd = FragmentPrudoctPageBinding.inflate(inflater, container, false);

        initViewModel();
        initModelView();
        initEvent();
        return bd.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();
        viewModel.getProducts();
        viewModel.initFilter();
    }

    @Override
    protected void initViewModel() {
        viewModel.categoryMDL.observe(getViewLifecycleOwner(),
                categories -> {
                    pagerAdapter.setFragmentsByCategories(categories);
                    Log.d("ProductsPageFragment", "initViewModel: 98896 " + categories.size());
                });

        viewModel.responseStateMDL.observe(getViewLifecycleOwner(), responseState -> {
            if (!responseState.isSuccssful()) {
                // TODO: 3/28/22 show offline image
                Toast.makeText(requireContext(), responseState.getMessage(), Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(requireContext(), responseState.getMessage(), Toast.LENGTH_SHORT).show();
            }

            Log.d("ProductListFragment", "onChanged: 98455 " + responseState.getMessage());
        });

        viewModel.filterLiveData.observe(getViewLifecycleOwner(), filter -> {

            filterBottomSheet = new ProductFilterBottomSheet(filter,
                    fromSheetFilter -> viewModel.updateProductFilter(fromSheetFilter));


            sortBottomSheet = new ProductSortBottomSheet(filter,
                    fromSheetFilter -> viewModel.updateProductFilter(fromSheetFilter));
            Log.d("ProductsPageFragment", "initViewModel: 75621 :" + filter.getOrderBy());
        });
    }

    @Override
    protected void initModelView() {
        initPagerView();
    }


    @Override
    protected void initEvent() {
        bd.showFilterBtn.setOnClickListener(view ->
                filterBottomSheet.show(getParentFragmentManager(), "Filter"));

        bd.showSortBtn.setOnClickListener(view ->
                sortBottomSheet.show(getParentFragmentManager(), "Sort"));
    }

    private void initPagerView() {
        pagerAdapter = new ProductListViewPagerAdapter(this);
        bd.viewPager.setAdapter(pagerAdapter);
        bd.viewPager.setUserInputEnabled(false);

        TabLayoutMediator layoutMediator = new TabLayoutMediator(bd.categoryTabL, bd.viewPager, (tab, position) -> {
            @SuppressLint("InflateParams") View view = LayoutInflater.from(bd.getRoot().getContext()).inflate(R.layout.view_categoryfortab, null);
            String name = pagerAdapter.getCategories().get(position).getCategoryName();
            ((TextView) view.findViewById(R.id.categoryNameTV)).setText(name);
            tab.setCustomView(view);
        });
        layoutMediator.attach();
    }
}