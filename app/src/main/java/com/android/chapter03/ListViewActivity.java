package com.android.chapter03;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.main.R;

public class ListViewActivity extends AppCompatActivity {

        private String[] data = { "Apple", "Banana", "Orange", "Watermelon",
                "Pear", "Grape", "Pineapple", "Strawberry", "Cherry", "Mango",
                "Apple", "Banana", "Orange", "Watermelon", "Pear", "Grape",
                "Pineapple", "Strawberry", "Cherry", "Mango" };
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.listview_layout);
            ArrayAdapter<String> adapter = new ArrayAdapter<>(
                    ListViewActivity.this, android.R.layout.simple_list_item_1, data);
            ListView listView = findViewById(R.id.list_view);
            listView.setAdapter(adapter);
    }
}
