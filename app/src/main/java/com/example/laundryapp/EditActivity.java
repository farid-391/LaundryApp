package com.example.laundryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditActivity extends AppCompatActivity {

    protected Cursor cursor;
    DataHelper dbHelper;
    Button btn1, btn2;
    EditText nomor, nama, alamat, berat, paket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        dbHelper = new DataHelper(this);
        nomor = (EditText) findViewById(R.id.edit_no);
        nama = (EditText) findViewById(R.id.edit_nama);
        alamat = (EditText) findViewById(R.id.edit_alamat);
        berat = (EditText) findViewById(R.id.edit_berat);
        paket = (EditText) findViewById(R.id.edit_paket);
        btn1 = (Button) findViewById(R.id.btn_edit_save);
        btn2 = (Button) findViewById(R.id.btn_edit_back);

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM orderan WHERE nama = '" +
                getIntent().getStringExtra("nama") + "'",null);
        cursor.moveToFirst();
        if (cursor.getCount()>0){
            cursor.moveToPosition(0);
            nomor.setText(cursor.getString(0).toString());
            nama.setText(cursor.getString(1).toString());
            alamat.setText(cursor.getString(2).toString());
            berat.setText(cursor.getString(3).toString());
            paket.setText(cursor.getString(4).toString());
        }
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.execSQL("update orderan set nama='"+
                        nama.getText().toString() +"', alamat='" +
                        alamat.getText().toString()+"', berat='"+
                        berat.getText().toString() +"', paket='" +
                        paket.getText().toString() + "' where id='" +
                        nomor.getText().toString()+"'");
                Toast.makeText(getApplicationContext(), "Data Berhasil Diupdate", Toast.LENGTH_LONG).show();
                MainActivity.ma.RefreshList();
                finish();
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                finish();
            }
        });
    }
}