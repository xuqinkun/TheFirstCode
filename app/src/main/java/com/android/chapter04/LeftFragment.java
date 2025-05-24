package com.android.chapter04;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.android.main.MainActivity;
import com.android.main.R;


public class LeftFragment extends Fragment {
    private boolean isAnother = false;
    private MainActivity activity;

    private static final String TAG = "LeftFragment";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        activity = (MainActivity) getActivity();
        View view = inflater.inflate(R.layout.left_fragment, container, false);
        Button button = view.findViewById(R.id.button_replace);
        button.setOnClickListener(v -> {
            if (isAnother) {
                replaceFragment(new RightFragment());
                isAnother = false;
            } else {
                isAnother = true;
                replaceFragment(new AnotherRightFragment());
            }
        });
        return view;
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.right_layout, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
        Fragment target = fragmentManager.findFragmentById(R.id.right_layout);
        if (target != null) {
            Toast.makeText(getContext(), target.getClass().getSimpleName(), Toast.LENGTH_SHORT).show();
            if (target instanceof RightFragment) {
                RightFragment rightFragment = (RightFragment) target;
                Log.d(TAG, "replaceFragment: " + rightFragment.getData());
            }
        } else {
            Log.d(TAG, "replaceFragment: null");
        }
    }
}
