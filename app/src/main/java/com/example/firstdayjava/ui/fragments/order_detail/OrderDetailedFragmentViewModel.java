package com.example.firstdayjava.ui.fragments.order_detail;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.firstdayjava.pojo.remote.callpack.BaseResponse;
import com.example.firstdayjava.pojo.remote.callpack.ResponseState;
import com.example.firstdayjava.pojo.remote.callpack.ResponsesCallBack;
import com.example.firstdayjava.pojo.remote.callpack.Result;
import com.example.firstdayjava.pojo.remote.models.orde_detile.OrderPostBody;
import com.example.firstdayjava.pojo.remote.models.orde_detile.OrderDetailResponse;
import com.example.firstdayjava.pojo.remote.models.orde_detile.OrderDetailsData;
import com.example.firstdayjava.pojo.repos.OrderRepo;

import java.util.List;

import javax.inject.Inject;

public class OrderDetailedFragmentViewModel extends AndroidViewModel {
    @Inject
    OrderRepo orderRepo;
    MutableLiveData<List<OrderDetailsData>> detailedListMDL;
    MutableLiveData<ResponseState> responseStateMDL;
    MutableLiveData<ResponseState> cancelResponseStateMDL;

    @Inject
    public OrderDetailedFragmentViewModel(@NonNull Application application) {
        super(application);
        detailedListMDL = new MutableLiveData<>();
        responseStateMDL = new MutableLiveData<>();
        cancelResponseStateMDL = new MutableLiveData<>();
    }

    public void getDetails(String orderId) {
        OrderPostBody postBody = new OrderPostBody(orderId);
        orderRepo.getOrderDetails(postBody, new ResponsesCallBack<OrderDetailResponse>() {
            @Override
            public void onSuccess(OrderDetailResponse response) {
                List<OrderDetailsData> dataList = response.getData().getDataList();
                detailedListMDL.postValue(dataList);
            }

            @Override
            public void onFailure(Result result) {
                responseStateMDL.postValue(new ResponseState(result.getErrMsg()));
            }
        });
    }

    public void cancelOrder(String orderId) {
        OrderPostBody postBody = new OrderPostBody(orderId);
        orderRepo.cancleOrder(postBody,new ResponsesCallBack<BaseResponse>() {
            @Override
            public void onSuccess(BaseResponse response) {
                cancelResponseStateMDL.postValue(new ResponseState());
            }

            @Override
            public void onFailure(Result result) {
                cancelResponseStateMDL.postValue(new ResponseState(result.getErrMsg()));
            }
        });
    }
}
