package com.android.chapter10;

import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class DownloadTask extends AsyncTask<String, Integer, Integer> {
    private static final String TAG = "DownloadTask";

    public static final int SUCCESS = 0;

    public static final int FAILED = 1;

    public static final int PAUSED = 2;

    public static final int CANCELED = 3;

    private DownloadListener listener;

    private boolean isCanceled;

    private boolean isPaused;

    private int lastProgress;

    public DownloadTask(DownloadListener listener) {
        this.listener = listener;
    }

    @Override
    protected Integer doInBackground(String... params) {
        InputStream is = null;
        RandomAccessFile savedFile = null;
        File file = null;
        try {
            long downloadedLength = 0;
            String url = params[0];
            String filename = url.substring(url.lastIndexOf("/"));
            String path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath();
            file = new File(path + filename);
            if (file.exists()) {
                downloadedLength = file.length();
            }
            long contentLength = getContentLength(url);
            if (contentLength == 0) {
                return FAILED;
            } else if (contentLength == downloadedLength) {
                return SUCCESS;
            }
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .addHeader("RANGE", "bytes=" + downloadedLength + "-")
                    .url(url)
                    .build();
            Response response = client.newCall(request).execute();
            if (response != null) {
                is = response.body().byteStream();
                savedFile = new RandomAccessFile(file, "rw");
                savedFile.seek(downloadedLength);
                byte[] buffer = new byte[1024];
                int total = 0;
                int len;
                while ((len = is.read(buffer)) != -1) {
                    if (isCanceled) {
                        return CANCELED;
                    }
                    else if (isPaused) {
                        return PAUSED;
                    }
                    else {
                        total += len;
                        savedFile.write(buffer, 0, len);
                        int progress = (int) ((total+downloadedLength) / contentLength);
                        publishProgress(progress);
                    }
                }
                response.body().close();
                return SUCCESS;
            }
        } catch (Exception e) {
            Log.d(TAG, "Error downloading: ", e);
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
                if (savedFile != null) {
                    savedFile.close();
                }
                if (isCanceled && file != null) {
                    file.delete();
                }
            } catch (IOException e) {
                Log.d(TAG, "Error close: ", e);
            }
        }
        return FAILED;
    }

    private long getContentLength(String url) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        Response response = client.newCall(request).execute();
        if (response != null && response.isSuccessful()) {
            long contentLength = response.body().contentLength();
            response.body().close();
            return contentLength;
        }
        return 0;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        Integer progress = values[0];
        if (progress > lastProgress) {
            listener.onProgress(progress);
            lastProgress = progress;
        }
    }

    @Override
    protected void onPostExecute(Integer status) {
        switch (status) {
            case SUCCESS:
                listener.onSuccess();
                break;
            case FAILED:
                listener.onFailed();
                break;
            case PAUSED:
                listener.onPaused();
                break;
            case CANCELED:
                listener.onCanceled();
                break;
            default:
                break;
        }
    }

    public void pauseDownload() {
        isPaused = true;
    }

    public void cancelDownload() {
        isCanceled = true;
    }

}
