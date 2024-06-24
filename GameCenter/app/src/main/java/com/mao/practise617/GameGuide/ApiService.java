package com.mao.practise617.GameGuide;

import com.mao.practise617.GameGuide.GameGuide;

import retrofit2.Call;
import retrofit2.http.GET;
import java.util.List;

public interface ApiService {
    @GET("/gameGuideAll")
    Call<List<GameGuide>> getGameGuideAll();
}
