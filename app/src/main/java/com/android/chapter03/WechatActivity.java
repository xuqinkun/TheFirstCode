package com.android.chapter03;

import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.main.R;

import java.util.ArrayList;
import java.util.List;

public class WechatActivity extends AppCompatActivity {
    private List<Msg> msgList = new ArrayList<>();
    private EditText inputText;
    private Button send;
    private RecyclerView msgRecyclerView;
    private MsgAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wechat_layout);
        initMsgs();
        inputText = findViewById(R.id.input_text);
        send = findViewById(R.id.send);
        msgRecyclerView = findViewById(R.id.msg_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        msgRecyclerView.setLayoutManager(layoutManager);
        adapter = new MsgAdapter(msgList);
        msgRecyclerView.setAdapter(adapter);
        send.setOnClickListener(v -> {
            String content = inputText.getText().toString();
            if (!content.isEmpty()) {
                Msg msg = new Msg(content, Msg.TYPE_SENT);
                msgList.add(msg);
                adapter.notifyItemInserted(msgList.size() - 1);
                // 当有新消息时，刷新RecyclerView中的显示
                msgRecyclerView.scrollToPosition(msgList.size() - 1);
                inputText.setText("");
            }
        });
        inputText.setOnKeyListener((v, keyCode, event) -> {
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {
                String content = inputText.getText().toString();
                if (!content.isEmpty()) {
                    Msg msg = new Msg(content, Msg.TYPE_SENT);
                    msgList.add(msg);
                    adapter.notifyItemInserted(msgList.size() - 1);
                    // 当有新消息时，刷新RecyclerView中的显示
                    msgRecyclerView.scrollToPosition(msgList.size() - 1);
                    inputText.setText("");
                }
                return true;
            }
            return false;
        });
    }

    private void initMsgs() {
        msgList.add(new Msg("Hello guy.", Msg.TYPE_RECEIVED));
        msgList.add(new Msg("Hello Who is that?", Msg.TYPE_SENT));
        msgList.add(new Msg("This is Tom. Nice talking to you.", Msg.TYPE_RECEIVED));
    }
}
