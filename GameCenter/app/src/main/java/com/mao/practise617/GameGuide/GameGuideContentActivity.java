package com.mao.practise617.GameGuide;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mao.practise617.Game;
import com.mao.practise617.GameRecommend.GameRecommendActivity;
import com.mao.practise617.IpAddr;
import com.mao.practise617.Network.Network;
import com.mao.practise617.Network.ResponseCallback;
import com.mao.practise617.R;
import com.mao.practise617.ViewPagerActivity;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class GameGuideContentActivity extends AppCompatActivity {

    private final OkHttpClient client = new OkHttpClient();
    private IpAddr ipAddr = new IpAddr();
    Network network = new Network();
    private ListView commentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
//        setContentView(R.layout.activity_game_guide_content);
        setContentView(R.layout.game_introduce);


        commentList = findViewById(R.id.comment_list);
        // Retrieve data from Intent
        Intent intent = getIntent();
        int id = intent.getIntExtra("itemId", -1);
//        int id = intent.getIntExtra("guideId", -1);
        String name = intent.getStringExtra("gameName");
        String content = intent.getStringExtra("guideContent");

        String url = ipAddr.getIpGameDetail() + "/detail/" + id;

        try {
            getData(url);
//            handleData(data);
            getComment(id);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void getData(String url) {
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

    public void getComment(int id){
        String url = ipAddr.getIpGameComment() + "/detail/" + id;

        network.sendGetRequestWithThread(url, new ResponseCallback() {
            @Override
            public void onSuccess(String response) {
                // 在这里处理成功响应
                System.out.println("响应成功: " + response);

                handleDataComment(response);
            }


            @Override
            public void onFailure(Exception e) {
                // 在这里处理失败情况
                System.out.println("请求失败: " + e.getMessage());
            }
        });
    }

    private void handleDataComment(String response) {
        Gson gson = new Gson();

        // 将JSON数组解析为GuideComment对象列表
        Type guideCommentListType = new TypeToken<List<GuideComment>>() {}.getType();
        List<GuideComment> guideCommentList = gson.fromJson(response, guideCommentListType);

        // 回归主线程
        List<Map<String, String>> res = new ArrayList<>();

        for (GuideComment guideComment : guideCommentList) {
            Map<String, String> event = new HashMap<>();
            event.put("commenter_name", guideComment.getCommenterName());
            event.put("comment_content", guideComment.getCommentContent());
            res.add(event);
        }


        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                // 更新视图
                SimpleAdapter eventsAdapter = new SimpleAdapter(GameGuideContentActivity.this, res, R.layout.comment_below_list,
                        new String[]{"commenter_name", "comment_content"},
                        new int[]{R.id.commenter_name, R.id.comment_content});

                commentList.setAdapter(eventsAdapter);
            }
        });

        // 更新视图
//        SimpleAdapter eventsAdapter = new SimpleAdapter(GameGuideContentActivity.this, res, R.layout.comment_below_list,
//                new String[]{"commenter_name", "comment_content"},
//                new int[]{R.id.commenter_name, R.id.comment_content});
//
//        commentList.setAdapter(eventsAdapter);

    }


}

