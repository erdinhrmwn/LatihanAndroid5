package com.example.latihanandroid5;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.latihanandroid5.helpers.PrefsHelper;

public class Login extends AppCompatActivity {
    EditText et_user, et_pass;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        et_user = findViewById(R.id.et_user);
        et_pass = findViewById(R.id.et_pass);
        Button btn = findViewById(R.id.btn_login);
        boolean login = PrefsHelper.sharedInstance(getApplicationContext()).isLogin();

        if (login){
            startActivity(new Intent(Login.this, MainActivity.class));
            finish();
        }

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (et_user.getText().equals("") || et_pass.getText().equals("")) {
                    Toast.makeText(getApplicationContext(), "Username atau password kosong", Toast.LENGTH_SHORT).show();
                } else {
                    PrefsHelper.sharedInstance(getApplicationContext()).setNameDefault(et_user.getText().toString());
                    PrefsHelper.sharedInstance(getApplicationContext()).setLogin(true);
                    startActivity(new Intent(Login.this, MainActivity.class));
                    finish();
                }
            }
        });
    }
}
