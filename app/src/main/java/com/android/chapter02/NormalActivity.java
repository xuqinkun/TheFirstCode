package com.android.chapter02;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.android.main.R;

public class NormalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.normal_layout);
        Button btn = (Button) findViewById(R.id.btn_back);
        btn.setOnClickListener(v -> {
            startActivity(new Intent(NormalActivity.this, MainActivity.class));
        });
    }
}