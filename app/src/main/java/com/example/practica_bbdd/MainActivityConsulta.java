package com.example.practica_bbdd;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivityConsulta extends AppCompatActivity {

    SQLiteHelper helper;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_consulta);

        Toolbar toolbar = findViewById(R.id.toolbar3);
        setSupportActionBar(toolbar);



    }


}