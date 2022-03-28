package com.example.firstdayjava.app;

import android.app.Application;
import com.example.firstdayjava.pojo.local.database.AppDao;
import com.example.firstdayjava.pojo.local.database.AppDatabase;
import com.example.firstdayjava.pojo.remote.api.UltimateApi;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ActivityComponent;
import dagger.hilt.android.scopes.ActivityScoped;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@dagger.Module
@InstallIn(ActivityComponent.class)
public abstract class Module {
    @Qualifier
    @Retention(RetentionPolicy.RUNTIME)
    private @interface JsonPlaceHolderApi {
    }

    @JsonPlaceHolderApi
    @Provides
    public static String provideBaseUrl() {
        return "http://mapp.yemensoft.net/UltimateStore/api/";
    }

    @ActivityScoped
    @Provides
    public static HttpLoggingInterceptor provideHttpLogging() {
        return new HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY);
    }

    @ActivityScoped
    @Provides
    public static OkHttpClient provideOkHttpClient(HttpLoggingInterceptor interceptor) {
        return new OkHttpClient.Builder()
                .addInterceptor(interceptor).build();
    }

    @ActivityScoped
    @Provides
    public static Retrofit provideRetrofit(@JsonPlaceHolderApi String baseUrl, OkHttpClient client) {
        return new Retrofit.Builder()
                .client(client)
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @ActivityScoped
    @Provides
    public static UltimateApi provideUltimateApi(Retrofit retrofit){
        return retrofit.create(UltimateApi.class);
    }

    @ActivityScoped
    @Provides
    public static AppDatabase provideAppDatabase(Application application){
        return AppDatabase.getDatabase(application);
    }

    @ActivityScoped
    @Provides
    public static AppDao provideAppDao(AppDatabase database){
        return database.appDao();
    }
}