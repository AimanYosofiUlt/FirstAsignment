package com.example.firstdayjava.ui.fragments.cart;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.firstdayjava.R;
import com.example.firstdayjava.databinding.FragmentCartBinding;
import com.example.firstdayjava.pojo.local.entities.Product;
import com.example.firstdayjava.ui.views.ProductView.ProductViewData;
import com.example.firstdayjava.ui.views.ProductView.ProductViewListener;
import com.example.firstdayjava.ui.views.cart_info.CartInfoShowAdapter;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class CartFragment extends Fragment {
    FragmentCartBinding bd;
    @Inject
    CartFragmentViewModel viewModel;

    CartInfoShowAdapter adapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        bd = FragmentCartBinding.inflate(inflater, container, false);

        initViewModel();
        initModelView();
        initEvent();
        return bd.getRoot();
    }

    private void initViewModel() {
        viewModel.cartDataLiveData.observe(getViewLifecycleOwner(), dataList -> adapter.setList(dataList));

        viewModel.totalLiveData.observe(getViewLifecycleOwner(), total -> bd.totalTV.setText(String.valueOf(total)));
    }

    private void initModelView() {
        adapter = new CartInfoShowAdapter(new ProductViewListener() {
            @Override
            public void onAmountChange(ProductViewData data) {
                viewModel.addToCart(data.amount, data.getProduct());
            }

            @Override
            public void onAmountEmpty(Product product) {
                showAlertDialog((dialogInterface, i) -> {
                    viewModel.deleteFromCart(product);
                    dialogInterface.cancel();
                });
            }

            private void showAlertDialog(DialogInterface.OnClickListener onClickListener) {
                new AlertDialog.Builder(requireContext())
                        .setTitle(getString(R.string.deleteTitle))
                        .setMessage(getString(R.string.deleteMsg))
                        .setPositiveButton(getString(R.string.yes), onClickListener)
                        .setNegativeButton(getString(R.string.cancle), (dialogInterface, i) -> dialogInterface.cancel())
                        .create()
                        .show();
            }
        });

        LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext());
        bd.productRV.setLayoutManager(layoutManager);
        bd.productRV.setAdapter(adapter);

    }

    private void initEvent() {

    }
}
