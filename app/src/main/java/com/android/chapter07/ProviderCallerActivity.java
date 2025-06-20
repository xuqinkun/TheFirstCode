package com.android.chapter07;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;

import com.android.main.R;

public class ProviderCallerActivity extends AppCompatActivity {
    private static final String TAG = "ProviderCallerActivity";

    private String newId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_provider_caller);
        Button addData = (Button) findViewById(R.id.add_data);
        addData.setOnClickListener(v -> {
            Uri uri = Uri.parse("content://com.xqk.contentprovider.provider/book");
            ContentValues values = new ContentValues();
            values.put("name", "A Clash of Kings");
            values.put("author", "George Martin");
            values.put("pages", 1040);
            values.put("price", 22.85);
            Uri newUri = getContentResolver().insert(uri, values);
            newId = newUri.getPathSegments().get(1);
        });
        Button queryData = (Button) findViewById(R.id.query_data);
        queryData.setOnClickListener(v -> {
            Uri uri = Uri.parse("content://com.xqk.contentprovider.provider/book");
            Cursor cursor = getContentResolver().query(uri, null, null, null, null);
            if (cursor != null) {
                while (cursor.moveToNext()) {
                    String name = cursor.getString(cursor.getColumnIndex("name"));
                    String author = cursor.getString(cursor.getColumnIndex("author"));
                    int pages = cursor.getInt(cursor.getColumnIndex("pages"));
                    double price = cursor.getDouble(cursor.getColumnIndex("price"));
                    Log.d(TAG, "Book name is " + name);
                    Log.d(TAG, "Book author is " + author);
                    Log.d(TAG, "Book pages is " + pages);
                    Log.d(TAG, "Book price is " + price);
                }
                cursor.close();
            }
        });
        Button updateData = (Button) findViewById(R.id.update_data);
        updateData.setOnClickListener(v -> {
            Uri uri = Uri.parse("content://com.xqk.contentprovider.provider/book/" + newId);
            ContentValues values = new ContentValues();
            values.put("name", "A Storm of Swords");
            values.put("pages", 1216);
            values.put("price", 24.05);
            getContentResolver().update(uri, values, null, null);
        });
        Button deleteData = (Button) findViewById(R.id.delete_data);
        deleteData.setOnClickListener(v -> {
            Uri uri = Uri.parse("content://com.xqk.contentprovider.provider/book/" + newId);
            getContentResolver().delete(uri, null, null);
        });
    }
}
