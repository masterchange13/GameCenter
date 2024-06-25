package com.mao.practise617.GameRecommend;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mao.practise617.Game;
import com.mao.practise617.GameGuide.GameGuide;
import com.mao.practise617.IpAddr;
import com.mao.practise617.Network.Network;
import com.mao.practise617.Network.ResponseCallback;
import com.mao.practise617.R;
import com.mao.practise617.utils.SqlDateDeserializer;

import org.w3c.dom.Text;

import java.util.Date;

public class GameRecommendActivity extends AppCompatActivity {
    private Network network = new Network();
    private IpAddr ipAddr = new IpAddr();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_recommend);

        Intent intent = getIntent();
        int gameId = intent.getIntExtra("itemId", -1);

//        String url = ipAddr.getIpGameRecommend() + "/detail/" + gameId;
        String url = ipAddr.getIpGame() + "/detail/" + gameId;

        try {
            getData(url);
//            handleData(data);

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
        Game game = new Game();
        try {

        // 使用 Gson 将响应字符串转换为 JSON 对象
            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(Date.class, new SqlDateDeserializer())
                    .create();

            game = gson.fromJson(data, Game.class);
        }catch (Exception e){
            e.printStackTrace();
        }

        // 在这里处理解析后的 JSON 对象

        // 例如，获取游戏指南的标题和内容
        String gameName = game.getGameName();
        String content = game.getDescription();
        String gameType = game.getGameType();
        String developer = game.getDeveloper();
        String platform = game.getPlatform();

        // 更新 UI
        TextView titleTextView = findViewById(R.id.recommend_game_name);
        TextView contentTextView = findViewById(R.id.recommend_game_comment);
        TextView platformView = findViewById(R.id.platform);
        TextView developerView = findViewById(R.id.developer);
        TextView gameTypeView = findViewById(R.id.game_type);

        titleTextView.setText(gameName);
        contentTextView.setText(content);
        platformView.setText(platform);
        developerView.setText(developer);
        gameTypeView.setText(gameType);
    }
}