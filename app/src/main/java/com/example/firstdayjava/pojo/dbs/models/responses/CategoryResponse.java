package com.example.firstdayjava.pojo.dbs.models.responses;

import com.example.firstdayjava.pojo.dbs.models.responses.datas.CategoryData;
import com.example.firstdayjava.pojo.dbs.models.responses.callpack.ResponseObject;
import com.example.firstdayjava.pojo.dbs.models.responses.callpack.Result;
import com.google.gson.annotations.SerializedName;

public class CategoryResponse extends ResponseObject {
    @SerializedName("Data")
    CategoryData data;

    public CategoryResponse(Result result) {
        super(result);
    }
}
