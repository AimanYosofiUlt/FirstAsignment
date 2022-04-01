package com.example.firstdayjava.ui.views.address;

import com.example.firstdayjava.pojo.remote.models.get_address.GetAddressData;

public interface AddressViewListener {
    void onEditReqListener(GetAddressData data);
    void onDeleteReqListener(GetAddressData data);
}
