package com.example.firstdayjava.ui.fragments.order;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.firstdayjava.R;
import com.example.firstdayjava.databinding.FragmentOrderBinding;
import com.example.firstdayjava.pojo.remote.callpack.ResponseState;
import com.example.firstdayjava.pojo.remote.models.getOrder.OrderMaster;
import com.example.firstdayjava.ui.fragments.base.BaseFragment;
import com.example.firstdayjava.ui.views.order.OrderViewAdapter;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class OrderFragment extends BaseFragment {

    @Inject
    OrderFragmentViewModel viewModel;

    FragmentOrderBinding bd;
    OrderViewAdapter adapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        bd = FragmentOrderBinding.inflate(inflater, container, false);

        initViewModel();
        initModelView();
        initEvent();
        return bd.getRoot();
    }


    @Override
    protected void initViewModel() {
        viewModel.orderMastersMDL.observe(getViewLifecycleOwner(), new Observer<List<OrderMaster>>() {
            @Override
            public void onChanged(List<OrderMaster> orderMasters) {
                adapter.setList(orderMasters);
            }
        });

        viewModel.responseStateMDL.observe(getViewLifecycleOwner(), new Observer<ResponseState>() {
            @Override
            public void onChanged(ResponseState responseState) {
                if (!responseState.isSuccessful()) {
                    showErrorDialog(responseState.getMessage());
                }
            }

            private void showErrorDialog(String message) {
                new AlertDialog.Builder(requireContext())
                        .setMessage(message)
                        .setNegativeButton(getString(R.string.done), (dialogInterface, i) -> dialogInterface.cancel())
                        .create()
                        .show();
            }
        });

        viewModel.clearDataMDL.observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                adapter.clearData();
            }
        });
    }

    @Override
    protected void initModelView() {
        adapter = new OrderViewAdapter();
        bd.ordersRV.setLayoutManager(new LinearLayoutManager(requireContext()));
        bd.ordersRV.setAdapter(adapter);

        viewModel.getOrders();
    }

    @Override
    protected void initEvent() {

    }
}
