package com.example.firstdayjava.ui.fragments.product_page;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.firstdayjava.pojo.local.entities.Category;
import com.example.firstdayjava.pojo.local.entities.Product;
import com.example.firstdayjava.pojo.local.entities.setting.ProductPageFilter;
import com.example.firstdayjava.pojo.remote.callpack.ResponsesCallBack;
import com.example.firstdayjava.pojo.remote.callpack.Result;
import com.example.firstdayjava.pojo.remote.models.product.Item;
import com.example.firstdayjava.pojo.remote.models.product.ItemImage;
import com.example.firstdayjava.pojo.remote.models.product.ProductResponse;
import com.example.firstdayjava.pojo.repos.CategoryRepo;
import com.example.firstdayjava.pojo.repos.ProductFilterRepo;
import com.example.firstdayjava.pojo.repos.ProductRepo;
import com.example.firstdayjava.ui.fragments.ResponseState;

import java.util.List;

import javax.inject.Inject;

public class ProductsPageFragmentViewModel extends AndroidViewModel {

    @Inject
    ProductRepo productRepo;

    @Inject
    CategoryRepo categoryRepo;

    @Inject
    ProductFilterRepo filterRepo;

    MutableLiveData<List<Category>> categoryMDL;
    MutableLiveData<ResponseState> responseStateMDL;
    MutableLiveData<ProductPageFilter> filterLiveData;

    @Inject
    public ProductsPageFragmentViewModel(@NonNull Application application) {
        super(application);
        categoryMDL = new MutableLiveData<>();
        responseStateMDL = new MutableLiveData<>();
        filterLiveData = new MutableLiveData<>();
    }

    public void initFilter() {
        filterLiveData.postValue(filterRepo.initProductFilter());
    }


    public void getProducts() {
        productRepo.getProducts(new ResponsesCallBack<ProductResponse>() {
            @Override
            public void onSuccess(ProductResponse response) {
                List<Item> items = response.getData().getItems();
                for (Item item : items) {
                    Product product = convertItemToProduct(item);
                    productRepo.addProduct(product);
                }

                sendState(true, response.getResult());
            }

            private void sendState(Boolean isSuccessful, Result result) {
                ResponseState state = new ResponseState(isSuccessful, result.getErrMsg());
                responseStateMDL.postValue(state);
                List<Category> offlineCategories = categoryRepo.getOfflineCategories();
                Log.d("ProductsPageFVM", "sendState: 98456: " + offlineCategories.size());
                categoryMDL.postValue(offlineCategories);
            }

            private Product convertItemToProduct(Item item) {
                Product product = new Product();
                Log.d("ProductsPageFVM", "convertItemToProduct: 857 " + item.getItemCode());
                product.setItemCode(item.getItemCode());
                product.setName(item.getItemName());
                product.setPrice(item.getPrice());
                product.setItemNameF(item.getItemNameF());
                product.setDescription(item.getDescription());
                product.setDescriptionF(item.getDescriptionF());
                product.setCurrencyCode(item.getCurrencyCode());
                product.setCategoryCode(item.getCategoryCode());
                product.setSubCategoryCode(item.getSubCategoryCode());

                List<ItemImage> images = item.getImages();
                if (images.size() > 0) {
                    product.setImageUrl(images.get(0).getImageUrl());
                } else {
                    product.setImageUrl("null");
                }
                return product;
            }

            @Override
            public void onFailure(Result result) {
                sendState(false, result);
            }
        });
    }

    void updateProductFilter(ProductPageFilter filter) {
        filterRepo.updateFilter(filter);
    }
}

//- pageing
//- 19:11 01:15