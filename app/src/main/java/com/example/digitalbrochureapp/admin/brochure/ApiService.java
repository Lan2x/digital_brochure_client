package com.example.digitalbrochureapp.admin.brochure;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ApiService {

    @Multipart
    @POST("upload")
    Call<FileResponse> addCustomer(@Part MultipartBody.Part image,
                                     @Part MultipartBody.Part pdf,
                                     @Part("title") RequestBody title   );

}


