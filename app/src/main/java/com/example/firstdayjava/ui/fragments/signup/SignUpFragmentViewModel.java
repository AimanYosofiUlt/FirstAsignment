package com.example.firstdayjava.ui.fragments.signup;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.firstdayjava.R;
import com.example.firstdayjava.pojo.remote.callpack.BaseResponse;
import com.example.firstdayjava.pojo.remote.callpack.ResponsesCallBack;
import com.example.firstdayjava.pojo.remote.callpack.Result;
import com.example.firstdayjava.pojo.remote.models.signup.SignUpPostBody;
import com.example.firstdayjava.pojo.repos.UserRepo;
import com.example.firstdayjava.pojo.remote.callpack.ResponseState;

import javax.inject.Inject;


public class SignUpFragmentViewModel extends AndroidViewModel {

    @Inject
    UserRepo userRepo;
    public MutableLiveData<ResponseState> signUpState;

    @Inject
    public SignUpFragmentViewModel(@NonNull Application application) {
        super(application);
        signUpState = new MutableLiveData<>();
    }

    public void signUp(SignUpPostBody postBody) {
        userRepo.signUp(postBody, new ResponsesCallBack<BaseResponse>() {
            @Override
            public void onSuccess(BaseResponse response) {
                ResponseState state = new ResponseState();
                signUpState.postValue(state);
            }

            @Override
            public void onFailure(Result result) {
                ResponseState state = new ResponseState(result.getErrMsg());
                int errorNo = result.getErrNo();
                switch (errorNo) {
                    case 602:
                        state.setMessage(getApplication()
                                .getString(R.string.api_error_602));
                        break;
                    case 603:
                        state.setMessage(getApplication()
                                .getString(R.string.api_error_603));
                        break;
                    case 604:
                        state.setMessage(getApplication()
                                .getString(R.string.api_error_604));
                        break;
                    case 605:
                        state.setMessage(getApplication()
                                .getString(R.string.api_error_605));
                        break;
                    case 606:
                        state.setMessage(getApplication()
                                .getString(R.string.api_error_606));
                        break;
                    case 607:
                        state.setMessage(getApplication()
                                .getString(R.string.api_error_607));
                        break;
                    default:
                        state.setMessage(getApplication()
                                .getString(R.string.api_error_genral));
                        break;
                }
            }
        });
    }
}
