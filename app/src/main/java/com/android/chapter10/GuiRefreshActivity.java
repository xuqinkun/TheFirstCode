package com.android.chapter10;

import static com.android.util.Constant.UPDATE_TEXT;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import com.android.main.R;

public class GuiRefreshActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_gui_refresh);
        Button changeText = (Button) findViewById(R.id.change_text);
        TextView textView = (TextView) findViewById(R.id.text);
        Handler handler = new Handler() {
            public void handleMessage(Message msg) {
                if (msg.what == UPDATE_TEXT) {
                    textView.setText("Nice to meet you");
                }
            }
        };
        changeText.setOnClickListener(v -> {
            new Thread(() -> {
                Message message = new Message();
                message.what = UPDATE_TEXT;
                handler.sendMessage(message);
            }).start();
        });
    }
}
