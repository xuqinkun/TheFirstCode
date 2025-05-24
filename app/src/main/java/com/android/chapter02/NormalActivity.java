package com.android.chapter02;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.android.chapter03.R;

public class NormalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.normal_layout);
        Button btn = findViewById(R.id.btn_back);
        btn.setOnClickListener(v -> {
            startActivity(new Intent(NormalActivity.this, MainActivity.class));
        });
    }
}