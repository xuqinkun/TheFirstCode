package com.xqk.broadcastbestpractice;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class BaseActivity extends AppCompatActivity {
    private static final String TAG = "BaseActivity";
    private ForceOfflineReceiver receiver;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCollector.addActivity(this);
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "onDestroy: " + getClass().getSimpleName());
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        Log.d(TAG, "onResume: " + getClass().getSimpleName());
        super.onResume();
        ActivityCollector.addActivity(this);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.xqk.broadcastbestpractice.FORCE_OFFLINE");
        receiver = new ForceOfflineReceiver();
        registerReceiver(receiver, intentFilter);
    }

    @Override
    protected void onPause() {
        Log.d(TAG, "onPause: " + getClass().getSimpleName());
        super.onPause();
        ActivityCollector.removeActivity(this);
        if (receiver != null) {
            unregisterReceiver(receiver);
            receiver = null;
        }
    }

    static class ForceOfflineReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d(TAG, "onReceive: " + getClass().getSimpleName());
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("warning");
            builder.setMessage("You are forced to be offline. Please try to login again.");
            builder.setCancelable(false);
            builder.setPositiveButton("OK", (dialog, which) -> {
                ActivityCollector.finishAll();
                Intent i = new Intent(context, LoginActivity.class);
                context.startActivity(i);
            });
            builder.show();
        }
    }
}
