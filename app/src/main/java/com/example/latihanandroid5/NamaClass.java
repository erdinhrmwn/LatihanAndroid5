package com.example.latihanandroid5;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.latihanandroid5.helpers.PrefsHelper;

public class NamaClass extends AppCompatActivity {
    TextView tv_nama, tv_edit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nama_layout);
        tv_nama = findViewById(R.id.tv_nama);
        tv_edit = findViewById(R.id.tv_edit);

        String data_nama = PrefsHelper.sharedInstance(getApplicationContext()).getNameDefault();
        tv_nama.setText(data_nama);
        tv_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(NamaClass.this, Login.class));
            }
        });
    }
}
