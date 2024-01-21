package com.example.practica_bbdd;



import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import androidx.annotation.Nullable;

public class SQLiteHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "bdEquipo.db";
    protected static final String TABLE_NAME_EQUIPO = "equipo";
    public static final String TABLE_NAME_PARTIDOS = "partido";
    public SQLiteHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(EstructuraBBDD.SQL_CREATE_ENTRIES);
        db.execSQL(EstructuraBBDD.SQL_CREATE_ENTRIES_PARTIDOS);
    }
    @Override
    public void onConfigure(SQLiteDatabase db) {
        db.setForeignKeyConstraintsEnabled(true);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(EstructuraBBDD.SQL_DELETE_ENTRIES);
        db.execSQL(EstructuraBBDD.SQL_DELETE_ENTRIES2);
        onCreate(db);
    }
}
