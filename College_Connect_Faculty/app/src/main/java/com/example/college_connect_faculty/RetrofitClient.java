package com.example.college_connect_faculty;

import android.content.Context;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    static Context context;


    final String BASE_URL = "http://192.168.153.223/college_connect/";
    private static RetrofitClient mInstance;
    Retrofit retrofit;

    RetrofitClient(){
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
    public static synchronized RetrofitClient getInstance(){
        if (mInstance==null){
            mInstance = new RetrofitClient();
        }
        return mInstance;
    }

    public api getApi(){
        return retrofit.create(api.class);
    }

}
