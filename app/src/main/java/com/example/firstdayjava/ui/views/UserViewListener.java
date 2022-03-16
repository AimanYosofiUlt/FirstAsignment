package com.example.firstdayjava.ui.views;

import com.example.firstdayjava.pojo.dbs.models.Users;

public interface UserViewListener {
    void onDeleteReq(Users user);
    void onUpdateReq(Users user);
}
