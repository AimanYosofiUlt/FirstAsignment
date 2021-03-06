package com.example.firstdayjava.ui.fragments.edit_address;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.firstdayjava.pojo.remote.callpack.BaseResponse;
import com.example.firstdayjava.pojo.remote.callpack.ResponseState;
import com.example.firstdayjava.pojo.remote.callpack.ResponsesCallBack;
import com.example.firstdayjava.pojo.remote.callpack.Result;
import com.example.firstdayjava.pojo.remote.models.edit_adress.AddAddressPostBody;
import com.example.firstdayjava.pojo.remote.models.edit_adress.UpdateAddressPostBody;
import com.example.firstdayjava.pojo.repos.AddressRepo;
import com.example.firstdayjava.pojo.repos.AppSettingRepo;

import javax.inject.Inject;

public class EditAddressFragmentViewHolder extends AndroidViewModel {

    @Inject
    AddressRepo addressRepo;

    @Inject
    AppSettingRepo settingRepo;


    public MutableLiveData<ResponseState> responseStateMDL;
    public MutableLiveData<String> userCodeMDL;

    @Inject
    public EditAddressFragmentViewHolder(@NonNull Application application) {
        super(application);
        responseStateMDL = new MutableLiveData<>();
        userCodeMDL = new MutableLiveData<>();
    }

    public void addAddress(AddAddressPostBody postBody) {
        addressRepo.addAddress(postBody, new ResponsesCallBack<BaseResponse>() {
            @Override
            public void onSuccess(BaseResponse response) {
                responseStateMDL.postValue(new ResponseState());
            }

            @Override
            public void onFailure(Result result) {
                responseStateMDL.postValue(new ResponseState(result.getErrMsg()));
            }
        });
    }

    public void getCurrentUserCode() {
        userCodeMDL.postValue(settingRepo.getUserCode());
    }

    public void updateAddress(UpdateAddressPostBody updatePostBody) {
        addressRepo.updateAddress(updatePostBody, new ResponsesCallBack<BaseResponse>() {
            @Override
            public void onSuccess(BaseResponse response) {
                responseStateMDL.postValue(new ResponseState());
            }

            @Override
            public void onFailure(Result result) {
                responseStateMDL.postValue(new ResponseState(result.getErrMsg()));
            }
        });
    }
}
