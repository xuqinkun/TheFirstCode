package com.android.chapter06.litepal;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.android.main.R;
import com.android.util.ToastUtil;

import org.litepal.LitePal;

public class LitepalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_database);
        Button dropBtn = (Button) findViewById(R.id.drop_database);
        dropBtn.setOnClickListener(v -> {
            LitePal.deleteDatabase("Book");
            ToastUtil.shortInfo(this, "Drop db Book");
        });
        Button createBtn = (Button) findViewById(R.id.create_database);
        createBtn.setOnClickListener(v -> {
            LitePal.getDatabase();
            ToastUtil.shortInfo(this, "Create db Book");
        });
    }
}
