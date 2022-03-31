package com.example.firstdayjava.ui.fragments.address;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.firstdayjava.pojo.remote.callpack.ResponseState;
import com.example.firstdayjava.pojo.remote.callpack.ResponsesCallBack;
import com.example.firstdayjava.pojo.remote.callpack.Result;
import com.example.firstdayjava.pojo.remote.models.get_address.GetAddressData;
import com.example.firstdayjava.pojo.remote.models.get_address.GetAddressPostBody;
import com.example.firstdayjava.pojo.remote.models.get_address.GetAddressResponse;
import com.example.firstdayjava.pojo.repos.AddressRepo;
import com.example.firstdayjava.pojo.repos.AppSettingRepo;

import java.util.List;

import javax.inject.Inject;

public class AddressFragmentViewHolder extends AndroidViewModel {
    @Inject
    AddressRepo addressRepo;

    @Inject
    AppSettingRepo settingRepo;

    public MutableLiveData<ResponseState> responseStateMDL;
    public MutableLiveData<List<GetAddressData>> getAddressMDL;

    @Inject
    public AddressFragmentViewHolder(@NonNull Application application) {
        super(application);
        getAddressMDL = new MutableLiveData<>();
        responseStateMDL = new MutableLiveData<>();
    }

    public void getAddress() {
        String userCode = settingRepo.getUserCode();
        GetAddressPostBody postBody = new GetAddressPostBody(userCode);
        addressRepo.getAddress(postBody, new ResponsesCallBack<GetAddressResponse>() {
            @Override
            public void onSuccess(GetAddressResponse response) {
                List<GetAddressData> data = response.getData();
                getAddressMDL.postValue(data);

                responseStateMDL.postValue(new ResponseState());
            }

            @Override
            public void onFailure(Result result) {
                responseStateMDL.postValue(new ResponseState(result.getErrMsg()));
            }
        });
    }
}
