package com.mao.practise617.GameStar;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.mao.practise617.GameGuide.GameGuide;
import com.mao.practise617.IpAddr;
import com.mao.practise617.Network.Network;
import com.mao.practise617.Network.ResponseCallback;
import com.mao.practise617.R;

public class StarIntroduceActivity extends AppCompatActivity {
    private IpAddr ipAddr = new IpAddr();
    private Network network = new Network();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.star_introduce);

        // Retrieve data from Intent
        Intent intent = getIntent();
        int id = intent.getIntExtra("itemId", -1);
        String name = intent.getStringExtra("starName");
        String content = intent.getStringExtra("starDescription");

        String url = ipAddr.getIpGameStar() + "/detail/" + id;
        getData(url);


        // Display the data
//        TextView nameTextView = findViewById(R.id.star_name_1);
//        TextView contentTextView = findViewById(R.id.star_content_1);
//
//        nameTextView.setText(name);
//        contentTextView.setText(content);


    }

    public void getData(String url) {
        network.sendGetRequestWithThread(url, new ResponseCallback() {
            @Override
            public void onSuccess(String response) {
                // 在这里处理成功响应
                System.out.println("响应成功: " + response);

//                // 使用 Gson 将响应字符串转换为 JSON 对象
//                Gson gson = new Gson();
//                GameGuide gameGuideObject = gson.fromJson(response, GameGuide.class);
//
//                // 在这里处理解析后的 JSON 对象
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
        GameStar gameStar = gson.fromJson(data, GameStar.class);

        // 在这里处理解析后的 JSON 对象

        // 例如，获取游戏指南的标题和内容
        String gameName = gameStar.getStarName();
        String content = gameStar.getStarDescription();

        // 更新 UI
        // Display the data
        TextView nameTextView = findViewById(R.id.star_name_1);
        TextView contentTextView = findViewById(R.id.star_content_1);

        nameTextView.setText(gameName);
        contentTextView.setText(content);
    }

}
