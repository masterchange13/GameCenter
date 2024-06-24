package com.mao.practise617;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

public class LoginMineActivity extends AppCompatActivity {

    private TextInputEditText usernameView;
    private TextInputEditText passwordView;
    private Button buttonView;

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        usernameView = findViewById(R.id.username);
        passwordView = findViewById(R.id.password);
        buttonView = findViewById(R.id.login_button);

        buttonView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameView.getText().toString();
                String password = passwordView.getText().toString();
                if (username.equals("admin") && password.equals("123456")) {
                    intent = new Intent(LoginMineActivity.this, MainActivityAnimation.class);
                    startActivity(intent);
                } else {
                    // 可以在这里添加错误提示或其他逻辑
                    Toast.makeText(LoginMineActivity.this, "用户名或密码错误", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
