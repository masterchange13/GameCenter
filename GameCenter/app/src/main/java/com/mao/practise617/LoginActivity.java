package com.mao.practise617;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mao.practise617.R;
import com.mao.practise617.ViewPagerActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class LoginActivity extends AppCompatActivity {

    private IpAddr ipAddr = new IpAddr();

    private HandlerThread handlerThread;
    private Handler handler; // 定义消息队列
    private final int LOING = 2;
    private Button loginButton;

    EditText username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);

        handlerThread = new HandlerThread("http");
        handlerThread.start();

        handler = new HttpHandler(handlerThread.getLooper());//让handler消息运行子线程

        loginButton = findViewById(R.id.login_button);
        // 创建一个消息对象
        Message msg = Message.obtain();
//        msg.what = LOGIN; // 设置消息类型为 LOGIN，可以是一个自定义的整型常量

        loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // 发送消息到 Handler
                msg.what = LOING;
//                handler.sendMessage(msg);
                login1();
            }
        });
    }
    // 监听类
    private class HttpHandler extends Handler {
        public HttpHandler(Looper looper) {
            super(looper);
        }
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case LOING:
                    login1();// 具体的操作
                    break;
                default:
                    break;
            }
        }
    }
    public void login01(View view){
        // 发消息
        handler.sendEmptyMessage(LOING);
    }

    //post请求
    private void login1() {
        String usernameStr = username.getText().toString();
        String passwordStr = password.getText().toString();

        new AsyncTask<String, Void, String>() {
            @Override
            protected String doInBackground(String... params) {
                String username = params[0];
                String password = params[1];
//                String registerUrl = "http://192.168.154.121:8083/login";
                String registerUrl = ipAddr.getIp() + "/login";
                StringBuffer buffer = new StringBuffer();
                try {
                    URL url = new URL(registerUrl);
                    HttpURLConnection postConnection = (HttpURLConnection) url.openConnection();
                    postConnection.setRequestMethod("POST");
                    postConnection.setConnectTimeout(1000 * 5);
                    postConnection.setReadTimeout(1000 * 5);
                    postConnection.setDoInput(true);
                    postConnection.setDoOutput(true);
                    postConnection.setRequestProperty("Content-type", "application/x-www-form-urlencoded");

                    String postParams = "username=" + username + "&password=" + password;
                    OutputStream outputStream = postConnection.getOutputStream();
                    outputStream.write(postParams.getBytes());
                    outputStream.flush();

                    int responseCode = postConnection.getResponseCode();
                    if (responseCode == HttpURLConnection.HTTP_OK) {
                        InputStream inputStream = postConnection.getInputStream();
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                        String line;
                        while ((line = bufferedReader.readLine()) != null) {
                            buffer.append(line);
                        }
                        inputStream.close();
                    } else {
                        // Handle error response
                        buffer.append("Error: ").append(responseCode);
                    }

                    postConnection.disconnect();
                } catch (IOException e) {
                    e.printStackTrace();
                    buffer.append("Error: ").append(e.getMessage());
                }

                return buffer.toString();
            }

            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);
                getJson(result);

                if ("".equals(result)) {
                    Intent intent = new Intent(LoginActivity.this, ViewPagerActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(LoginActivity.this, result, Toast.LENGTH_SHORT).show();
                }
            }
        }.execute(usernameStr, passwordStr);

        Intent intent = new Intent(LoginActivity.this, ViewPagerActivity.class);
        startActivity(intent);
    }

    public void getJson(String data){
        try {
            JSONObject jsonObject = new JSONObject(data);
            System.out.println("id============="+jsonObject.get("id"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

}