package com.example.firstdayjava.pojo.repos;

import androidx.lifecycle.LiveData;

import com.example.firstdayjava.pojo.local.database.AppDao;
import com.example.firstdayjava.pojo.local.entities.Cart;

import javax.inject.Inject;

public class CartRepo {
    @Inject
    AppDao appDao;

    @Inject
    public CartRepo() {
    }

   public void addToCart(Cart cart) {
        appDao.insertCart(cart);
    }

    public void deleteFromCart(Cart cart) {
        appDao.deleteCart(cart);
    }

    public LiveData<Integer> getAmount() {
        return appDao.getAmount();
    }
}
