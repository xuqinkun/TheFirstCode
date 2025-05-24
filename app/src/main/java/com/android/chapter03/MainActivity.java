package com.android.chapter03;

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
            AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
            dialog.setTitle("This is a dialog");
            dialog.setMessage("FBI WARNING!");
            dialog.setCancelable(false);
            dialog.setPositiveButton("OK", (dialog1, which) -> {
                Log.d(TAG, "It's ok");
            });
            dialog.setNegativeButton("Cancel", (dialog1, which) -> {
                Log.d(TAG, "It's canceled");
            });

            dialog.show();
        });
    }
}