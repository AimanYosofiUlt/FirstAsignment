package com.example.firstdayjava.ui.fragments.product_info;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.firstdayjava.pojo.local.entities.Cart;
import com.example.firstdayjava.pojo.local.entities.Product;
import com.example.firstdayjava.pojo.repos.AppSettingRepo;
import com.example.firstdayjava.pojo.repos.CartRepo;
import com.example.firstdayjava.pojo.repos.ProductRepo;
import com.example.firstdayjava.ui.views.ProductView.ProductViewData;

import java.util.List;

import javax.inject.Inject;

public class ProductsInfoFragmentViewModel extends AndroidViewModel {
    @Inject
    AppSettingRepo settingRepo;
    @Inject
    CartRepo cartRepo;
    @Inject
    ProductRepo productRepo;


    MutableLiveData<List<ProductViewData>> otherProductDML;
    MutableLiveData<Integer> faveStateLiveData;

    @Inject
    public ProductsInfoFragmentViewModel(@NonNull Application application) {
        super(application);
        otherProductDML = new MutableLiveData<>();
        faveStateLiveData = new MutableLiveData<>();
    }

    public void getOtherProduct(String itemCode, String categoryCode) {
        otherProductDML.postValue(productRepo.getOtherProduct(itemCode, categoryCode));

    }

    public void addToCart(int amount, Product product) {
        Cart cart = new Cart(product.getItemCode(), settingRepo.getUserCode(), amount);
        cartRepo.addToCart(cart);
    }

    public void deleteFromCart(Product product) {
        Cart cart = new Cart(product.getItemCode(), settingRepo.getUserCode(), 0);
        cartRepo.deleteFromCart(cart);
    }

    public void setProductFavState(Product product) {
        productRepo.setProductFavState(product);
        getFavState(product.getItemCode());
    }

    public void getFavState(String itemCode){
        faveStateLiveData.postValue(productRepo.getFavState(itemCode));
    }
}

//- pageing
//- 19:11 01:15
