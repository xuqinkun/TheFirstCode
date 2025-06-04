package com.android.chapter10;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;

import com.android.main.R;

public class ServiceControlActivity extends AppCompatActivity {
    private static final String TAG = "ServiceControlActivity";

    private ForegroundService.DownloadBinder downloadBinder;

    private final ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d(TAG, "onServiceConnected");
            downloadBinder = (ForegroundService.DownloadBinder) service;
            downloadBinder.startDownload();
            downloadBinder.getProgress();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d(TAG, "onServiceDisconnected: ");
        }
    };

    private boolean isBound;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_first_service);
        Button startService = (Button) findViewById(R.id.start_service);
        Button stopService = (Button) findViewById(R.id.stop_service);
        Button bindService = (Button) findViewById(R.id.bind_service);
        Button unbindService = (Button) findViewById(R.id.unbind_service);
        Button startIntentService = (Button) findViewById(R.id.start_intent_service);
        startService.setOnClickListener(v -> {
            startService(new Intent(this, ForegroundService.class));
        });
        stopService.setOnClickListener(v -> {
            stopService(new Intent(this, ForegroundService.class));
        });
        bindService.setOnClickListener(v -> {
            Intent intent = new Intent(this, ForegroundService.class);
            isBound = bindService(intent, connection, BIND_AUTO_CREATE);
            Log.d(TAG, "bindService: isBound=" + isBound);
        });
        unbindService.setOnClickListener(v -> {
            Log.d(TAG, "unbindService: isBound=" + isBound);
            if (isBound) {
                unbindService(connection);
                isBound = false;
            }
        });
        startIntentService.setOnClickListener(v -> {
            Log.d(TAG, "Main thread is " + Thread.currentThread().getId());
            Intent intent = new Intent(this, MyIntentService.class);
            startService(intent);
        });
    }
}
