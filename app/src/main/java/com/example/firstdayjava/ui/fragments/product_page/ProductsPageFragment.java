package com.example.firstdayjava.ui.fragments.product_page;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.navigation.fragment.NavHostFragment;

import com.example.firstdayjava.R;
import com.example.firstdayjava.databinding.FragmentPrudoctPageBinding;
import com.example.firstdayjava.pojo.local.entities.Category;
import com.example.firstdayjava.ui.fragments.base.BaseFragment;
import com.example.firstdayjava.ui.fragments.product_list.ProductListListener;
import com.example.firstdayjava.ui.viewpagers.prudoctlist.ProductListViewPagerAdapter;
import com.example.firstdayjava.ui.views.BottomSheets.ProductFilterBottomSheet;
import com.example.firstdayjava.ui.views.BottomSheets.ProductSortBottomSheet;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.List;

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

    Boolean itJustStart = true;
    String categoryCodeFromMainPage = "";

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        bd = FragmentPrudoctPageBinding.inflate(inflater, container, false);
        categoryCodeFromMainPage = ProductsPageFragmentArgs.fromBundle(getArguments()).getCategoryCode();
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
        viewModel.categoryMDL.observe(getViewLifecycleOwner(), new Observer<List<Category>>() {
            @Override
            public void onChanged(List<Category> categories) {
                ProductListListener productListListener = data -> NavHostFragment
                        .findNavController(requireParentFragment())
                        .navigate(
                                ProductsPageFragmentDirections
                                        .actionProductPageFragmentToProductInfoFragment(data)
                        );

                pagerAdapter.setFragmentsByCategories(categories, productListListener);

                if (itJustStart) {
                    setStartupTab(categories);
                }
            }

            private void setStartupTab(List<Category> categories) {
                int currentIndex = 0;
                for (int i = 0; i < categories.size(); i++) {
                    if (categories.get(i).getCategoryCode().equals(categoryCodeFromMainPage)) {
                        currentIndex = i;
                        break;
                    }
                }
                bd.viewPager.setCurrentItem(currentIndex);
            }
        });

        viewModel.responseStateMDL.observe(getViewLifecycleOwner(), responseState -> {
            if (!responseState.isSuccessful()) {
                // TODO: 3/28/22 show offline image
                Toast.makeText(requireContext(), responseState.getMessage(), Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(requireContext(), responseState.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        viewModel.filterLiveData.observe(getViewLifecycleOwner(), filter -> {
            filterBottomSheet = new ProductFilterBottomSheet(filter,
                    fromSheetFilter -> viewModel.updateProductFilter(fromSheetFilter));


            sortBottomSheet = new ProductSortBottomSheet(filter,
                    fromSheetFilter -> viewModel.updateProductFilter(fromSheetFilter));
        });

        viewModel.amountLiveData.observe(getViewLifecycleOwner(),
                count -> bd.productCount.setText(String.valueOf(count)));
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

        bd.openFavBtn.setOnClickListener(view ->
                NavHostFragment.findNavController(requireParentFragment())
                        .navigate(R.id.action_productPageFragment_to_favoriteFragment));

        bd.backBtn.setOnClickListener(view -> NavHostFragment.findNavController(requireParentFragment()).popBackStack());
    }

    private void initPagerView() {
        pagerAdapter = new ProductListViewPagerAdapter(this);
        bd.viewPager.setAdapter(pagerAdapter);
        bd.viewPager.setUserInputEnabled(false);

        TabLayoutMediator layoutMediator = new TabLayoutMediator(bd.categoryTabL, bd.viewPager, (tab, position) -> {
            View view = LayoutInflater.from(bd.getRoot().getContext()).inflate(R.layout.view_categoryfortab, null, false);
            try {
                String name = pagerAdapter.getCategories().get(position).getCategoryName();
                ((TextView) view.findViewById(R.id.categoryNameTV)).setText(name);
                tab.setCustomView(view);
            } catch (Exception exception) {

            }
        });

        layoutMediator.attach();
    }
}