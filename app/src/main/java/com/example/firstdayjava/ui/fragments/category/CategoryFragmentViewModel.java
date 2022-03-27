package com.example.firstdayjava.ui.fragments.category;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.firstdayjava.pojo.dbs.models.Category;
import com.example.firstdayjava.pojo.dbs.models.responses.CategoryResponse;
import com.example.firstdayjava.pojo.dbs.models.responses.callpack.ResponsesCallBack;
import com.example.firstdayjava.pojo.dbs.models.responses.callpack.Result;
import com.example.firstdayjava.pojo.dbs.models.responses.datas.PostBody;
import com.example.firstdayjava.pojo.repos.CategoryRepo;

import java.util.List;

import javax.inject.Inject;

public class CategoryFragmentViewModel extends AndroidViewModel {
    @Inject
    CategoryRepo categoryRepo;

    MutableLiveData<List<Category>> categoriesMutableLiveData;

    @Inject
    public CategoryFragmentViewModel(@NonNull Application application) {
        super(application);
        categoriesMutableLiveData = new MutableLiveData<>();
    }

    public void getCategory() {
        categoryRepo.getCategories(new PostBody(), new ResponsesCallBack<CategoryResponse>() {
            @Override
            public void onSuccess(CategoryResponse response) {
                List<Category> categories = response.getData().getCategory();
                categoriesMutableLiveData.postValue(categories);
            }

            @Override
            public void onFailure(Result result) {

            }
        });
    }
}
