package com.android.chapter09;

public interface HttpCallbackListener {
    void onFinish(String response);
    void onError(Exception e);
}