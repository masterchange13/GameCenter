package com.mao.practise617;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpMainActivity extends AppCompatActivity  {

    private HandlerThread handlerThread;
    private Handler handler;
    private ImageView imageView;
    EditText n1,p1;
    private EditText username, password;
    private final int DOWNDLOAD = 1;
    private final int REGISTER = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http_main);

        imageView = findViewById(R.id.img);
        n1 = findViewById(R.id.n1);
        p1= findViewById(R.id.p1);

        handlerThread = new HandlerThread("http");
        handlerThread.start();

        handler = new HttpHandler(handlerThread.getLooper());//让handler消息运行子线程



    }


    // 图片
    public void down(View view){
        handler.sendEmptyMessage(DOWNDLOAD);
    }
    // 登录
    public void register(View view){
        handler.sendEmptyMessage(REGISTER);
    }
    public void toGetGoodsArray(View view){
        handler.sendEmptyMessage(3);
    }
    private class HttpHandler extends Handler {
        public HttpHandler(Looper looper) {
            super(looper);
        }
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case DOWNDLOAD:
                    downloadFile();
                    break;
                case REGISTER:
                    registerUser();
                    break;
                case 3:
                    toGetArray();
                    break;
                default:
                    break;
            }
        }
    }

    //post请求
    private void registerUser() {

        String registerUrl = "http://192.168.43.206:8083/tologin1";
        try {
            //1\访问的网络地址
            URL url = new URL(registerUrl);
            // 创建连接
            HttpURLConnection postConnection = (HttpURLConnection) url.openConnection();
            // 连接参数
            postConnection.setRequestMethod("POST");//post 请求
            postConnection.setConnectTimeout(1000*5);
            postConnection.setReadTimeout(1000*5);
            postConnection.setDoInput(true);//允许从服务端读取数据
            postConnection.setDoOutput(true);//允许写入

            postConnection.setRequestProperty("Content-type", "application/x-www-form-urlencoded");//以表单形式传递参数

            //发送数据
            String postParms = "username="+n1.getText().toString()+"&password="+p1.getText().toString();
            OutputStream outputStream = postConnection.getOutputStream();
            outputStream.write(postParms.getBytes());//把参数发送过去.
            outputStream.flush();
            //发送结束

            // 接受数据
            final StringBuffer buffer = new StringBuffer();
            int code = postConnection.getResponseCode();
            if (code == 200) {//成功
                InputStream inputStream = postConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String line = null;//一行一行的读取
                while ((line = bufferedReader.readLine()) != null) {
                    buffer.append(line);//把一行数据拼接到buffer里
                }
                // 接受结束  buffer
                String sign = buffer.toString();
                this.getJson(sign);
                if ("ok".equals(sign)){
                    Intent intent = new Intent(HttpMainActivity.this, ViewPagerActivity.class);
                    startActivity(intent);
                }else{
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //把从服务端获取的数据提示出来
                            Toast.makeText(HttpMainActivity.this, buffer.toString(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }

                //允许 在 子线程中 更新主线程 定义的组件

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void toGetArray() {

        String registerUrl = "http://192.168.43.206:8083/findGoods";
        try {
            //1\访问的网络地址
            URL url = new URL(registerUrl);
            // 创建连接
            HttpURLConnection postConnection = (HttpURLConnection) url.openConnection();
            // 连接参数
            postConnection.setRequestMethod("POST");//post 请求
            postConnection.setConnectTimeout(1000*5);
            postConnection.setReadTimeout(1000*5);
            postConnection.setDoInput(true);//允许从服务端读取数据
            postConnection.setDoOutput(true);//允许写入

            postConnection.setRequestProperty("Content-type", "application/x-www-form-urlencoded");//以表单形式传递参数

            //发送数据
            String postParms = "username="+n1.getText().toString()+"&password="+p1.getText().toString();
            OutputStream outputStream = postConnection.getOutputStream();
            outputStream.write(postParms.getBytes());//把参数发送过去.
            outputStream.flush();
            //发送结束

            // 接受数据
            final StringBuffer buffer = new StringBuffer();
            int code = postConnection.getResponseCode();
            if (code == 200) {//成功
                InputStream inputStream = postConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String line = null;//一行一行的读取
                while ((line = bufferedReader.readLine()) != null) {
                    buffer.append(line);//把一行数据拼接到buffer里
                }
                // 接受结束  buffer
                String sign = buffer.toString();
                this.getJsonArray(sign);
                if ("ok".equals(sign)){
                    Intent intent = new Intent(HttpMainActivity.this, ViewPagerActivity.class);
                    startActivity(intent);
                }else{
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //把从服务端获取的数据提示出来
                            Toast.makeText(HttpMainActivity.this, buffer.toString(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }

                //允许 在 子线程中 更新主线程 定义的组件

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //需要放到子线程中去,使用handlerThread.
    private void downloadFile() {
        String downloadUrl = "http://192.168.43.206:8099/web01/kk.jpg";
//        String savePath = "/sdcard/flowergirl.jpg";
//
//        File file = new File(savePath);
//        if (file.exists()) {
//            file.delete();//如果文件存在，先删掉
//        }
        BufferedInputStream bufferedInputStream = null;
        FileOutputStream outputStream = null;
        try {
            URL url = new URL(downloadUrl);
            try {
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.setConnectTimeout(1000*5);
                httpURLConnection.setReadTimeout(1000*5);
                httpURLConnection.setDoInput(true);
                httpURLConnection.connect();

                //代表请求成功
                if (httpURLConnection.getResponseCode() == 200) {
                    InputStream in = httpURLConnection.getInputStream();
                    bufferedInputStream = new BufferedInputStream(in);//文件输入流
//                    outputStream = new FileOutputStream(savePath);
//                    byte[] buffer = new byte[1024];
//                    int length = 0;
//                    while ((length = bufferedInputStream.read(buffer)) != -1) {
//                        outputStream.write(buffer, 0, length);//写入文件
//                    }
//                    outputStream.flush();//强制把数据写入磁盘
                    final Bitmap bitmap = BitmapFactory.decodeStream(bufferedInputStream);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            imageView.setImageBitmap(bitmap);
                        }
                    });
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufferedInputStream!= null) {
                    bufferedInputStream.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


    public void getJson(String data){
        try {
            JSONObject jsonObject = new JSONObject(data);
            System.out.println("id============="+jsonObject.get("id"));


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void getJsonArray(String data){
        try {
            JSONArray jsonArray = new JSONArray(data);

                // 遍历JSON数组
                for (int i = 0; i < jsonArray.length(); i++) {
                    // 获取每个元素，并将其转换为JSONObject
                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                    // 从JSONObject中获取数据
                    int goodsid = jsonObject.getInt("goodsid");
                    String goodsname = jsonObject.getString("goodsname");

                    // 处理获取到的数据，例如打印输出
                    System.out.println("goodsid: " + goodsid + ", goodsname: " + goodsname);
                }



        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void toZhuce(View view){



    }

}

