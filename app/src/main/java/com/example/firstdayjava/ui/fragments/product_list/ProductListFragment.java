package com.example.firstdayjava.ui.fragments.product_list;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firstdayjava.databinding.FragmentPrudoctListBinding;
import com.example.firstdayjava.pojo.local.entities.Category;
import com.example.firstdayjava.pojo.local.entities.setting.ProductPageFilter;
import com.example.firstdayjava.ui.fragments.base.BaseFragment;
import com.example.firstdayjava.ui.views.ProductView.ProductViewAdapter;
import com.example.firstdayjava.ui.views.TypeCardView.TypeCardViewAdapter;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class ProductListFragment extends BaseFragment {
    FragmentPrudoctListBinding bd;

    TypeCardViewAdapter typeAdapter;
    ProductViewAdapter productAdapter;

    Category category;

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


    private void addTypes() {
        typeAdapter.addTypeCard(category.getCategoryName());
    }

    @Override
    protected void initViewModel() {
        viewModel.productMDL.observe(getViewLifecycleOwner(), products -> {
            productAdapter.setList(products);
            Log.d("ProductListFragment", "initViewModel: 6548 :" + products.size());
        });

        viewModel.filterLiveData.observe(getViewLifecycleOwner(), new Observer<ProductPageFilter>() {
            @Override
            public void onChanged(ProductPageFilter filter) {
//                changeSortingType(filter.getSortingType());
                changeSortingOrder(filter.getOrderBy());
            }

            private void changeSortingOrder(String orderBy) {
                Toast.makeText(requireContext(), "changeSortingOrder:" + orderBy, Toast.LENGTH_SHORT).show();
                viewModel.getProducts(category.getCategoryCode(), "MC002", orderBy);
            }

            private void changeSortingType(String sortingType) {
                RecyclerView.LayoutManager layout;
                if (sortingType.equals(ProductPageFilter.GRID_SHOW)) {
                    layout = new GridLayoutManager(requireContext(), 2);
                } else {
                    layout = new LinearLayoutManager(requireContext());
                }

                bd.prudoctRV.setLayoutManager(layout);
                productAdapter.setShowType(sortingType);
            }
        });
    }

    @Override
    protected void initModelView() {
        typeAdapter = new TypeCardViewAdapter();
        bd.typeRV.setAdapter(typeAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(requireContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        bd.typeRV.setLayoutManager(linearLayoutManager);

        productAdapter = new ProductViewAdapter();
        bd.prudoctRV.setAdapter(productAdapter);
        bd.prudoctRV.setLayoutManager(new GridLayoutManager(requireContext(), 2));
        addTypes();
    }

    @Override
    protected void initEvent() {

    }
}