package com.example.digitalbrochureapp.admin.brochure;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface BrochureApi {
    @GET("getAllBrochure")
    Call<List<Brochure>> getBrochures();

    @FormUrlEncoded
    @POST("getOnePdf")
    Call<ResponseBody> getPdf(
            @Field("filename") String code
    );
}