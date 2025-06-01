package com.android.util;

import static com.android.util.Constant.CONNECT_TIMEOUT;
import static com.android.util.Constant.READ_TIMEOUT;

import android.util.Log;

import com.android.chapter09.HttpCallbackListener;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class HttpUtil {
    private static final String TAG = "HttpUtil";

    public static void sendHttpRequest(String address, final HttpCallbackListener listener) {
        HttpURLConnection connection = null;
        try {
            URL url = new URL(address);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(CONNECT_TIMEOUT);
            connection.setReadTimeout(READ_TIMEOUT);
            connection.setDoInput(true);
            connection.setDoOutput(true);
            InputStream in = connection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            if (listener != null) {
                listener.onFinish(response.toString());
            }
        }  catch (Exception e) {
            if (listener != null) {
                listener.onError(e);
            }
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }

    public static void sendHttpRequestWithOkHttp(String address, okhttp3.Callback callback) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(address).build();
        client.newCall(request).enqueue(callback);
    }
}
