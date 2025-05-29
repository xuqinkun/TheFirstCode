package com.android.chapter06;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;

import com.android.main.R;
import com.android.util.ToastUtil;

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
        Button addBtn = (Button) findViewById(R.id.add_database);
        addBtn.setOnClickListener(v -> {
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("name", "The Da Vinci Code");
            values.put("author", "Dan Brown");
            values.put("pages", 454);
            values.put("price", 16.96);
            db.insert("Book", null, values); // 插入第一条数据
            values.clear();
            // 开始组装第二条数据
            values.put("name", "The Lost Symbol");
            values.put("author", "Dan Brown");
            values.put("pages", 510);
            values.put("price", 19.95);
            db.insert("Book", null, values); // 插入第二条数据
            ToastUtil.shortInfo(this, "Add data succeed");
        });

        Button updateBtn = (Button) findViewById(R.id.update_database);
        updateBtn.setOnClickListener(v -> {
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("price", 10.99);
            db.update("Book", values, "name = ?", new String[]{"The Da Vinci Code"});
        });
    }
}
