package com.xqk.helloworld;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class FirstActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_layout);
        Log.d(TAG, "onCreate: init");
        Button btn = findViewById(R.id.btn1);
        btn.setOnClickListener(v -> startActivity(new Intent("com.xqk.helloworld.ACTION_START")));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.add_item) {
            Toast.makeText(FirstActivity.this, "You clicked add item", Toast.LENGTH_SHORT).show();
        } else if (item.getItemId() == R.id.remove_item) {
            Toast.makeText(FirstActivity.this, "You clicked remove item", Toast.LENGTH_SHORT).show();
        }
        return true;
    }
}
