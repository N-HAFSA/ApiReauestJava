package com.mbd.arestpigetrequest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class JsonResponse {

    @SerializedName("page")
    @Expose
    private int page;
    @SerializedName("per_page")
    @Expose
    private int per_page;
    @SerializedName("total")
    @Expose
    private int total;
    @SerializedName("total_pages")
    @Expose
    private int total_pages;

    @SerializedName("data")
    @Expose
    private List<User> data = null;

    public int getPage() {
        return page;
    }

    public int getPer_page() {
        return per_page;
    }

    public int getTotal() {
        return total;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public List<User> getData() {
        return data;
    }
}
