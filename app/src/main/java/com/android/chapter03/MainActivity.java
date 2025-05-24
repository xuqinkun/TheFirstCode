package com.android.chapter03;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.android.main.R;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn = findViewById(R.id.button);
        btn.setOnClickListener(v -> {
            ProgressDialog dialog = new ProgressDialog(MainActivity.this);
            dialog.setTitle("This is a ProgressDialog");
            dialog.setMessage("Loading...");
            dialog.setCancelable(true);
            dialog.show();
            new Thread(()->{
                try {
                    Thread.sleep(3000);
                    dialog.dismiss();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }).start();
        });
    }
}