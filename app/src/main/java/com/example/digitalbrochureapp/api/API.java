package com.example.digitalbrochureapp.api;


import com.example.digitalbrochureapp.admin.brochure.FileResponse;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;

public interface API {

    @FormUrlEncoded
    @POST("accounts/")
    Call<ResponseBody> createUser(
            @Field("fullName") String firstName,
            @Field("birthDate") String birthDate,
            @Field("gender") String gender,
            @Field("birthPlace") String birthPlace,
            @Field("email") String email,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("/accounts/login")
    Call<LoginResponse> loginUser(
            @Field("email") String email,
            @Field("password") String password
    );


    @GET("/getAllBrochure")
    Call<FileResponse> getBrochures();



}
