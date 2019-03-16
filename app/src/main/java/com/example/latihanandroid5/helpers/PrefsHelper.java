package com.example.latihanandroid5.helpers;

import android.content.Context;
import android.content.SharedPreferences;

public class PrefsHelper {
    SharedPreferences prefs;
    Context ctx;

    private static PrefsHelper instance;

    public static PrefsHelper sharedInstance(Context context){
        if (instance == null){
            instance = new PrefsHelper(context);
        }
        return instance;
    }

    private PrefsHelper(Context ctx){
        this.ctx = ctx;
        this.prefs = ctx.getSharedPreferences("SAMPLE", Context.MODE_PRIVATE);
    }

    public void setNameDefault(String nama) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("NAMADEFAULT", nama);
        editor.apply();
    }

    public String getNameDefault(){
        return prefs.getString("NAMADEFAULT", "Master");
    }

    public void setLogin(boolean login){
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("login", login);
        editor.apply();
    }

    public boolean isLogin(){
        return prefs.getBoolean("login", false);
    }
}
