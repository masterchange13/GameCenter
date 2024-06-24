package com.mao.practise617;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        Toast.makeText(this, "正在跳转", Toast.LENGTH_SHORT).show();
        try {
            startActivity(intent);
        }catch (Exception e){
            Toast.makeText(this, "xxxxx", Toast.LENGTH_SHORT).show();
            System.out.println(e.toString());
        }
    }
}