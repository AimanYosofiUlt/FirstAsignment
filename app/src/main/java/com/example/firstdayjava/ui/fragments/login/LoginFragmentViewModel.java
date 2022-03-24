package com.example.firstdayjava.ui.fragments.login;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.firstdayjava.R;
import com.example.firstdayjava.pojo.dbs.models.responses.callpack.ResponsesCallBack;
import com.example.firstdayjava.pojo.dbs.models.responses.callpack.Result;
import com.example.firstdayjava.pojo.dbs.models.responses.datas.LoginPostBody;
import com.example.firstdayjava.pojo.dbs.models.responses.LoginResponse;
import com.example.firstdayjava.pojo.repos.UserRepo;
import com.example.firstdayjava.ui.fragments.ResponseState;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import dagger.hilt.android.scopes.ViewModelScoped;

public class LoginFragmentViewModel extends AndroidViewModel {

    @Inject
    UserRepo userRepo;
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
                ResponseState state = new ResponseState(true, getApplication().getString(R.string.done));
                loginState.postValue(state);
            }

            @Override
            public void onFailure(Result result) {
                ResponseState state;
                int errorNo = result.getErrNo();
                if (errorNo == 600) {
                    state = new ResponseState(false, getApplication().getString(R.string.invalid_phone));
                } else if (errorNo == 601) {
                    state = new ResponseState(false, getApplication().getString(R.string.invalid_password));
                } else if (errorNo == 10) {
                    state = new ResponseState(true, getApplication().getString(R.string.api_error_genral) + " : " + result.getErrMsg());
                } else {
                    state = new ResponseState(true, getApplication().getString(R.string.server_error_genral) + " : " + result.getErrMsg());
                }
                loginState.postValue(state);
            }
        });
    }
}

