package com.example.laundryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class TambahActivity extends AppCompatActivity {

    protected Cursor cursor;
    DataHelper dbHelper;
    Button btn1, btn2;
    EditText nama, alamat, berat, paket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah);

        dbHelper = new DataHelper(this);
        nama = (EditText) findViewById(R.id.tambah_nama);
        alamat = (EditText) findViewById(R.id.tambah_alamat);
        berat = (EditText) findViewById(R.id.tambah_berat);
//        paket = (EditText) findViewById(R.id.tambah_paket);
        btn1 = (Button) findViewById(R.id.btn_tambah_save);
        btn2 = (Button) findViewById(R.id.btn_tambah_back);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                final Spinner paket = findViewById(R.id.tambah_paket);
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.execSQL("insert into orderan(nama, alamat, berat, paket) values('" +
                        nama.getText().toString() +"','" +
                        alamat.getText().toString()+"','"+
                        berat.getText().toString() +"','" +
                        paket.getSelectedItem() + "')");
                Toast.makeText(getApplicationContext(), "Data Berhasil Ditambahkan", Toast.LENGTH_LONG).show();
                finish();
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                finish();
            }
        });
    }
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//            // Inflate the main; this adds items to the action bar if it is present.
//                getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }
}