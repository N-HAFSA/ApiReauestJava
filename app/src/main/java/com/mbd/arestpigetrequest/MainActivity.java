package com.mbd.arestpigetrequest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private TextView textViewResult;
    List<User> usersList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       // textViewResult = findViewById(R.id.text_view_result);
        usersList = new ArrayList<>();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://reqres.in/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        JsonApiHolder request = retrofit.create(JsonApiHolder.class);
        Call<JsonResponse> call1 = request.getUsers();
        call1.enqueue(new Callback<JsonResponse>() {
            @Override
            public void onResponse(Call<JsonResponse> call, Response<JsonResponse> response) {
                Toast.makeText(MainActivity.this, "Success! response for first item >> \n car :" + response.body().getData().get(0).getLast_name() + "\ndesc :" + response.body().getData().get(0).getFirst_name(), Toast.LENGTH_SHORT).show();
                textViewResult.setText("Code: " + response.body().getData().get(0).getLast_name() );
            }

            @Override
            public void onFailure(Call<JsonResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Failure", Toast.LENGTH_SHORT).show();
            }

        });
    }

    private void parseJson() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://reqres.in/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonApiHolder request = retrofit.create(JsonApiHolder.class);
        Call<JsonResponse> call1=request.getUsers();
        call1.enqueue(new Callback<JsonResponse>() {
            @Override
            public void onResponse(Call<JsonResponse> call, Response<JsonResponse> response) {
                Toast.makeText(MainActivity.this, "Success! response for first item >> \n car :" + response.body().getData().get(0).getLast_name() + "\ndesc :" + response.body().getData().get(0).getFirst_name(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<JsonResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this,"Failure",Toast.LENGTH_SHORT).show();
            }

        });
    }
}
