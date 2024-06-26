package com.mao.practise617;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.google.gson.Gson;
import com.mao.practise617.GameRecommend.GameRecommendActivity;
import com.mao.practise617.GameStar.GameStar;
import com.mao.practise617.Network.Network;
import com.mao.practise617.Network.ResponseCallback;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameStarAddActivity extends AppCompatActivity {
    private EditText gameStarNameText, gameStarDescriptionText;
    private Button cancelButton, confirmButton;
    private Network network = new Network();

    private IpAddr ipAddr = new IpAddr();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_star_add);

        gameStarNameText = findViewById(R.id.game_star_name);
        gameStarDescriptionText = findViewById(R.id.game_star_description);

        cancelButton = findViewById(R.id.game_star_cancel_button);
        confirmButton = findViewById(R.id.game_star_confirm_button);

        initConfirmButton();
        initCancelButton();
    }

    public void initConfirmButton(){
        confirmButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                String gameStarName = String.valueOf(gameStarNameText.getText());
                String gameStarDescription = String.valueOf(gameStarDescriptionText.getText());

                if (gameStarName.equals("Name") && gameStarDescription.equals("简介")){
                    Toast.makeText(GameStarAddActivity.this, "请输入姓名和描述", Toast.LENGTH_SHORT).show();
                }else {
                    String url = ipAddr.getIpGameStar() + "/save";
//                    Map<String, String> data = new HashMap<>();
                    GameStar gameStar = new GameStar();
                    gameStar.setStarName(gameStarName);
                    gameStar.setStarDescription(gameStarDescription);
//                    data.put("name", gameStarName);
//                    data.put("description", gameStarDescription);

                    Gson gson = new Gson();
                    String json = gson.toJson(gameStar);

                    network.sendPostRequestWithThread(url, json, new ResponseCallback() {
                        @Override
                        public void onSuccess(String response) {
                            // 在这里处理成功响应
                            System.out.println("响应成功: " + response);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(GameStarAddActivity.this, "保存成功", Toast.LENGTH_SHORT).show();
                                    Intent resultIntent = new Intent();
                                    resultIntent.putExtra("refreshNeeded", true);
                                    setResult(RESULT_OK, resultIntent);
                                    finish();

                                }
                            });
                        }

                        @Override
                        public void onFailure(Exception e) {
                            // 在这里处理失败情况
                            System.out.println("请求失败: " + e.getMessage());
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(GameStarAddActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    });
                }
            }
        });
    }

    public void initCancelButton(){
        cancelButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}