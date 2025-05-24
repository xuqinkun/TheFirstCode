package com.android.chapter03;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn = findViewById(R.id.button);
        EditText text = findViewById(R.id.edit_text);
        ImageView imageView = findViewById(R.id.image_view);
        ProgressBar progressBar = findViewById(R.id.progress_bar);
        btn.setOnClickListener(v -> {
            progressBar.setProgress(progressBar.getProgress() + 10);
        });
    }
}