package com.example.firstdayjava.ui.fragments.login;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.firstdayjava.R;
import com.example.firstdayjava.pojo.remote.callpack.ResponseState;
import com.example.firstdayjava.pojo.remote.callpack.ResponsesCallBack;
import com.example.firstdayjava.pojo.remote.callpack.Result;
import com.example.firstdayjava.pojo.remote.models.login.LoginPostBody;
import com.example.firstdayjava.pojo.remote.models.login.LoginResponse;
import com.example.firstdayjava.pojo.repos.AppSettingRepo;
import com.example.firstdayjava.pojo.repos.UserRepo;

import javax.inject.Inject;

public class LoginFragmentViewModel extends AndroidViewModel {

    @Inject
    UserRepo userRepo;

    @Inject
    AppSettingRepo settingRepo;

    public MutableLiveData<ResponseState> loginState;

    @Inject
    public LoginFragmentViewModel(@NonNull Application application) {
        super(application);
        loginState = new MutableLiveData<>();
    }

    public void login(String phoneNo, String password) {
        LoginPostBody postBody = new LoginPostBody(phoneNo, password);

        userRepo.login(postBody, new ResponsesCallBack<LoginResponse>() {
            @Override
            public void onSuccess(LoginResponse response) {
                ResponseState state = new ResponseState();
                loginState.postValue(state);

                String userCode = response.getData().getUserCode();
                settingRepo.updateCurrentUser(userCode);
            }

            @Override
            public void onFailure(Result result) {
                ResponseState state;
                int errorNo = result.getErrNo();
                if (errorNo == 600) {
                    state = new ResponseState(getApplication().getString(R.string.invalid_phone));
                } else if (errorNo == 601) {
                    state = new ResponseState(getApplication().getString(R.string.invalid_password));
                } else if (errorNo == 10) {
                    state = new ResponseState(getApplication().getString(R.string.api_error_genral) + " : " + result.getErrMsg());
                } else {
                    state = new ResponseState(getApplication().getString(R.string.server_error_genral) + " : " + result.getErrMsg());
                }
                loginState.postValue(state);
            }
        });
    }

    public void updateLanguage(String language) {
        settingRepo.updateLanguage(language);
    }
}

