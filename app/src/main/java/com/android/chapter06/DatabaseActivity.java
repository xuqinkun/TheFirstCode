package com.android.chapter06;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;

import com.android.main.R;

public class DatabaseActivity extends AppCompatActivity {
    private static final String TAG = "DatabaseActivity";

    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_database);
        dbHelper = new DatabaseHelper(this, "Book.db", null, 2);
        Button createBtn = (Button) findViewById(R.id.create_database);
        createBtn.setOnClickListener(v -> {
            Log.d(TAG, "onCreate: clicked " + getClass().getSimpleName());
            dbHelper.getWritableDatabase();
        });
    }
}
