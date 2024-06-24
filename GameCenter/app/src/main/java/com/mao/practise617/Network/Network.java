package com.mao.practise617.Network;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import java.io.IOException;

public class Network {
    private final OkHttpClient client = new OkHttpClient();

    public void sendGetRequestWithThread(String url, ResponseCallback callback) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Request request = new Request.Builder()
                        .url(url)
                        .build();

                try (Response response = client.newCall(request).execute()) {
                    if (!response.isSuccessful()) {
                        callback.onFailure(new IOException("Unexpected code " + response));
                        return;
                    }

                    // 返回响应
                    callback.onSuccess(response.body().string());
                } catch (Exception e) {
                    callback.onFailure(e);
                }
            }
        }).start();
    }
}
