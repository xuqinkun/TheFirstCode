package com.android.chapter03;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.android.main.R;

public class TitleLayout extends LinearLayout {
    public TitleLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.title, this);
        Button backBtn = findViewById(R.id.title_back);
        Button editBtn = findViewById(R.id.title_edit);
        backBtn.setOnClickListener(v -> {
            ((Activity)getContext()).finish();
        });
        editBtn.setOnClickListener(v -> {
            Toast.makeText(getContext(), "Edit", Toast.LENGTH_SHORT).show();
        });
    }
}
