package com.android.broadcastreceiver;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;

import com.android.main.R;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.blank_layout);
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(v -> {
            Log.d(TAG, "onCreate: send broadcast");
            Intent intent = new Intent("android.intent.action.MY_BROADCAST");
            sendOrderedBroadcast(intent, null);
        });
    }

}
