package com.mbd.arestpigetrequest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView textViewResult;
    List<User> usersList;
    RecyclerView recyclerView;
    UserAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        recyclerView = findViewById(R.id.recycle_view);
        parseJson();

    }

    private void parseJson() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://reqres.in/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        JsonApiHolder jsonPlaceHolderApi = retrofit.create(JsonApiHolder.class);
        Call<JsonResponse> call = jsonPlaceHolderApi.getUsers();
        call.enqueue(new Callback<JsonResponse>() {
            @Override
            public void onResponse(Call<JsonResponse> call, Response<JsonResponse> response) {
                if (!response.isSuccessful()) {
                    textViewResult.setText("Code: " + response.code());
                    return;
                }
                JsonResponse posts = response.body();
                setRecyclerView(posts.getData());
                Log.d("myTag", String.valueOf(posts.getData()));
            }
            @Override
            public void onFailure(Call<JsonResponse> call, Throwable t) {
                Log.d("failure", t.getMessage());
            }
        });

    }

    private  void  setRecyclerView(List<User> users){
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new UserAdapter(this,getList(users));
        recyclerView.setAdapter(adapter);
    }

    private List<User> getList(List<User> users){
        List<User> userList =new ArrayList<>();
        for (User post : users) {
            Log.d("myTag", String.valueOf(post.getLast_name()));
            userList.add(new User(Integer.valueOf(post.getId()),String.valueOf(post.getEmail()),String.valueOf(post.getAvatar()),String.valueOf(post.getFirst_name() + " " +post.getLast_name())));

        }

        return userList;
    }
    

}
