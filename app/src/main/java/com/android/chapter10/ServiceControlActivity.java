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

    private FirstService.DownloadBinder downloadBinder;

    private final ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d(TAG, "onServiceConnected");
            downloadBinder = (FirstService.DownloadBinder) service;
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
        startService.setOnClickListener(v -> {
            startService(new Intent(this, FirstService.class));
        });
        stopService.setOnClickListener(v -> {
            stopService(new Intent(this, FirstService.class));
        });
        bindService.setOnClickListener(v -> {
            Intent intent = new Intent(this, FirstService.class);
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
    }
}
