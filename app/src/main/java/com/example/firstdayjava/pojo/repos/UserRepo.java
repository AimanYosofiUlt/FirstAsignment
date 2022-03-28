package com.example.firstdayjava.pojo.repos;

import androidx.lifecycle.LiveData;

import com.example.firstdayjava.pojo.local.database.AppDao;
import com.example.firstdayjava.pojo.local.entities.User;
import com.example.firstdayjava.pojo.remote.models.login.LoginResponse;
import com.example.firstdayjava.pojo.local.models.responses.SignUpResponse;
import com.example.firstdayjava.pojo.remote.callpack.ResponsesCallBack;
import com.example.firstdayjava.pojo.remote.callpack.Result;
import com.example.firstdayjava.pojo.remote.models.login.LoginData;
import com.example.firstdayjava.pojo.remote.models.login.LoginPostBody;
import com.example.firstdayjava.pojo.remote.api.UltimateApi;

import java.util.List;

import javax.inject.Inject;

public class UserRepo {
    AppDao appDao;
    public LiveData<List<User>> users;

    @Inject
    UltimateApi api;

    @Inject
    public UserRepo(AppDao appDao) {
        this.appDao = appDao;
        users = appDao.getUsers();
    }

    public void addUser(User user) {
        appDao.insertUser(user);
    }

    public void deleteUser(User user) {
        appDao.deleteUser(user);
    }

    public void updateUser(User user) {
        appDao.updateUser(user);
    }

    public void login(LoginPostBody postBody, ResponsesCallBack<LoginResponse> callBack) {
        api.login(postBody).enqueue(new ResponsesCallBack<LoginResponse>() {
            @Override
            public void onSuccess(LoginResponse response) {
                addUserFromLogin(response.getData(), postBody.getPassword());
                callBack.onSuccess(response);
            }

            @Override
            public void onFailure(Result result) {
                callBack.onFailure(result);
            }
        });

    }

    void addUserFromLogin(LoginData data, String password) {
        User user = new User();
        user.setFirstName(data.getFirstName());
        user.setLastName(data.getLastName());
        user.setPhone(data.getPhone());
        user.setEmail(data.getEmail());
        user.setPassword(password);

        appDao.insertUserFromLogin(user);
    }

    public void signUp(User user, ResponsesCallBack<SignUpResponse> callBack) {
        api.signup(user).enqueue(new ResponsesCallBack<SignUpResponse>() {
            @Override
            public void onSuccess(SignUpResponse response) {
                callBack.onSuccess(response);
                addUser(user);
            }

            @Override
            public void onFailure(Result result) {
                callBack.onFailure(result);
            }
        });
    }
}
