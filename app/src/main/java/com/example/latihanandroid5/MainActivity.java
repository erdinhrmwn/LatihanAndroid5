package com.example.latihanandroid5;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.example.latihanandroid5.helpers.BookHelper;
import com.example.latihanandroid5.helpers.PrefsHelper;


public class MainActivity extends AppCompatActivity {
    Cursor c;
    SimpleCursorAdapter sca;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView lv = findViewById(R.id.lv_list);
        FloatingActionButton fab = findViewById(R.id.fab);
        String username = PrefsHelper.sharedInstance(getApplicationContext()).getNameDefault();
        BookHelper helper = new BookHelper(this);

        SQLiteDatabase db = helper.getWritableDatabase();

        String[] datax = {"_id", "title", "author"};
        c = db.query("samplebooks", datax,
                null,
                null,
                null,
                null,
                null);
        sca = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_2, c,
                new String[]{"title", "author"},
                new int[]{android.R.id.text1, android.R.id.text2},
                CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        lv.setAdapter(sca);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Inserting.class));
            }
        });
        if (username.equals("admin")) {
            registerForContextMenu(lv);
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout: {
                PrefsHelper.sharedInstance(getApplicationContext()).setLogin(false);
                startActivity(new Intent(MainActivity.this, NamaClass.class));
                finish();
            }
            default: {
                //
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.edit_menu, menu);
    }

    // membuat method delete buku
    public void DeleteBooks(long id) {
        BookHelper helper = new BookHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();
        db.delete("samplebooks", "_id=?",
                new String[]{String.valueOf(id)});
        Toast.makeText(this, "Data berhasil dihapus", Toast.LENGTH_SHORT).show();

        Cursor x = db.query("samplebooks",
                new String[]{"_id", "title", "author"},
                null,
                null,
                null,
                null,
                null);
        sca.changeCursor(x);
        sca.notifyDataSetChanged();
    }

    public void UpdateBooks(long id) {
        BookHelper helper = new BookHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor e = db.query("samplebooks",
                new String[]{"title", "author"},
                "_id=?",
                new String[]{String.valueOf(id)},
                null,
                null,
                null);
        e.moveToFirst();
        Intent i = new Intent(MainActivity.this, Inserting.class);
        i.putExtra("_id", id);
        i.putExtra("title", e.getString(e.getColumnIndex("title")));
        i.putExtra("author", e.getString(e.getColumnIndex("author")));
        startActivity(i);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {
            case R.id.delete: {
                DeleteBooks(info.id);
            }
            break;
            case R.id.update: {
                UpdateBooks(info.id);
            }
            break;
            default: {
                //
            }
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.logout, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
