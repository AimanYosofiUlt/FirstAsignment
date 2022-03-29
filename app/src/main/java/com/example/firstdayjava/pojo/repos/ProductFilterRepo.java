package com.example.firstdayjava.pojo.repos;

import androidx.lifecycle.LiveData;

import com.example.firstdayjava.pojo.local.database.AppDao;
import com.example.firstdayjava.pojo.local.entities.setting.ProductPageFilter;

import javax.inject.Inject;

public class ProductFilterRepo {

  public   AppDao appDao;
    public LiveData<ProductPageFilter> filterLiveData;

    @Inject
    public ProductFilterRepo(AppDao appDao) {
        this.appDao = appDao;
    }

    public ProductPageFilter initProductFilter() {
        appDao.initProductFilter();
        return appDao.getProductFilter();
    }

    public void updateFilter(ProductPageFilter filter) {
        appDao.updateProductFilter(filter);
    }
}
