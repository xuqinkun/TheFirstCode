package com.android.chapter02;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;


public class BaseActivity extends AppCompatActivity {
    private static final String TAG = "BaseActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, getClass().getSimpleName());
        ActivityController.add(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityController.remove(this);
    }
}
