package com.xqk.helloworld;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;


public class SecondActivity extends AppCompatActivity {
    private static final String TAG = "SecondActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_layout);
        Button btn = findViewById(R.id.btn2);
        btn.setOnClickListener(v -> {
            startActivity(new Intent(SecondActivity.this, FirstActivity.class));
        });
    }

//    @Override
//    public void onBackPressed() {
//        Log.d(TAG, "back");
//        Intent intent = new Intent();
//        intent.putExtra("data_return", "Hello FirstActivity");
//        setResult(RESULT_OK, intent);
//        finish();
//        Log.d(TAG, "finish");
//        super.onBackPressed();
//    }
}