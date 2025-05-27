package com.xqk.broadcastbestpractice;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;


public class MainActivity extends BaseActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: " + getClass().getSimpleName());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button offlineBtn = (Button) findViewById(R.id.force_offline);
        offlineBtn.setOnClickListener(v -> {
            Log.d(TAG, "onCreate: send broadcast" + getClass().getSimpleName());
            Intent intent = new Intent("com.xqk.broadcastbestpractice.FORCE_OFFLINE");
            sendBroadcast(intent);
        });
    }
}