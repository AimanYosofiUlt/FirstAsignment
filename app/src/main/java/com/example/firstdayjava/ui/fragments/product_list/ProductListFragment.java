package com.example.firstdayjava.ui.fragments.product_list;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firstdayjava.databinding.FragmentPrudoctListBinding;
import com.example.firstdayjava.pojo.local.entities.Category;
import com.example.firstdayjava.pojo.local.entities.Product;
import com.example.firstdayjava.pojo.local.entities.setting.ProductPageFilter;
import com.example.firstdayjava.ui.fragments.base.BaseFragment;
import com.example.firstdayjava.ui.views.ProductView.ProductViewAdapter;
import com.example.firstdayjava.ui.views.ProductView.ProductViewData;
import com.example.firstdayjava.ui.views.ProductView.ProductViewListener;
import com.example.firstdayjava.ui.views.SubCategoryCardView.SubCategoryAdapter;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class ProductListFragment extends BaseFragment implements ProductViewListener {
    FragmentPrudoctListBinding bd;

    SubCategoryAdapter typeAdapter;
    ProductViewAdapter productAdapter;

    Category category;
    String selectedSubCatCode = "";
    String orderBy = ProductPageFilter.SORT_BY_NAME;

    @Inject
    ProductListFragmentViewModel viewModel;

    public ProductListFragment(Category category) {
        this.category = category;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        bd = FragmentPrudoctListBinding.inflate(inflater, container, false);

        initModelView();
        initViewModel();
        initEvent();
        return bd.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();
        viewModel.getSubCategory(category.getCategoryCode());
    }

    @Override
    protected void initViewModel() {
        viewModel.productDataMDL.observe(getViewLifecycleOwner(),
                products -> productAdapter.setList(products));

        viewModel.subCategoryMDL.observe(getViewLifecycleOwner(),
                subCategories -> typeAdapter.setList(subCategories));

        viewModel.filterLiveData.observe(getViewLifecycleOwner(), new Observer<ProductPageFilter>() {
            @Override
            public void onChanged(ProductPageFilter filter) {
                changeSortingType(filter.getSortingType());
                changeSortingOrder(filter.getOrderBy());
            }

            private void changeSortingOrder(String orderBy) {
                ProductListFragment.this.orderBy = orderBy;
                getProducts();
            }

            private void changeSortingType(String sortingType) {
                RecyclerView.LayoutManager layout;
                if (sortingType.equals(ProductPageFilter.GRID_SHOW)) {
                    layout = new GridLayoutManager(requireContext(), 2);
                } else {
                    layout = new LinearLayoutManager(requireContext());
                }
                bd.prudoctRV.setAdapter(null);
                productAdapter = new ProductViewAdapter(sortingType, ProductListFragment.this);
                bd.prudoctRV.setAdapter(productAdapter);
                bd.prudoctRV.setLayoutManager(layout);
            }
        });


    }

    @Override
    protected void initModelView() {
        typeAdapter = new SubCategoryAdapter(subCategory -> {
            selectedSubCatCode = subCategory;
            getProducts();
        });

        bd.typeRV.setAdapter(typeAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(requireContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        bd.typeRV.setLayoutManager(linearLayoutManager);

        productAdapter = new ProductViewAdapter(ProductPageFilter.GRID_SHOW, this);
        bd.prudoctRV.setAdapter(productAdapter);
        bd.prudoctRV.setLayoutManager(new GridLayoutManager(requireContext(), 2));
    }

    private void getProducts() {
        viewModel.getProducts(category.getCategoryCode(),
                selectedSubCatCode,
                orderBy);
    }

    @Override
    protected void initEvent() {

    }

    @Override
    public void onAmountChange(ProductViewData data) {
        viewModel.addToCart(data.amount,data.getProduct());
    }

    @Override
    public void onAmountEmpty(Product product) {
        viewModel.deleteFromCart(product);
    }
}