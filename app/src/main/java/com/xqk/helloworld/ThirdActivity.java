package com.xqk.helloworld;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

public class ThirdActivity extends BaseActivity {
    private static final String TAG = "ThirdActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "Task id is " + getTaskId());
        setContentView(R.layout.third_layout);
        Button btn = findViewById(R.id.btn3);
        btn.setOnClickListener(v -> {
            ActivityController.finishAll();
        });

    }
}