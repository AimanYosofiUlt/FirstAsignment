package com.example.firstdayjava.pojo.repos;

import com.example.firstdayjava.pojo.local.database.AppDao;
import com.example.firstdayjava.pojo.local.entities.Product;
import com.example.firstdayjava.pojo.local.entities.setting.ProductPageFilter;
import com.example.firstdayjava.pojo.remote.api.UltimateApi;
import com.example.firstdayjava.pojo.remote.callpack.ResponsesCallBack;
import com.example.firstdayjava.pojo.remote.callpack.Result;
import com.example.firstdayjava.pojo.remote.models.product.ProductPostBody;
import com.example.firstdayjava.pojo.remote.models.product.ProductResponse;
import com.example.firstdayjava.ui.views.ProductView.ProductViewData;

import java.util.List;

import javax.inject.Inject;

public class ProductRepo {
    @Inject
    UltimateApi api;

    @Inject
    AppDao appDao;

    @Inject
    public ProductRepo() {
    }

    public void getProducts(ResponsesCallBack<ProductResponse> callBack) {
        ProductPostBody postBody = new ProductPostBody();
        api.getProducts(postBody).enqueue(new ResponsesCallBack<ProductResponse>() {
            @Override
            public void onSuccess(ProductResponse response) {
                callBack.onSuccess(response);
            }

            @Override
            public void onFailure(Result result) {
                callBack.onFailure(result);
            }
        });
    }


    public void addProduct(Product product) {
        appDao.insertProduct(product);
    }

    public List<ProductViewData> getProductsOffline(String catCode, String subCatCode, String orderBy) {
        return appDao.getProductData(catCode,subCatCode,orderBy);
    }

}
