package com.android.chapter06.litepal;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;

import com.android.main.R;
import com.android.util.ToastUtil;

import org.litepal.LitePal;
import org.litepal.crud.DataSupport;

import java.util.List;

public class LitePalActivity extends AppCompatActivity {
    private static final String TAG = "LitePalActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_database);
        Button dropBtn = (Button) findViewById(R.id.drop_database);
        dropBtn.setOnClickListener(v -> {
            boolean res = LitePal.deleteDatabase("Book");
            ToastUtil.shortInfo(this, "Drop db Book:" + res);
        });
        Button createBtn = (Button) findViewById(R.id.create_database);
        createBtn.setOnClickListener(v -> {
            LitePal.getDatabase();
            ToastUtil.shortInfo(this, "Create db Book");
        });
        Button addBtn = (Button) findViewById(R.id.add_database);
        addBtn.setOnClickListener(v -> {
            Book book = new Book();
            book.setName("The Da Vinci Code");
            book.setAuthor("Dan Brown");
            book.setPages(454);
            book.setPrice(16.96);
            book.setPress("Unknown");
            book.save();
            ToastUtil.shortInfo(this, "Add data succeed");
        });
        Button updateBtn = (Button) findViewById(R.id.update_database);
        updateBtn.setOnClickListener(v -> {
            Book book = new Book();
            book.setToDefault("pages");
            book.updateAll();
            ToastUtil.shortInfo(this, "Update data succeed");
        });
        Button deleteBtn = (Button) findViewById(R.id.delete_database);
        deleteBtn.setOnClickListener(v -> {
            DataSupport.deleteAll(Book.class, "price < ?", "15");
            ToastUtil.shortInfo(this, "Delete data succeed");
        });
        Button queryBtn = (Button) findViewById(R.id.query_database);
        queryBtn.setOnClickListener(v -> {
            List<Book> books = DataSupport.findAll(Book.class);
            for (Book book : books) {
                Log.d(TAG, "Book name is: " + book.getName());
                Log.d(TAG, "Book author is: " + book.getAuthor());
                Log.d(TAG, "Book pages is: " + book.getPages());
                Log.d(TAG, "Book price is: " + book.getPrice());
                Log.d(TAG, "Book press is: " + book.getPress());
            }
            ToastUtil.shortInfo(this, "Query data succeed");
        });
    }
}
