package com.example.firstdayjava.pojo.dbs.models.responses.callpack;

import androidx.annotation.NonNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public abstract class ResponsesCallBack<T extends ResponseObject> implements Callback<T> {
    public abstract void onSuccess(T response);

    public abstract void onFailure(Result result);

    @Override
    final public void onResponse(@NonNull Call<T> call, Response<T> response) {
        if (response.isSuccessful()) {
            ResponseObject responseObject = response.body();
            if (responseObject == null) {
                onFailure(new Result(10, "fail api"));
            } else {
                if (responseObject.getResult() != null) {
                    if (responseObject.getResult().getErrNo() == 0) {
                        onSuccess(response.body());

                    } else {
                        onFailure(responseObject.getResult());
                    }
                } else {
                    onFailure(new Result(10, "fail api"));

                }
            }
        } else {
            onFailure(new Result(response.code(), response.message()));
        }

    }

    @Override
    final public void onFailure(@NonNull Call<T> call, @NonNull Throwable t) {
        onFailure(new Result(10, t.getMessage()));
    }
}

