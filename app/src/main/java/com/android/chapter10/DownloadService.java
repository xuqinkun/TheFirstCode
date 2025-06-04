package com.android.chapter10;


import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Binder;
import android.os.Environment;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.NotificationCompat;

import com.android.main.R;
import com.android.util.ToastUtil;

import java.io.File;

public class DownloadService extends Service {
    private DownloadTask downloadTask;
    private String downloadUrl;
    private DownloadListener listener = new DownloadListener() {
        @Override
        public void onProgress(int progress) {
            getNotificationManager().notify(1, getNotification("Downloading...", progress));
        }

        @Override
        public void onSuccess() {
            downloadTask = null;
            stopForeground(true);
            getNotificationManager().notify(1, getNotification("Download Success", -1));
            ToastUtil.showShortInfo(DownloadService.this, "Download Success");
        }

        @Override
        public void onFailed() {
            downloadTask = null;
            stopForeground(true);
            getNotificationManager().notify(1, getNotification("Download Failed", -1));
            ToastUtil.showShortInfo(DownloadService.this, "Download Failed");
        }

        @Override
        public void onPaused() {
            downloadTask = null;
            ToastUtil.showShortInfo(DownloadService.this, "Paused");
        }

        @Override
        public void onCanceled() {
            downloadTask = null;
            stopForeground(true);
            ToastUtil.showShortInfo(DownloadService.this, "Canceled");
        }
    };

    private DownloadBinder binder = new DownloadBinder();


    class DownloadBinder extends Binder {

        public static final int NOTIFICATION_ID = 1;

        public void startDownload(String url) {
            if (downloadTask == null) {
                downloadUrl = url;
                downloadTask = new DownloadTask(listener);
                downloadTask.execute(downloadUrl);
                startForeground(NOTIFICATION_ID, getNotification("Downloading...", 0));
                ToastUtil.showShortInfo(DownloadService.this, "Downloading...");
            }
        }

        public void pauseDownload() {
            if (downloadTask != null) {
                downloadTask.pauseDownload();
            }
        }

        public void cancelDownload() {
            if (downloadTask != null) {
                downloadTask.cancelDownload();
            }
            if (downloadTask != null) {
                String filename = downloadUrl.substring(downloadUrl.lastIndexOf("/"));
                String path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath();
                File file = new File(path + filename);
                if (file.exists()) {
                    file.delete();
                }
                getNotificationManager().cancel(NOTIFICATION_ID);
                stopForeground(true);
                ToastUtil.showShortInfo(DownloadService.this, "Canceled");
            }
        }
    }

    private Notification getNotification(String title, int progress) {
        Intent intent = new Intent(this, DownloadActivity.class);
        PendingIntent pi = PendingIntent.getActivity(this, 0, intent, 0);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
        builder.setContentIntent(pi);
        builder.setContentTitle(title);
        if (progress >= 0) {
            builder.setContentText(progress + "%");
            builder.setProgress(100, progress, false);
        }
        return builder.build();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    public NotificationManager getNotificationManager() {
        return (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    }
}
