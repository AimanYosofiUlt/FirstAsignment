package com.example.firstdayjava.ui.fragments.setting.profile;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.firstdayjava.pojo.local.entities.User;
import com.example.firstdayjava.pojo.remote.callpack.BaseResponse;
import com.example.firstdayjava.pojo.remote.callpack.ResponseState;
import com.example.firstdayjava.pojo.remote.callpack.ResponsesCallBack;
import com.example.firstdayjava.pojo.remote.callpack.Result;
import com.example.firstdayjava.pojo.remote.models.edit_profile.EditProfilePostBody;
import com.example.firstdayjava.pojo.repos.AppSettingRepo;
import com.example.firstdayjava.pojo.repos.UserRepo;

import javax.inject.Inject;

public class ProfileFragmentViewModel extends AndroidViewModel {
    @Inject
    AppSettingRepo settingRepo;
    MutableLiveData<User> appUserMDL;

    @Inject
    UserRepo userRepo;
    public MutableLiveData<ResponseState> responseStateMDL;

    @Inject
    public ProfileFragmentViewModel(@NonNull Application application) {
        super(application);
        appUserMDL = new MutableLiveData<>();
        responseStateMDL = new MutableLiveData<>();
    }

    public void getAppUser() {
        appUserMDL.postValue(settingRepo.getAppUser());
    }


    private void setUserProfile(String firstName, String lastName, String email) {
        settingRepo.setUserProfile(firstName, lastName, email);
        getAppUser();
    }

    public void updateUserProfile(User user) {
        String userCode = settingRepo.getUserCode();
        EditProfilePostBody postBody = new EditProfilePostBody(userCode,
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPhone());

        userRepo.updateProfile(postBody, new ResponsesCallBack<BaseResponse>() {
            @Override
            public void onSuccess(BaseResponse response) {
                setUserProfile(user.getFirstName(), user.getLastName(), user.getEmail());
                responseStateMDL.postValue(new ResponseState());
            }

            @Override
            public void onFailure(Result result) {
                responseStateMDL.postValue(new ResponseState(result.getErrMsg()));
            }
        });
    }
}
