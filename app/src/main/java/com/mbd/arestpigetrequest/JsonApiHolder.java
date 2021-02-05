package com.mbd.arestpigetrequest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface  JsonApiHolder {

    @GET("users")
    Call<JsonResponse> getUsers();
}
