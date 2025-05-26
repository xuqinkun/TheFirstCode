package com.android.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.android.main.R;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private IntentFilter intentFilter;

    private LocalReceiver localReceiver;
    private LocalBroadcastManager broadcastManager;

    static class LocalReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(context, "received local broadcast", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.blank_layout);
        broadcastManager = LocalBroadcastManager.getInstance(this);

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(v -> {
            Log.d(TAG, "onCreate: send broadcast");
            Intent intent = new Intent("android.intent.action.LOCAL_BROADCAST");
            broadcastManager.sendBroadcast(intent);
        });
        intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.LOCAL_BROADCAST");
        localReceiver = new LocalReceiver();
        broadcastManager.registerReceiver(localReceiver, intentFilter);
    }

}
