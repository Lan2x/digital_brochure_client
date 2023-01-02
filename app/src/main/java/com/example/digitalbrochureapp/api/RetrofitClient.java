package com.example.digitalbrochureapp.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static final String BASE_URL = "http://192.168.8.106:1111"; // adi an ip san computer hosting the nodejs server
    private static RetrofitClient mInstance;
    private Retrofit retrofit;

    private RetrofitClient(){ //constructor
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
    //adi an pag access san retrofit na class
    public static synchronized RetrofitClient getInstance(){
        if(mInstance == null){
            mInstance = new RetrofitClient();
        }
        return mInstance;
    }
    public API getAPI(){
        return retrofit.create(API.class);
    }
}
