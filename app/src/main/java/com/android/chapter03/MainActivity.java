package com.android.chapter03;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


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