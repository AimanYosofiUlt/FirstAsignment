package com.example.firstdayjava.ui.fragments.order;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.firstdayjava.pojo.remote.callpack.ResponseState;
import com.example.firstdayjava.pojo.remote.callpack.ResponsesCallBack;
import com.example.firstdayjava.pojo.remote.callpack.Result;
import com.example.firstdayjava.pojo.remote.models.getOrder.GetOrderPostBody;
import com.example.firstdayjava.pojo.remote.models.getOrder.GetOrderResponse;
import com.example.firstdayjava.pojo.remote.models.getOrder.OrderMaster;
import com.example.firstdayjava.pojo.repos.AppSettingRepo;
import com.example.firstdayjava.pojo.repos.OrderRepo;

import java.util.List;

import javax.inject.Inject;

public class OrderFragmentViewModel extends AndroidViewModel {

    @Inject
    OrderRepo orderRepo;

    @Inject
    AppSettingRepo settingRepo;

    MutableLiveData<List<OrderMaster>> orderMastersMDL;
    MutableLiveData<ResponseState> responseStateMDL;
    MutableLiveData<Boolean> clearDataMDL;

    @Inject
    public OrderFragmentViewModel(@NonNull Application application) {
        super(application);
        orderMastersMDL = new MutableLiveData<>();
        responseStateMDL = new MutableLiveData<>();
        clearDataMDL = new MutableLiveData<>();
    }

    void getOrders() {
        String userCode = settingRepo.getUserCode();
        GetOrderPostBody postBody = new GetOrderPostBody(userCode);
        orderRepo.getOrders(postBody, new ResponsesCallBack<GetOrderResponse>() {
            @Override
            public void onSuccess(GetOrderResponse response) {
                List<OrderMaster> masterList = response.getData().getOrderMasterList();
                orderMastersMDL.postValue(masterList);
            }

            @Override
            public void onFailure(Result result) {
                if (result.getErrNo() == 1) {
                    clearDataMDL.postValue(true);
                } else {
                    responseStateMDL.postValue(new ResponseState(result.getErrMsg()));
                }
            }
        });
    }
}
