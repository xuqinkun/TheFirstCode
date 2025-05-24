package com.android.chapter03;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn = findViewById(R.id.button);
        EditText text = findViewById(R.id.edit_text);
        ImageView imageView = findViewById(R.id.image_view);
        btn.setOnClickListener(v -> {
            String string = text.getText().toString();
            Toast.makeText(MainActivity.this, string, Toast.LENGTH_SHORT).show();
            imageView.setImageResource(R.drawable.img_2);
        });
    }
}