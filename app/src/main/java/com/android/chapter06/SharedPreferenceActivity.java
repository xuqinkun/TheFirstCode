package com.android.chapter06;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;

import com.android.main.R;

public class SharedPreferenceActivity extends AppCompatActivity {
    private static final String TAG = "SharedPreference";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_shared);
        Button saveBtn = (Button) findViewById(R.id.save_data);
        saveBtn.setOnClickListener(v -> {
            Log.d(TAG, "onCreate: Save Data");
            SharedPreferences.Editor editor = getSharedPreferences("data", MODE_PRIVATE).edit();
            editor.putString("name", "Tom");
            editor.putInt("age", 28);
            editor.putBoolean("married", false);
            editor.apply();
        });
        Button restoreBtn = (Button) findViewById(R.id.restore_data);
        restoreBtn.setOnClickListener(v -> {
            SharedPreferences preferences = getSharedPreferences("data", MODE_PRIVATE);
            String name = preferences.getString("name", "");
            int age = preferences.getInt("age", 0);
            boolean married = preferences.getBoolean("married", false);
            Log.d(TAG, "name:" + name);
            Log.d(TAG, "age:" + age);
            Log.d(TAG, "married:" + married);
        });
    }
}