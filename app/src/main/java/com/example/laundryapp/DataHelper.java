package com.example.laundryapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DataHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "laundry.db";
    private static int DATABASE_VERSION = 1;


    public DataHelper (Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table orderan(id integer primary key AUTOINCREMENT, nama text null, alamat text null, berat integer null, paket text null)";
        Log.d("Data", "onCreate"+ sql);
        db.execSQL(sql);
        sql = "INSERT INTO orderan(nama, alamat, berat, paket) VALUES ('Farid Afgar', 'Kos Gg Dahlia', '2', 'Cuci')";
        db.execSQL(sql);

//        String sql2 = "create table user(id integer primary key AUTOINCREMENT, nama text null, alamat text null, berat integer null, paket text null)";
//        Log.d("Data", "onCreate"+ sql);
//        db.execSQL(sql2);
//        sql = "INSERT INTO user(nama, alamat, berat, paket) VALUES ('Farid Afgar', 'Kos Gg Dahlia', '2', 'Cuci')";
//        db.execSQL(sql2);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
