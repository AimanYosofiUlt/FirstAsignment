package com.example.firstdayjava.ui.fragments.setting.changepassword;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.firstdayjava.pojo.remote.api.UltimateApi;
import com.example.firstdayjava.pojo.remote.callpack.BaseResponse;
import com.example.firstdayjava.pojo.remote.callpack.ResponseState;
import com.example.firstdayjava.pojo.remote.callpack.ResponsesCallBack;
import com.example.firstdayjava.pojo.remote.callpack.Result;
import com.example.firstdayjava.pojo.remote.models.cahnge_password.ChangePasswordPostBody;
import com.example.firstdayjava.pojo.repos.AppSettingRepo;
import com.example.firstdayjava.pojo.repos.UserRepo;

import javax.inject.Inject;

public class ChangePasswordFragmentViewModel extends AndroidViewModel {
    public static final String INCORRECT_PASSWORD_FLAG = "0";

    @Inject
    UltimateApi api;

    @Inject
    AppSettingRepo settingRepo;

    @Inject
    UserRepo userRepo;

    MutableLiveData<ResponseState> responseStateMDL;

    @Inject
    public ChangePasswordFragmentViewModel(@NonNull Application application) {
        super(application);
        responseStateMDL = new MutableLiveData<>();
    }

    public void changePassword(String oldPassword, String newPassword) {
        if (settingRepo.isPasswordCorrect(oldPassword)) {
            String userCode = settingRepo.getUserCode();
            ChangePasswordPostBody postBody = new ChangePasswordPostBody(userCode, oldPassword, newPassword);
            userRepo.changePassword(postBody, new ResponsesCallBack<BaseResponse>() {
                @Override
                public void onSuccess(BaseResponse response) {
                    responseStateMDL.postValue(new ResponseState());
                    settingRepo.changePassword(newPassword);
                }

                @Override
                public void onFailure(Result result) {
                    responseStateMDL.postValue(new ResponseState(result.getErrMsg()));
                }
            });
        } else {
            responseStateMDL.postValue(new ResponseState(INCORRECT_PASSWORD_FLAG));
        }
    }
}
