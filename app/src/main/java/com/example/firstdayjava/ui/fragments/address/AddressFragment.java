package com.example.firstdayjava.ui.fragments.address;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.firstdayjava.R;
import com.example.firstdayjava.databinding.FragmentAddressBinding;
import com.example.firstdayjava.pojo.remote.callpack.ResponseState;
import com.example.firstdayjava.pojo.remote.models.get_address.GetAddressData;
import com.example.firstdayjava.ui.fragments.base.BaseFragment;
import com.example.firstdayjava.ui.views.address.AddressViewAdapter;
import com.example.firstdayjava.ui.views.address.AddressViewListener;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class AddressFragment extends BaseFragment {
    FragmentAddressBinding bd;

    @Inject
    AddressFragmentViewHolder viewModel;

    AddressViewAdapter adapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        bd = FragmentAddressBinding.inflate(inflater, container, false);

        initViewModel();
        initModelView();
        initEvent();
        return bd.getRoot();
    }

    @Override
    protected void initViewModel() {
        viewModel.getAddressMDL.observe(getViewLifecycleOwner(), new Observer<List<GetAddressData>>() {
            @Override
            public void onChanged(List<GetAddressData> dataList) {
                adapter.setList(dataList);
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
        viewModel.getAddress();
        adapter = new AddressViewAdapter(new AddressViewListener() {
            @Override
            public void onEditReqListener(GetAddressData data) {
                NavHostFragment.findNavController(requireParentFragment())
                        .navigate(
                                AddressFragmentDirections
                                        .actionAddressFragmentToAddAddressFragment()
                                        .setIsInEditMode(true)
                                        .setAddressData(data)
                        );
            }

            @Override
            public void onDeleteReqListener(GetAddressData data) {
                viewModel.deleteAddress(data.getAddressID());
            }
        });
        bd.addressRV.setAdapter(adapter);
        bd.addressRV.setLayoutManager(new LinearLayoutManager(requireContext()));
    }

    @Override
    protected void initEvent() {
        bd.addAddressBtn.setOnClickListener(view -> NavHostFragment.findNavController(requireParentFragment())
                .navigate(R.id.action_addressFragment_to_addAddressFragment));

        bd.backBtn.setOnClickListener(view -> NavHostFragment.findNavController(requireParentFragment()).popBackStack());
    }
}
