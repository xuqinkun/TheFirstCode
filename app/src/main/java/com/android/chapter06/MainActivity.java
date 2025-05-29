package com.android.chapter06;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;

import com.android.main.R;
import com.android.util.ToastUtil;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private EditText editText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.blank_layout);
        editText = (EditText) findViewById(R.id.edit);
        String saveData = load();
        if (!TextUtils.isEmpty(saveData)) {
            editText.setText(saveData);
            editText.setSelection(saveData.length());
            ToastUtil.shortInfo(this, "Load data succeed");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        String content = editText.getText().toString();
        save(content);
    }

    private void save(String content) {
        Log.d(TAG, "save: " + content);
        try (FileOutputStream out = openFileOutput("data", Context.MODE_PRIVATE);
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(out))) {
            bw.write(content);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String load() {
        FileInputStream in = null;
        BufferedReader reader = null;
        StringBuilder content = new StringBuilder();
        try {
            in = openFileInput("data");
            reader = new BufferedReader(new InputStreamReader(in));
            String line = "";
            while ((line = reader.readLine()) != null) {
                content.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return content.toString();
    }
}
