package com.android.chapter09;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.main.R;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class NetworkActivity extends AppCompatActivity implements View.OnClickListener {
    public static final int CONNECT_TIMEOUT = 8000;
    public static final int READ_TIMEOUT = 8000;
    private TextView responseText;

    private static final String TAG = "NetworkActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_network);
        Button sendReq = (Button) findViewById(R.id.send_request);
        responseText = (TextView) findViewById(R.id.response_text);
        sendReq.setOnClickListener(this);
    }

    private void sendRequestWithHttpURLConnection() {
        new Thread(() -> {
            HttpURLConnection connection = null;
            BufferedReader reader = null;
            try {
                URL url = new URL("https://www.baidu.com");
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setConnectTimeout(CONNECT_TIMEOUT);
                connection.setReadTimeout(READ_TIMEOUT);
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                showResponse(response.toString());
            } catch (Exception e) {
                Log.d(TAG, "Send request error: ", e);
            } finally {
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (Exception e) {
                        Log.d(TAG, "Close reader error: ", e);
                    }
                }
                if (connection != null) {
                    connection.disconnect();
                }
            }
        }).start();

    }

    private void showResponse(String response) {
        runOnUiThread(() -> responseText.setText(response));
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.send_request) {
            sendRequestWithOkHttp();
        }
    }

    private void sendRequestWithOkHttp() {
        Log.d(TAG, "sendRequestWithOkHttp");
        new Thread(()->{
            try {
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder().url("https://www.baidu.com").build();
                Response response = client.newCall(request).execute();
                String data = response.body().string();
                showResponse(data);
            } catch (Exception e) {
                Log.d(TAG, "sendRequest Error: ", e);
            }
        }).start();
    }


}
