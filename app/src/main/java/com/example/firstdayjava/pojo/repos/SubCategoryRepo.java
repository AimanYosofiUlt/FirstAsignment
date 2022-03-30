package com.example.firstdayjava.pojo.repos;

import com.example.firstdayjava.pojo.local.database.AppDao;
import com.example.firstdayjava.pojo.local.entities.Category;
import com.example.firstdayjava.pojo.local.entities.SubCategory;

import java.util.List;

import javax.inject.Inject;

public class SubCategoryRepo {
    @Inject
    AppDao appDao;

    @Inject
    SubCategoryRepo() {

    }

   public List<SubCategory> getSubCategory(String catCode) {
        return appDao.getSubCategories(catCode);
    }
}
