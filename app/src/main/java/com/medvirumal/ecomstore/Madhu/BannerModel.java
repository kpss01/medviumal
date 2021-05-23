package com.medvirumal.ecomstore.Madhu;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class BannerModel {

    @SerializedName("status")
    public String status;

    @SerializedName("message")
    public String message;

    @SerializedName("status_code")
    public String status_code;

    @SerializedName("data")
    public ArrayList<labtestlist> testlist = new ArrayList<>();

    public static class labtestlist {

        @SerializedName("image")
        public String image;

        @SerializedName("cat_id")
        public String cat_id;

        @SerializedName("cat_name")
        public String cat_name;

    }
}