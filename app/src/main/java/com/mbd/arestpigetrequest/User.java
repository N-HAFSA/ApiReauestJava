package com.mbd.arestpigetrequest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {


    @SerializedName("id")
    @Expose
    private int  id;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("first_name")
    @Expose
    private String  first_name;
    @SerializedName("last_name")
    @Expose
    private String  last_name;
    @SerializedName("avatar")
    @Expose
    private String avatar;

    public User(int id, String email, String avatar, String first_name ) {
        this.id = id;
        this.email = email;
        this.first_name = first_name;
        this.avatar = avatar;
    }

    public String getAvatar() {
        return avatar;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }
}
