package com.mao.practise617;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.mao.practise617.GameGuide.ApiService;
import com.mao.practise617.GameGuide.GameGuide;
import com.mao.practise617.GameGuide.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class GameGuideActivity extends AppCompatActivity {

    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_game_guide);

        // Initialize Retrofit
        Retrofit retrofit = RetrofitClient.getClient("http://yourserver.com");
        apiService = retrofit.create(ApiService.class);
    }


    private void fetchGameGuides() {
        Call<List<GameGuide>> call = apiService.getGameGuideAll();
        call.enqueue(new Callback<List<GameGuide>>() {
            @Override
            public void onResponse(Call<List<GameGuide>> call, Response<List<GameGuide>> response) {
                if (response.isSuccessful()) {
                    List<GameGuide> gameGuides = response.body();
                    // Handle the response
                    Log.d("MainActivity", "Game Guides: " + gameGuides);

                } else {
//                    Toast.makeText(MainActivity.this, "Failed to retrieve data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<GameGuide>> call, Throwable t) {

            }
        });
    }
}