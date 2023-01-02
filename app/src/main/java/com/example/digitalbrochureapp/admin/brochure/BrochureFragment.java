package com.example.digitalbrochureapp.admin.brochure;

import static android.app.Activity.RESULT_OK;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.digitalbrochureapp.R;
import com.example.digitalbrochureapp.admin.MainActivity;
import com.hbisoft.pickit.PickiT;
import com.hbisoft.pickit.PickiTCallbacks;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BrochureFragment extends Fragment implements PickiTCallbacks {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final int PICK_PDF_REQUEST = 2;
    private static final int REQUEST_CODE_READ_CALL_LOG = 1;

    // TODO: Rename and change types of parameters

    PickiT pickiT;

    RecyclerView recyclerView;
    ArrayList<Brochure> appList;
    BrochureAdapter locationAdapter;


    ImageView imageView;
    String pathForImage;
    String pathForPdf;
    private MainActivity mainActivity;

    private static final int REQUEST_PERMISSIONS = 1;
    private static String[] PERMISSIONS = {
            Manifest.permission.INTERNET,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE
    };


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mainActivity = (MainActivity) context;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.admin_fragment_brochure, container, false);
//
//        mButtonSelectImage = view.findViewById(R.id.brochureSelectThumbnailBtn);
//        mButtonSelectPdf = view.findViewById(R.id.brochureSelectPdfBtn);
//        mTextViewPdfName = view.findViewById(R.id.brochureSelectedPdfText);
//        mImageView = view.findViewById(R.id.thumbnailImageView);

        recyclerView = view.findViewById(R.id.locationRecycleView);
        appList = new ArrayList<>();
        locationAdapter = new BrochureAdapter(appList, view.findViewById(R.id.pdfView));
        recyclerView.setAdapter(locationAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        getAllBrochure();



        // Check if the app has the necessary permissions
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            // If the app doesn't have the necessary permissions, request them
            ActivityCompat.requestPermissions(getActivity(), PERMISSIONS, REQUEST_PERMISSIONS);
        } else {
            // If the app has the necessary permissions, proceed with the app logic
        }


          view.findViewById(R.id.select_image).setOnClickListener(v->{
            if (ContextCompat.checkSelfPermission(getContext(),
                    Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, 10);
            } else {
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
            }
        });



        imageView = view.findViewById(R.id.imageview);

        view.findViewById(R.id.save).setOnClickListener(v->{
            EditText name = view.findViewById(R.id.name);
            EditText reference = view.findViewById(R.id.reference);

            addCustomer(name.getText().toString(),reference.getText().toString());
            Toast.makeText(getContext(), "not ffffffffff", Toast.LENGTH_SHORT).show();



        });

        view.findViewById(R.id.select_pdf).setOnClickListener(v -> {
            if (ContextCompat.checkSelfPermission(getContext(),
                    Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                Intent intent = new Intent();
                intent.setType("application/pdf");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent,  20);
            } else {
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
            }
        });

        //Initialize PickiT
        //context, listener, activity
        pickiT = new PickiT(getContext(), this, getActivity());

//        appList.add(new Brochure("balugaw",R.drawable.ic_baseline_calendar_month_24, "aaaa"));

        return view;
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 10 && resultCode == getActivity().RESULT_OK) {
            Uri uri = data.getData();
            Log.d("balugaw", "imageUri: "+uri);

            Context context = getContext();
             pathForImage = RealPathUtil.getRealPath(context, uri);
            Log.d("balugaw", "imagepath: "+ pathForImage);
            Log.d("balugaw", "imageuri: "+uri);
            Log.d("balugaw", "image autorighty: "+uri.getAuthority());

            Bitmap bitmap = BitmapFactory.decodeFile(pathForImage);
            imageView.setImageBitmap(bitmap);
        }
        else if (requestCode == 20 && resultCode == RESULT_OK && data != null) {
            // Do something with the PDF (e.g. display it in a WebView)
            // ...
            pickiT.getPath(data.getData(), Build.VERSION.SDK_INT);

        }
    }

    public void addCustomer(String name, String refernce) {

        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://192.168.254.118:1111/brochure/")
                .addConverterFactory(GsonConverterFactory.create()).build();

        File file = new File(pathForImage);
        RequestBody requestFile = RequestBody.create(MediaType.parse("image/jpeg"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("image", file.getName(), requestFile);

        File pdfFile = new File(pathForPdf);
        RequestBody pdfRequestFile = RequestBody.create(MediaType.parse("application/pdf"), pdfFile);
        MultipartBody.Part pdfBody = MultipartBody.Part.createFormData("pdf", pdfFile.getName(), pdfRequestFile);

        RequestBody cus_name = RequestBody.create(MediaType.parse("text/plain"),name);
        RequestBody cus_reference = RequestBody.create(MediaType.parse("text/plain"), refernce);

        Log.d("balugaw", "image: " + file.getName());
        Log.d("balugaw", "pdf: " + pdfFile.getAbsolutePath());

        ApiService apiService = retrofit.create(ApiService.class);
        Call<FileResponse> call = apiService.addCustomer(body, pdfBody, cus_name);
        call.enqueue(new Callback<FileResponse>() {
            @Override
            public void onResponse(Call<FileResponse> call, Response<FileResponse> response) {
                Log.d("balugaw","string message: "+ response.message().toString());

                if (response.isSuccessful()) {

                    Log.d("balugaw","string message 22222: "+ response.message().toString());

                    if (response.body().getMessage().contains("success")) {
                        // Get the brochure array from the response
                        Toast.makeText(getContext(), response.body().getMessage().toString(), Toast.LENGTH_SHORT).show();
                    }
                    } else {
                        Toast.makeText(getContext(), response.body().getMessage().toString(), Toast.LENGTH_SHORT).show();
                    }
            }

            @Override
            public void onFailure(Call<FileResponse> call, Throwable t) {
                Log.d("balugaw",t.toString());
                Toast.makeText(getContext(), t.toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void getAllBrochure(){


        Log.d("balugaw","getallbrochure");

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.254.118:1111/brochure/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        BrochureApi brochureApi = retrofit.create(BrochureApi.class);
        Call<List<Brochure>> call = brochureApi.getBrochures();
        call.enqueue(new Callback<List<Brochure>>() {
            @Override
            public void onResponse(Call<List<Brochure>> call, Response<List<Brochure>> response) {
                Log.d("balugaw","responsesssssss");

                if (response.isSuccessful()) {
                    Log.d("balugaw","sucessss");

                    List<Brochure> brochures = response.body();
                        // Clear the list of apps
                        appList.clear();
                        for (Brochure app : brochures) {
                            appList.add(new Brochure(app.getTitle(),app.getImage(), app.getPdf()));
                        }
                        // Notify the adapter that the list of children has changed
                        locationAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<Brochure>> call, Throwable t) {
                Log.d("balugaw",""+t.getMessage());
            }
        });

    }


    @Override
    public void PickiTonUriReturned() {

    }

    @Override
    public void PickiTonStartListener() {

    }

    @Override
    public void PickiTonProgressUpdate(int progress) {

    }

    @Override
    public void PickiTonCompleteListener(String path, boolean wasDriveFile, boolean wasUnknownProvider, boolean wasSuccessful, String Reason) {
        Log.d("balugaw", "pdfRealpathFInal: "+path);
        pathForPdf = path;
    }

    @Override
    public void PickiTonMultipleCompleteListener(ArrayList<String> paths, boolean wasSuccessful, String Reason) {

    }
}
