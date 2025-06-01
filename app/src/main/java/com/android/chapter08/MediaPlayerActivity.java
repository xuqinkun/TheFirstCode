package com.android.chapter08;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.android.main.R;
import com.android.util.ToastUtil;

import java.io.File;
import java.io.IOException;

public class MediaPlayerActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "MediaPlayerActivity";

    private MediaPlayer mediaPlayer = new MediaPlayer();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_media_player);
        Button play = (Button) findViewById(R.id.play);
        Button pause = (Button) findViewById(R.id.pause);
        Button stop = (Button) findViewById(R.id.stop);
        play.setOnClickListener(this);
        pause.setOnClickListener(this);
        stop.setOnClickListener(this);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        } else {
            initMediaPlayer();
        }
    }

    private void initMediaPlayer() {
        try {
            File dataDir = ContextCompat.getDataDir(this);
            File mp3 = new File(dataDir, "cache/music.mp3");
            Log.d(TAG, "initMediaPlayer: " + dataDir.getAbsolutePath());
            Log.d(TAG, "exits: " + mp3.exists() + " abs:" + mp3.getPath());
            mediaPlayer.setDataSource(mp3.getPath());
            mediaPlayer.prepare(); // 让MediaPlayer进入到准备状态
        } catch (IOException e) {
            Log.d(TAG, "initMediaPlayer: ", e);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                initMediaPlayer();
            } else {
                ToastUtil.showShortInfo(this, "You denied the permission");
                finish();
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.play:
                if (!mediaPlayer.isPlaying()) {
                    Log.d(TAG, "onClick: play");
                    mediaPlayer.start();
                }
                break;
            case R.id.pause:
                if (mediaPlayer.isPlaying()) {
                    Log.d(TAG, "onClick: pause");
                    mediaPlayer.pause();
                }
                break;
            case R.id.stop:
                Log.d(TAG, "onClick: stop");
                mediaPlayer.reset();
                initMediaPlayer();
                break;
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
        }
    }
}
