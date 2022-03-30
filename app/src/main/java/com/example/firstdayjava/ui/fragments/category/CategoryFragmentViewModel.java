package com.example.firstdayjava.ui.fragments.category;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.firstdayjava.pojo.local.entities.Category;
import com.example.firstdayjava.pojo.local.entities.SubCategory;
import com.example.firstdayjava.pojo.remote.callpack.ResponsesCallBack;
import com.example.firstdayjava.pojo.remote.callpack.Result;
import com.example.firstdayjava.pojo.remote.models.category.AdsSlider;
import com.example.firstdayjava.pojo.remote.models.category.CategoryData;
import com.example.firstdayjava.pojo.remote.models.category.MainPagePostBody;
import com.example.firstdayjava.pojo.remote.models.category.MainPageResponse;
import com.example.firstdayjava.pojo.remote.models.category.SubCategoryData;
import com.example.firstdayjava.pojo.repos.CategoryRepo;
import com.example.firstdayjava.ui.fragments.ResponseState;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class CategoryFragmentViewModel extends AndroidViewModel {
    @Inject
    CategoryRepo categoryRepo;

    MutableLiveData<List<Category>> categoriesMDL;
    MutableLiveData<List<AdsSlider>> adsMDL;
    MutableLiveData<ResponseState> responseStateMDL;

    @Inject
    public CategoryFragmentViewModel(@NonNull Application application) {
        super(application);
        categoriesMDL = new MutableLiveData<>();
        adsMDL = new MutableLiveData<>();
        responseStateMDL = new MutableLiveData<>();
    }

    public void getCategories() {
        categoryRepo.getCategories(new MainPagePostBody(), new ResponsesCallBack<MainPageResponse>() {
            @Override
            public void onSuccess(MainPageResponse response) {
                List<CategoryData> categoriesData = response.getData().getCategories();
                addCategoriesToDB(categoriesData);

                List<AdsSlider> adsSliders = response.getData().getAdsSliders();
                adsMDL.postValue(adsSliders);

                responseStateMDL.postValue(new ResponseState(true, "DONE"));
            }

            @Override
            public void onFailure(Result result) {
                responseStateMDL.postValue(new ResponseState(false, result.getErrMsg()));

                List<Category> categories = categoryRepo.getOfflineCategories();
                categoriesMDL.postValue(categories);
            }

            private void addCategoriesToDB(List<CategoryData> categoriesData) {
                List<Category> categories = new ArrayList<>();
                for (CategoryData data : categoriesData) {
                    Category category = new Category(
                            data.getName(),
                            data.getImageUrl(),
                            data.getCode()
                    );

                    categories.add(category);
                    categoryRepo.addCategory(category);
                    addSubCategoriesToDB(category, data.getSubCategories());
                }

                categoriesMDL.postValue(categories);
            }

            private void addSubCategoriesToDB(Category category, List<SubCategoryData> subCategoriesData) {
                for (SubCategoryData subData : subCategoriesData) {
                    SubCategory subCategory = new SubCategory(
                            subData.getCode(),
                            subData.getName(),
                            subData.getImageUrl(),
                            category.getCategoryCode()
                    );
                    categoryRepo.addSubCategory(subCategory);
                }
            }
        });
    }
}
