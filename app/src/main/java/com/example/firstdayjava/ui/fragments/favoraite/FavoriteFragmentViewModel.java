package com.example.firstdayjava.ui.fragments.favoraite;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.firstdayjava.pojo.local.entities.Cart;
import com.example.firstdayjava.pojo.local.entities.Product;
import com.example.firstdayjava.pojo.repos.AppSettingRepo;
import com.example.firstdayjava.pojo.repos.CartRepo;
import com.example.firstdayjava.ui.views.ProductView.ProductViewData;

import java.util.List;

import javax.inject.Inject;

public class FavoriteFragmentViewModel extends AndroidViewModel {
    @Inject
    AppSettingRepo settingRepo;

    CartRepo cartRepo;

    LiveData<List<ProductViewData>> favoriteDataLiveData;

    @Inject
    public FavoriteFragmentViewModel(@NonNull Application application, CartRepo cartRepo) {
        super(application);
        this.cartRepo = cartRepo;
        favoriteDataLiveData = cartRepo.getFavoriteData();
    }

    public void addToCart(int amount, Product product) {
        Cart cart = new Cart(product.getItemCode(), settingRepo.getUserCode(), amount);
        cartRepo.addToCart(cart);
    }

    public void deleteFromCart(Product product) {
        Cart cart = new Cart(product.getItemCode(), settingRepo.getUserCode(), 0);
        cartRepo.deleteFromCart(cart);
    }
}
