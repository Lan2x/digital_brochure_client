package com.example.digitalbrochureapp.admin.brochure;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.digitalbrochureapp.R;
import com.example.digitalbrochureapp.admin.MainActivity;
import com.github.barteksc.pdfviewer.PDFView;

import org.apache.commons.io.IOUtils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import retrofit2.Callback;
import retrofit2.Response;



public class BrochureAdapter extends RecyclerView.Adapter<BrochureAdapter.BrochureViewHolder> {

    private List<Brochure> brochures;
    private PDFView mpdfView;


        public static class BrochureViewHolder extends RecyclerView.ViewHolder {
            public TextView titleTextView;
            public ImageView imageView;
            public TextView pdfTextView;

            public BrochureViewHolder(View view) {
                super(view);
                titleTextView = view.findViewById(R.id.brochureTitle);
                imageView = view.findViewById(R.id.brochureImage);
                pdfTextView = view.findViewById(R.id.brochurePdf);
            }
        }


        public BrochureAdapter(List<Brochure> brochures, PDFView pdfView) {
            this.brochures = brochures;
            this.mpdfView = pdfView;
        }

        @Override
        public BrochureViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.admin_brochure_entry, parent, false);
            return new BrochureViewHolder(view);
        }



    @Override
        public void onBindViewHolder(BrochureViewHolder holder, int position) {
            Brochure brochure = brochures.get(position);

            byte[] imageByteArray = Base64.decode(brochure.getImage(), Base64.DEFAULT);

            holder.titleTextView.setText(brochure.getTitle());
            Glide.with(holder.imageView.getContext())
                    .load(imageByteArray)
                    .into(holder.imageView);

            holder.pdfTextView.setText("Open PDF");
            holder.pdfTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Toast.makeText(view.getContext(), "Loading", Toast.LENGTH_SHORT).show();

                    Retrofit retrofit = new Retrofit.Builder().baseUrl("http://192.168.254.118:1111/brochure/")
                            .addConverterFactory(GsonConverterFactory.create()).build();

                    BrochureApi brochureApi = retrofit.create(BrochureApi.class);
                    Call<ResponseBody> call = brochureApi.getPdf(brochure.getPdf());
                    call.enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            if (response.isSuccessful()) {
                                mpdfView.setVisibility(View.VISIBLE);


                                Log.d("balugaw", "id");

                                Log.d("balugaw", "id sa pdfview: "+String.valueOf(mpdfView));

                                Log.d("balugaw", "id random: "+view.findViewById(R.id.brochureTitle));

//
                                byte[] fileBytes = new byte[0];
                                try {
                                    fileBytes = response.body().bytes();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                InputStream inputStream = new ByteArrayInputStream(fileBytes);
                                mpdfView.fromStream(inputStream).load();



                                try {
                                    writeResponseBodyToStorage(response.body());
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }

                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            Toast.makeText(view.getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    });
                }

            });
        }

    private void writeResponseBodyToStorage(ResponseBody body) throws IOException {
        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "file.pdf");

        InputStream inputStream = null;
        OutputStream outputStream = null;

        try {
            inputStream = body.byteStream();
            outputStream = new FileOutputStream(file);

            byte[] fileReader = new byte[4096];

            while (true) {
                int read = inputStream.read(fileReader);

                if (read == -1) {
                    break;
                }

                outputStream.write(fileReader, 0, read);
            }

            outputStream.flush();
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }

            if (outputStream != null) {
                outputStream.close();
            }
        }
    }


    @Override
        public int getItemCount() {
            return brochures.size();
        }
    }



