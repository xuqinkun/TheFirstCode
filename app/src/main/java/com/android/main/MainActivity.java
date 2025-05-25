package com.android.main;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.android.chapter04.RightFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_layout);
        // FragmentManager fragmentManager = getSupportFragmentManager();
        // FragmentTransaction transaction = fragmentManager.beginTransaction();
        // transaction.add(R.id.right_layout, new RightFragment()).commit();
    }
}
