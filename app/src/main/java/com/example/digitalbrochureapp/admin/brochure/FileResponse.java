package com.example.digitalbrochureapp.admin.brochure;


import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FileResponse {
    private String message;
    private List<Brochure> brochure;

    public String getMessage() {
        return message;
    }

    public List<Brochure> getBrochure() {
        return brochure;
    }
}
