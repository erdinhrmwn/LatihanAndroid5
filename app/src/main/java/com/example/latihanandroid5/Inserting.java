package com.example.latihanandroid5;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.latihanandroid5.helpers.BookHelper;

public class Inserting extends AppCompatActivity implements View.OnClickListener {
    EditText et_title, et_author;
    boolean update = false;
    long id;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.insert_layout);
        et_title = findViewById(R.id.et_title);
        et_author = findViewById(R.id.et_author);

        Button btn = findViewById(R.id.btn);
        btn.setOnClickListener(this);

        id = getIntent().getLongExtra("_id",0);
        if (id != 0){
            update = true;
            et_title.setText(getIntent().getStringExtra("title"));
            et_author.setText(getIntent().getStringExtra("author"));
        }
    }

    @Override
    public void onClick(View v) {
        BookHelper helper = new BookHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("title", et_title.getText().toString().trim());
        cv.put("author", et_author.getText().toString().trim());


        if (update){
            db.update("samplebooks", cv,"_id=?", new String[]{String.valueOf(id)});
        }else {
            db.insert("samplebooks", null, cv);
        }
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
