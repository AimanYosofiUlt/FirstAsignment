package com.example.firstdayjava.ui.fragments.add_address;

import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.navigation.fragment.NavHostFragment;

import com.example.firstdayjava.R;
import com.example.firstdayjava.databinding.FragmentAddAdressBinding;
import com.example.firstdayjava.pojo.remote.callpack.ResponseState;
import com.example.firstdayjava.pojo.remote.models.add_adress.AddAddressPostBody;
import com.example.firstdayjava.ui.fragments.address.AddressFragmentViewHolder;
import com.example.firstdayjava.ui.fragments.base.BaseFragment;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class AddAddressFragment extends BaseFragment {
    FragmentAddAdressBinding bd;

    String longitude = "31.139660";
    String latitude = "29.997840";
    String addressDetails = "Street";
    String userCode;
    AddAddressPostBody postBody;

    @Inject
    AddAddressFragmentViewHolder viewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        bd = FragmentAddAdressBinding.inflate(inflater, container, false);

        initViewModel();
        initModelView();
        initEvent();
        return bd.getRoot();
    }

    @Override
    protected void initViewModel() {
        viewModel.userCodeMDL.observe(getViewLifecycleOwner(), currentUserCode -> userCode = currentUserCode);

        viewModel.responseStateMDL.observe(getViewLifecycleOwner(), new Observer<ResponseState>() {
            @Override
            public void onChanged(ResponseState responseState) {
                if (responseState.isSuccessful()) {
                    Toast.makeText(requireContext(), "Save", Toast.LENGTH_SHORT).show();
                    NavHostFragment.findNavController(requireParentFragment()).popBackStack();
                } else {
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
    }

    @Override
    protected void initModelView() {
         viewModel.getCurrentUserCode();
    }

    @Override
    protected void initEvent() {
        bd.backBtn.setOnClickListener(view -> NavHostFragment.findNavController(requireParentFragment()).popBackStack());

        bd.saveBtn.setOnClickListener(view -> {
            if (isSaveAble()) {
                postBody = new AddAddressPostBody(
                        userCode,
                        bd.streetED.getText().toString(),
                        addressDetails,
                        latitude,
                        longitude,
                        bd.buildingED.getText().toString(),
                        bd.floorED.getText().toString(),
                        bd.apartmentED.getText().toString(),
                        bd.mobileED.getText().toString()
                );

                viewModel.addAddress(postBody);
            }
        });
    }

    private boolean isSaveAble() {
        if (hasEmptyFieldHandel(bd.streetED)) return false;

        if (hasEmptyFieldHandel(bd.latlangED)) return false;

        if (hasEmptyFieldHandel(bd.locationTypeED)) return false;

        return !hasEmptyFieldHandel(bd.mobileED);
    }

    private boolean hasEmptyFieldHandel(TextView ed) {
        boolean isEmpty = ed.getText().toString().equals("");
        if (isEmpty)
            ed.setError("Empty Field");
        return isEmpty;
    }
}
