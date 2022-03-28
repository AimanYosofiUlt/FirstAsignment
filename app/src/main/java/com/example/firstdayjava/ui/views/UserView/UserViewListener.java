package com.example.firstdayjava.ui.views.UserView;

import com.example.firstdayjava.pojo.local.entities.User;

public interface UserViewListener {
    void onDeleteReq(User user);
    void onUpdateReq(User user);
}
