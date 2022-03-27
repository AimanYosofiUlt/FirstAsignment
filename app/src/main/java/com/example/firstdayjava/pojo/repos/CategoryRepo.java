package com.example.firstdayjava.pojo.repos;

import com.example.firstdayjava.pojo.dbs.models.responses.CategoryResponse;
import com.example.firstdayjava.pojo.dbs.models.responses.callpack.ResponsesCallBack;
import com.example.firstdayjava.pojo.dbs.models.responses.callpack.Result;
import com.example.firstdayjava.pojo.dbs.models.responses.datas.PostBody;
import com.example.firstdayjava.pojo.remote.api.UltimateApi;

import javax.inject.Inject;

public class CategoryRepo {
    @Inject
    UltimateApi api;

    @Inject
    public CategoryRepo() {
    }

    public void getCategories(PostBody postBody, ResponsesCallBack<CategoryResponse> call) {
        api.getCategories(postBody).enqueue(new ResponsesCallBack<CategoryResponse>() {
            @Override
            public void onSuccess(CategoryResponse response) {
                call.onSuccess(response);
            }

            @Override
            public void onFailure(Result result) {
                call.onFailure(result);
            }
        });
    }
}
