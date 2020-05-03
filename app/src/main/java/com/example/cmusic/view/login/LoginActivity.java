package com.example.cmusic.view.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cmusic.R;
import com.example.cmusic.view.main.MainActivity;

public class LoginActivity extends AppCompatActivity {

    private Button loginBtn;
    private EditText loginAccountEt;
    private EditText loginPasswordEt;
    private String testAccount;
    private String testPassword;
    private Handler handler;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        isLogin();
        intiView();
    }

    private void isLogin() {
        sharedPreferences = getSharedPreferences("isLogin",MODE_PRIVATE);
        Boolean isLogin = sharedPreferences.getBoolean("isLogin",false);
        if (isLogin) {
            turnToMain();
        }
    }

    private void intiView() {
        loginBtn = findViewById(R.id.login_login_btn);
        loginAccountEt = findViewById(R.id.login_account_et);
        loginPasswordEt = findViewById(R.id.login_password_et);
        getTestUserDataFormUrl();
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account = loginAccountEt.getText().toString();
                String password = loginPasswordEt.getText().toString();
                if (testAccount.equals(account)&&testPassword.equals(password)) {
                    SharedPreferences.Editor editor = getSharedPreferences("isLogin",MODE_PRIVATE).edit();
                    editor.putBoolean("isLogin",true);
                    editor.apply();
                   turnToMain();
                }else Toast.makeText(LoginActivity.this,"密码或账号输入错误，请重新输入",Toast.LENGTH_SHORT).show();
            }
        });
    }

private void turnToMain() {
    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
    startActivity(intent);
    handler = new Handler();
    handler.postDelayed(new Runnable() {
        @Override
        public void run() {
            finish();
        }
    },500);
}
    private void getTestUserDataFormUrl() {
        testAccount = "000000";
        testPassword = "000000";
    }
}
