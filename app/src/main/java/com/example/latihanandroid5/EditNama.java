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

public class EditNama extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_nama);
        final EditText et_nama = findViewById(R.id.et_nama);
        Button btn = findViewById(R.id.btn_);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (et_nama.getText().equals("")) {
                    Toast.makeText(getApplicationContext(), "NAMA KOSONG", Toast.LENGTH_SHORT).show();
                } else {
                    PrefsHelper.sharedInstance(getApplicationContext()).setNameDefault(et_nama.getText().toString());
                    startActivity(new Intent(EditNama.this, NamaClass.class));
                    finish();
                }
            }
        });
    }
}
