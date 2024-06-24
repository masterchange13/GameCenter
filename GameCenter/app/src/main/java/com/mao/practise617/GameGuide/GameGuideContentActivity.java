package com.mao.practise617.GameGuide;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.gson.Gson;
import com.mao.practise617.IpAddr;
import com.mao.practise617.Network.Network;
import com.mao.practise617.Network.ResponseCallback;
import com.mao.practise617.R;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class GameGuideContentActivity extends AppCompatActivity {

    private final OkHttpClient client = new OkHttpClient();
    private IpAddr ipAddr = new IpAddr();
    Network network = new Network();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
//        setContentView(R.layout.activity_game_guide_content);
        setContentView(R.layout.game_introduce);

        // Retrieve data from Intent
        Intent intent = getIntent();
        int id = intent.getIntExtra("itemId", -1);
//        int id = intent.getIntExtra("guideId", -1);
        String name = intent.getStringExtra("gameName");
        String content = intent.getStringExtra("guideContent");

        String url = ipAddr.getIpGameDetail() + "/detail/" + id;

        try {
            getData(url, id);
//            handleData(data);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void getData(String url, int id) {
        network.sendGetRequestWithThread(url, new ResponseCallback() {
            @Override
            public void onSuccess(String response) {
                // 在这里处理成功响应
                System.out.println("响应成功: " + response);

                handleData(response);
            }

            @Override
            public void onFailure(Exception e) {
                // 在这里处理失败情况
                System.out.println("请求失败: " + e.getMessage());
            }
        });
//        return res[0];
    }

    public void handleData(String data){
        // 使用 Gson 将响应字符串转换为 JSON 对象
        Gson gson = new Gson();
        GameGuide gameGuideObject = gson.fromJson(data, GameGuide.class);

        // 在这里处理解析后的 JSON 对象

        // 例如，获取游戏指南的标题和内容
        String gameName = gameGuideObject.getGameName();
        String content = gameGuideObject.getGuideContent();

        // 更新 UI
        TextView titleTextView = findViewById(R.id.game_guide_content_title);
        TextView contentTextView = findViewById(R.id.game_guide_content_text);
        titleTextView.setText(gameName);
        contentTextView.setText(content);
    }
}

