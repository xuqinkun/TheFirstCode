package com.xqk.broadcastbestpractice;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;


public class LoginActivity extends BaseActivity {
    private EditText accountEdit;
    private EditText passwordEdit;
    private Button loginBtn;

    private CheckBox rememberPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        accountEdit = (EditText) findViewById(R.id.account);
        passwordEdit = (EditText) findViewById(R.id.password);
        loginBtn = (Button) findViewById(R.id.login);
        rememberPass = (CheckBox) findViewById(R.id.remember_me);
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        boolean isRememberPass = pref.getBoolean("remember_password", false);
        if (isRememberPass) {
            String account = pref.getString("account", "");
            String password = pref.getString("password", "");
            accountEdit.setText(account);
            passwordEdit.setText(password);
            rememberPass.setChecked(true);
        }
        loginBtn.setOnClickListener(v -> {
            String account = accountEdit.getText().toString();
            String password = passwordEdit.getText().toString();
            if (account.equals("admin") && password.equals("1234")) {
                SharedPreferences.Editor editor = pref.edit();
                if (rememberPass.isChecked()) {
                    editor.putString("account", account);
                    editor.putString("password", password);
                    editor.putBoolean("remember_password", true);
                    editor.apply();
                } else {
                    editor.clear();
                }
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(LoginActivity.this, "account or password is invalid", Toast.LENGTH_SHORT).show();
            }
        });
    }
}