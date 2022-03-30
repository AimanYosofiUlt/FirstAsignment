package com.example.firstdayjava.ui.fragments.product_list;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.firstdayjava.pojo.local.entities.Product;
import com.example.firstdayjava.pojo.local.entities.SubCategory;
import com.example.firstdayjava.pojo.local.entities.setting.ProductPageFilter;
import com.example.firstdayjava.pojo.repos.ProductFilterRepo;
import com.example.firstdayjava.pojo.repos.ProductRepo;
import com.example.firstdayjava.pojo.repos.SubCategoryRepo;

import java.util.List;

import javax.inject.Inject;

public class ProductListFragmentViewModel extends AndroidViewModel {

    @Inject
    ProductRepo productRepo;

    @Inject
    SubCategoryRepo subCategoryRepo;

    ProductFilterRepo filterRepo;
    MutableLiveData<List<Product>> productMDL;
    MutableLiveData<List<SubCategory>> subCategoryMDL;
    LiveData<ProductPageFilter> filterLiveData;

    @Inject
    public ProductListFragmentViewModel(@NonNull Application application, ProductFilterRepo filterRepo) {
        super(application);
        productMDL = new MutableLiveData<>();
        subCategoryMDL = new MutableLiveData<>();
        this.filterRepo = filterRepo;
        filterLiveData = filterRepo.appDao.getProductFilterLive();
    }

    public void getProducts(String catCode, String subCatCode, String orderBy) {
        List<Product> productsOffline = productRepo.getProductsOffline(catCode, subCatCode, orderBy);
        productMDL.postValue(productsOffline);
        Log.d("ProductListFVM", "getProducts: 651:" + orderBy);
    }

    public void getSubCategory(String catCode){
        List<SubCategory> subCategories = subCategoryRepo.getSubCategory(catCode);
        subCategoryMDL.postValue(subCategories);
    }

}
