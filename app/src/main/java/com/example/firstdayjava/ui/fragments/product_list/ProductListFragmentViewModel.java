package com.example.firstdayjava.ui.fragments.product_list;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.firstdayjava.pojo.local.entities.Cart;
import com.example.firstdayjava.pojo.local.entities.Product;
import com.example.firstdayjava.pojo.local.entities.SubCategory;
import com.example.firstdayjava.pojo.local.entities.setting.ProductPageFilter;
import com.example.firstdayjava.pojo.repos.AppSettingRepo;
import com.example.firstdayjava.pojo.repos.CartRepo;
import com.example.firstdayjava.pojo.repos.ProductFilterRepo;
import com.example.firstdayjava.pojo.repos.ProductRepo;
import com.example.firstdayjava.pojo.repos.SubCategoryRepo;
import com.example.firstdayjava.ui.views.ProductView.ProductViewData;

import java.util.List;

import javax.inject.Inject;

public class ProductListFragmentViewModel extends AndroidViewModel {

    @Inject
    ProductRepo productRepo;

    @Inject
    SubCategoryRepo subCategoryRepo;

    @Inject
    AppSettingRepo settingRepo;

    @Inject
    CartRepo cartRepo;
    ProductFilterRepo filterRepo;

    MutableLiveData<List<ProductViewData>> productDataMDL;
    MutableLiveData<List<SubCategory>> subCategoryMDL;
    LiveData<ProductPageFilter> filterLiveData;


    @Inject
    public ProductListFragmentViewModel(@NonNull Application application, ProductFilterRepo filterRepo) {
        super(application);
        productDataMDL = new MutableLiveData<>();
        subCategoryMDL = new MutableLiveData<>();
        this.filterRepo = filterRepo;
        filterLiveData = filterRepo.appDao.getProductFilterLive();

    }

    public void getProducts(String catCode, String subCatCode, String orderBy) {
        List<ProductViewData> productsOffline = productRepo.getProductsOffline(catCode, subCatCode, orderBy);
        productDataMDL.postValue(productsOffline);
    }

    public void getSubCategory(String catCode) {
        List<SubCategory> subCategories = subCategoryRepo.getSubCategory(catCode);
        subCategoryMDL.postValue(subCategories);
    }

    void addToCart(int amount, Product product) {
        Cart cart = new Cart(product.getItemCode(), settingRepo.getUserCode(), amount);
        cartRepo.addToCart(cart);
    }

    public void deleteFromCart(Product product) {
        Cart cart = new Cart(product.getItemCode(), settingRepo.getUserCode(), 0);
        cartRepo.deleteFromCart(cart);
    }
}
