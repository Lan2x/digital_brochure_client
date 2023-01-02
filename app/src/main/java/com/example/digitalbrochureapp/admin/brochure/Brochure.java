package com.example.digitalbrochureapp.admin.brochure;

import com.google.gson.annotations.SerializedName;

public class Brochure {
    private String title;
    private String image;
    private String pdf;

    public Brochure(String title, String image, String pdf) {
        this.title = title;
        this.image = image;
        this.pdf = pdf;
    }

    public String getTitle() {
        return title;
    }

    public String getImage() {
        return image;
    }

    public String getPdf() {
        return pdf;
    }


}
