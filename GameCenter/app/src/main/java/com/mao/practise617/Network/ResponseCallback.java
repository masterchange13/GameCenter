package com.mao.practise617.Network;

public interface ResponseCallback {
    void onSuccess(String response);
    void onFailure(Exception e);
}

