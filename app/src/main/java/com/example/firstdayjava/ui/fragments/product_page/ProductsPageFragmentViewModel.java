package com.example.firstdayjava.ui.fragments.product_page;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.firstdayjava.pojo.local.entities.Category;
import com.example.firstdayjava.pojo.local.entities.Product;
import com.example.firstdayjava.pojo.local.entities.setting.ProductPageFilter;
import com.example.firstdayjava.pojo.remote.callpack.ResponseState;
import com.example.firstdayjava.pojo.remote.callpack.ResponsesCallBack;
import com.example.firstdayjava.pojo.remote.callpack.Result;
import com.example.firstdayjava.pojo.remote.models.product.Item;
import com.example.firstdayjava.pojo.remote.models.product.ItemImage;
import com.example.firstdayjava.pojo.remote.models.product.ProductResponse;
import com.example.firstdayjava.pojo.repos.CartRepo;
import com.example.firstdayjava.pojo.repos.CategoryRepo;
import com.example.firstdayjava.pojo.repos.ProductFilterRepo;
import com.example.firstdayjava.pojo.repos.ProductRepo;

import java.util.List;

import javax.inject.Inject;

public class ProductsPageFragmentViewModel extends AndroidViewModel {

    @Inject
    ProductRepo productRepo;

    @Inject
    CategoryRepo categoryRepo;

    @Inject
    ProductFilterRepo filterRepo;

    CartRepo cartRepo;

    MutableLiveData<List<Category>> categoryMDL;
    MutableLiveData<ResponseState> responseStateMDL;
    MutableLiveData<ProductPageFilter> filterLiveData;
    LiveData<Integer> amountLiveData;
    @Inject
    public ProductsPageFragmentViewModel(@NonNull Application application, CartRepo cartRepo) {
        super(application);
        categoryMDL = new MutableLiveData<>();
        responseStateMDL = new MutableLiveData<>();
        filterLiveData = new MutableLiveData<>();

        this.cartRepo = cartRepo;
        amountLiveData = cartRepo.getAmount();
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

                ResponseState state = new ResponseState();
                responseStateMDL.postValue(state);
                getOfflineCategory();
            }

            private void getOfflineCategory() {
                List<Category> offlineCategories = categoryRepo.getOfflineCategories();
                categoryMDL.postValue(offlineCategories);
            }

            private Product convertItemToProduct(Item item) {
                Product product = new Product();
                product.setItemCode(item.getItemCode());
                product.setName(item.getItemName());
                product.setPrice(item.getPrice());
                product.setItemNameF(item.getItemNameF());
                product.setDescription(item.getDescription());
                product.setDescriptionF(item.getDescriptionF());
                product.setCurrencyCode(item.getCurrencyCode());
                product.setCategoryCode(item.getCategoryCode());
                product.setSubCategoryCode(item.getSubCategoryCode());
                product.setFavState(Product.NOT_IN_FAV_STATE);

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
                ResponseState state = new ResponseState(result.getErrMsg());
                responseStateMDL.postValue(state);
                getOfflineCategory();
            }
        });
    }

    void updateProductFilter(ProductPageFilter filter) {
        filterRepo.updateFilter(filter);
    }
}

//- pageing
//- 19:11 01:15
