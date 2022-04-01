package com.example.firstdayjava.ui.fragments.edit_address;

import android.app.AlertDialog;
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
import com.example.firstdayjava.databinding.FragmentAddAdressBinding;
import com.example.firstdayjava.pojo.remote.callpack.ResponseState;
import com.example.firstdayjava.pojo.remote.models.edit_adress.AddAddressPostBody;
import com.example.firstdayjava.pojo.remote.models.edit_adress.UpdateAddressPostBody;
import com.example.firstdayjava.pojo.remote.models.get_address.GetAddressData;
import com.example.firstdayjava.ui.fragments.base.BaseFragment;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class EditAddressFragment extends BaseFragment {
    FragmentAddAdressBinding bd;

    String longitude = "31.139660";
    String latitude = "29.997840";
    String addressDetails = "addressDetails";
    String userCode;
    AddAddressPostBody postBody;
    Boolean isInEditMode = false;
    GetAddressData editData;
    @Inject
    EditAddressFragmentViewHolder viewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        bd = FragmentAddAdressBinding.inflate(inflater, container, false);
        isInEditMode = EditAddressFragmentArgs.fromBundle(getArguments()).getIsInEditMode();
        if (isInEditMode) {
            editData = EditAddressFragmentArgs.fromBundle(getArguments()).getAddressData();
        }

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
                    if (isInEditMode)
                        Toast.makeText(requireContext(), getString(R.string.saved), Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(requireContext(), getString(R.string.edited), Toast.LENGTH_SHORT).show();
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

        if (isInEditMode) {
            bd.streetED.setText(editData.getStreetName());
            bd.buildingED.setText(editData.getBuilding());
            bd.floorED.setText(editData.getFloor());
            bd.apartmentED.setText(editData.getApartment());
            bd.landmarkED.setText(editData.getNearestlandmark());
            bd.addressDetile.setText(editData.getAddressDetails());
            bd.mobileED.setText(editData.getMobile());
            bd.landlineED.setText(editData.getLandlineNumber());

            latitude = editData.getLatitude();
            longitude = editData.getLongitude();
            bd.latlongTV.setText(getLatLongFormat());
            bd.latlongTV.setVisibility(View.VISIBLE);

            userCode = editData.getUserCode();
            addressDetails = editData.getAddressDetails();
            bd.saveBtn.setText(getString(R.string.update_address));
        }
    }

    private String getLatLongFormat() {
        return "(" + longitude + ", " + latitude + ")";
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
                        bd.mobileED.getText().toString(),
                        bd.landlineED.getText().toString(),
                        bd.landmarkED.getText().toString()
                );

                if (isInEditMode) {
                    UpdateAddressPostBody updatePostBody = new UpdateAddressPostBody(editData.getAddressID(), postBody);
                    viewModel.updateAddress(updatePostBody);
                } else {
                    viewModel.addAddress(postBody);
                }
            }
        });
    }

    private boolean isSaveAble() {
        if (hasEmptyFieldHandel(bd.streetED)) return false;

        if (hasEmptyFieldHandel(bd.addressDetile)) return false;

        return !hasEmptyFieldHandel(bd.mobileED);
    }

    private boolean hasEmptyFieldHandel(TextView ed) {
        boolean isEmpty = ed.getText().toString().equals("");
        if (isEmpty)
            ed.setError("Empty Field");
        return isEmpty;
    }
}
