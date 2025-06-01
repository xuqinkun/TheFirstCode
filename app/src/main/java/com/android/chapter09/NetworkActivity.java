package com.android.chapter09;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.main.R;
import com.android.util.HttpUtil;
import com.android.util.ToastUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.StringReader;
import java.util.List;

import javax.xml.parsers.SAXParserFactory;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class NetworkActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView responseText;

    private static final String TAG = "NetworkActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_network);
        Button sendReq = (Button) findViewById(R.id.send_request);
        responseText = (TextView) findViewById(R.id.response_text);
        sendReq.setOnClickListener(this);
    }

    private void sendRequestWithHttpURLConnection() {
        new Thread(() -> {
            String url = "https://www.baidu.com";
            HttpUtil.sendHttpRequest(url, new HttpCallbackListener() {
                @Override
                public void onFinish(String response) {
                    showResponse(response);
                }

                @Override
                public void onError(Exception e) {
                    Log.e(TAG, "onError: " + e.getMessage());
                    runOnUiThread(() -> ToastUtil.showShortInfo(NetworkActivity.this, "Error request:" + url));
                }
            });
        }).start();

    }

    private void showResponse(String response) {
        runOnUiThread(() -> responseText.setText(response));
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.send_request) {
            // sendRequestWithHttpURLConnection();
            sendRequestWithOkHttp();
        }
    }

    private void sendRequestWithOkHttp() {
        Log.d(TAG, "sendRequestWithOkHttp");
        HttpUtil.sendHttpRequestWithOkHttp("http://192.168.31.246:88/get_data.json", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d(TAG, "sendRequest Error: ", e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String data = response.body().string();
                showResponse(data);
                parseJSONWithGson(data);
            }
        });
    }

    private void parseJSONWithGson(String data) {
        Gson gson = new Gson();
        List<App> appList = gson.fromJson(data, new TypeToken<List<App>>() {}.getType());
        for (App app : appList) {
            Log.d(TAG + ":gson", "id is " + app.getId());
            Log.d(TAG + ":gson", "name is " + app.getName());
            Log.d(TAG + ":gson", "version is " + app.getVersion());
        }
    }

    private void parseJSONWithJSONObject(String data) {
        try {
            JSONArray jsonArray = new JSONArray(data);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String id = jsonObject.getString("id");
                String name = jsonObject.getString("name");
                String version = jsonObject.getString("version");
                Log.d(TAG, "id is " + id);
                Log.d(TAG, "name is " + name);
                Log.d(TAG, "version is " + version);
            }
        } catch (Exception e) {
            Log.d(TAG, "Error parseJSONWithJSONObject: ", e);
        }
    }

    private void parseXMLWithSAX(String data) {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            XMLReader xmlReader = factory.newSAXParser().getXMLReader();
            XMLHandler handler = new XMLHandler();
            xmlReader.setContentHandler(handler);
            xmlReader.parse(new InputSource(new StringReader(data)));
        } catch (Exception e) {
            Log.d(TAG, "Error parseXMLWithSAX: ", e);
        }
    }

    private void parseXMLWithPull(String data) {
        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser xmlPullParser = factory.newPullParser();
            xmlPullParser.setInput(new StringReader(data));
            int eventType = xmlPullParser.getEventType();
            String id = "";
            String name = "";
            String version = "";
            while (eventType != XmlPullParser.END_DOCUMENT) {
                String nodeName = xmlPullParser.getName();
                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        if ("id" .equals(nodeName)) {
                            id = xmlPullParser.nextText();
                        } else if ("name" .equals(nodeName)) {
                            name = xmlPullParser.nextText();
                        } else if ("version" .equals(nodeName)) {
                            version = xmlPullParser.nextText();
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        if ("app" .equals(nodeName)) {
                            Log.d(TAG, "id is " + id);
                            Log.d(TAG, "name is " + name);
                            Log.d(TAG, "version is " + version);
                        }
                        break;
                    default:
                        break;
                }
                eventType = xmlPullParser.next();
            }
        } catch (Exception e) {
            Log.d(TAG, "parseXMLWithPull: ", e);
        }
    }


}
