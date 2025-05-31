package com.xqk.contentprovider;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;


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
        Button addBtn = (Button) findViewById(R.id.add_data);
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
        });

        Button updateBtn = (Button) findViewById(R.id.update_database);
        updateBtn.setOnClickListener(v -> {
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("price", 10.99);
            db.update("Book", values, "name = ?", new String[]{"The Da Vinci Code"});
        });
        Button deleteBtn = (Button) findViewById(R.id.delete_database);
        deleteBtn.setOnClickListener(v -> {
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            db.delete("Book", "pages > ?", new String[]{"500"});
        });
        Button queryBtn = (Button) findViewById(R.id.query_database);
        queryBtn.setOnClickListener(v -> {
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            Cursor cursor = db.query("Book", null, null,
                    null, null, null, null);
            if (cursor.moveToFirst()) {
                do {
                    String name = cursor.getString(cursor.getColumnIndex("name"));
                    String author = cursor.getString(cursor.getColumnIndex("author"));
                    int pages = cursor.getInt(cursor.getColumnIndex("pages"));
                    double price = cursor.getDouble(cursor.getColumnIndex("price"));
                    Log.d(TAG, "book name is " + name);
                    Log.d(TAG, "book author is " + author);
                    Log.d(TAG, "book pages is " + pages);
                    Log.d(TAG, "book price is " + price);
                } while (cursor.moveToNext());
            }
            cursor.close();
        });
    }
}
