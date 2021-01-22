package com.example.laundryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LihatActivity extends AppCompatActivity {
    protected Cursor cursor;
    DataHelper dbHelper;
    Button btn1, btn2;
    TextView nomor, nama, alamat, berat, paket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat);

        dbHelper = new DataHelper(this);
        nomor = (TextView) findViewById(R.id.lihat_nomor);
        nama = (TextView) findViewById(R.id.lihat_nama);
        alamat = (TextView) findViewById(R.id.lihat_alamat);
        berat = (TextView) findViewById(R.id.lihat_berat);
        paket = (TextView) findViewById(R.id.lihat_paket);
        btn1 = (Button) findViewById(R.id.btn_lihat_back);
        btn2 = (Button) findViewById(R.id.btn_antar);

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM orderan WHERE nama = '" +
                getIntent().getStringExtra("nama") + "'", null);
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            cursor.moveToPosition(0);
            nomor.setText(cursor.getString(0).toString());
            nama.setText(cursor.getString(1).toString());
            alamat.setText(cursor.getString(2).toString());
            berat.setText(cursor.getString(3).toString());
            paket.setText(cursor.getString(4).toString());
        }
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String maps = alamat.getText().toString();
                String mapUrl = "https://www.google.com/maps/search/"+maps;
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(mapUrl)));
            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                finish();
            }
        });
    }
}