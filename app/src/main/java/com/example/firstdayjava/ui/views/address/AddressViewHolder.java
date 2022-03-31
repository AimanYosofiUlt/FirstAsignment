package com.example.firstdayjava.ui.views.address;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firstdayjava.databinding.ViewAddressBinding;
import com.example.firstdayjava.pojo.remote.models.get_address.GetAddressData;

public class AddressViewHolder extends RecyclerView.ViewHolder {
    GetAddressData data;
    ViewAddressBinding bd;

    public AddressViewHolder(@NonNull View itemView) {
        super(itemView);
        bd = ViewAddressBinding.bind(itemView);
    }

    void bind(GetAddressData data) {
        this.data = data;
        String building = (data.getBuilding() != null && !data.getBuilding().isEmpty()) ? "Building:" + data.getBuilding() + " " : "";
        String floor = (data.getFloor() != null && !data.getFloor().isEmpty()) ? "Floor:" + data.getFloor() + " " : "";
        String apartment = (data.getApartment() != null && !data.getApartment().isEmpty()) ? "Apartment:" + data.getApartment() + " " : "";
        String landmark = (data.getLandmark() != null && !data.getLandmark().isEmpty()) ? "Near " + data.getLandmark() : "";
        bd.streetTV.setText(data.getStreetName());
        String desc = building + floor + apartment + landmark;
        if (desc.isEmpty()) {
            bd.locationDESC.setVisibility(View.GONE);
            bd.locationImg.setVisibility(View.GONE);
        } else {
            bd.locationDESC.setVisibility(View.VISIBLE);
            bd.locationImg.setVisibility(View.VISIBLE);
            bd.locationDESC.setText(desc);
        }
        bd.mobileTV.setText(data.getMobile());
    }
}
