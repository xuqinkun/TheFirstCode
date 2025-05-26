package com.android.chapter02;

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
        Log.d(TAG, "onCreate");
        setContentView(R.layout.main_layout);
        Button startNormal = (Button) findViewById(R.id.start_normal);
        Button startDialog = (Button) findViewById(R.id.start_dialog);
        startNormal.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, NormalActivity.class));
        });
        startDialog.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, DialogActivity.class));
        });
        if (savedInstanceState != null) {
            Log.d(TAG, "onCreate: " + savedInstanceState.getString("key"));
        } else {
            Log.d(TAG, "onCreate: nothing");
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop");
        finish();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG, "onSaveInstanceState");
        outState.putString("key", "Don't forgive me");
    }
}