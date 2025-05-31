package com.android.chapter08;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.widget.Button;

import com.android.main.R;

import java.io.File;

public class NotificationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        Button sendNotice = (Button) findViewById(R.id.send_notice);
        sendNotice.setOnClickListener(v -> {
            switch (v.getId()) {
                case R.id.send_notice:
                    Intent intent = new Intent(this, WorkerActivity.class);
                    PendingIntent pi = PendingIntent.getActivity(this, 0, intent, 0);
                    NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                    Notification notification = new NotificationCompat.Builder(this).setContentTitle("Title")
                            .setContentText("This is content text")
                            .setWhen(System.currentTimeMillis())
                            .setSmallIcon(R.mipmap.ic_launcher)
                            .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                            .setContentIntent(pi)
                            // .setSound(Uri.fromFile(new File("/system/media/audio/ringtones/Candy.ogg")))
                            // .setAutoCancel(true) // 点击后自动取消通知
                            // .setVibrate(new long[]{0,1000,1000,1000}) // 震动
                            // .setLights(Color.GREEN, 1000, 1000) // 灯光
                            .setDefaults(NotificationCompat.DEFAULT_ALL)
                            .build();
                    manager.notify(1, notification);
                    break;
                default:
                    break;
            }
        });

    }
}