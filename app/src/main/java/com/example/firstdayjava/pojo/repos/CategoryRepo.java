package com.example.firstdayjava.pojo.repos;

import com.example.firstdayjava.pojo.local.database.AppDao;
import com.example.firstdayjava.pojo.local.entities.Category;
import com.example.firstdayjava.pojo.remote.api.UltimateApi;
import com.example.firstdayjava.pojo.remote.callpack.ResponsesCallBack;
import com.example.firstdayjava.pojo.remote.callpack.Result;
import com.example.firstdayjava.pojo.remote.models.category.CategoryData;
import com.example.firstdayjava.pojo.remote.models.category.MainPageData;
import com.example.firstdayjava.pojo.remote.models.category.MainPagePostBody;
import com.example.firstdayjava.pojo.remote.models.category.MainPageResponse;

import java.util.List;

import javax.inject.Inject;

public class CategoryRepo {

    @Inject
    AppDao appDao;

    @Inject
    UltimateApi api;

    @Inject
    public CategoryRepo() {
    }

    public void getCategories(MainPagePostBody postBody, ResponsesCallBack<MainPageResponse> call) {
        api.getCategories(postBody).enqueue(call);
    }

    public List<Category> getOfflineCategories(){
        return appDao.getCategories();
    }

    public void addCategory(Category category){
        appDao.insertCategory(category);
    }
}
