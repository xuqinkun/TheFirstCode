package com.android.chapter03;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class FruitDisplayActivity extends AppCompatActivity {
    private final List<Fruit> fruitList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_layout);
        initFruits();
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(new RecyclerAdapter(fruitList));
    }
        private void initFruits() {
            for (int i = 0; i < 2; i++) {
                Fruit apple = new Fruit("Apple", R.drawable.apple_pic);
                fruitList.add(apple);
                Fruit banana = new Fruit("Banana", R.drawable.banana_pic);
                fruitList.add(banana);
                Fruit orange = new Fruit("Orange", R.drawable.orange_pic);
                fruitList.add(orange);
                Fruit watermelon = new Fruit("Watermelon", R.drawable.watermelon_pic);
                fruitList.add(watermelon);
                Fruit pear = new Fruit("Pear", R.drawable.pear_pic);
                fruitList.add(pear);
                Fruit grape = new Fruit("Grape", R.drawable.grape_pic);
                fruitList.add(grape);
                Fruit pineapple = new Fruit("Pineapple", R.drawable.pineapple_pic);
                fruitList.add(pineapple);
                Fruit strawberry = new Fruit("Strawberry", R.drawable.strawberry_pic);
                fruitList.add(strawberry);
                Fruit cherry = new Fruit("Cherry", R.drawable.cherry_pic);
                fruitList.add(cherry);
                Fruit mango = new Fruit("Mango", R.drawable.mango_pic);
                fruitList.add(mango);
            }
    }
}
