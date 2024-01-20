package com.example.practica_bbdd;



import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import androidx.annotation.Nullable;

public class SQLiteHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "bdEquipo.db";
    public static final String TABLE_NAME_EQUIPO = "equipo";
    public SQLiteHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(EstructuraBBDD.SQL_CREATE_ENTRIES);

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(EstructuraBBDD.SQL_DELETE_ENTRIES);
        onCreate(db);
    }
























    //--------------------------------------------------------
    public Cursor consultarEquiposPorNombre(String nombreEquipo) {
        SQLiteDatabase db = getReadableDatabase();

        String[] columns = {
                EstructuraBBDD.EstructuraPartidos.COLUMN_NAME_EQUIPO,
                EstructuraBBDD.EstructuraPartidos.COLUMN_NAME_CIUDAD,
                EstructuraBBDD.EstructuraPartidos.COLUMN_PUNTOS,
                EstructuraBBDD.EstructuraPartidos.COLUMN_FOTO_EQUIPO
        };

        String selection = EstructuraBBDD.EstructuraPartidos.COLUMN_NAME_EQUIPO + " LIKE ?";
        String[] selectionArgs = {"%" + nombreEquipo + "%"};

        String groupBy = null;
        String having = null;
        String orderBy = EstructuraBBDD.EstructuraPartidos.COLUMN_PUNTOS;

        return db.query(
                EstructuraBBDD.EstructuraPartidos.TABLE_NAME_EQUIPO,
                columns,
                selection,
                selectionArgs,
                groupBy,
                having,
                orderBy
        );
    }

    public Cursor consultarEquiposPorCiudad(String nombreCiudad) {
        SQLiteDatabase db = getReadableDatabase();

        String[] columns = {
                EstructuraBBDD.EstructuraPartidos.COLUMN_NAME_EQUIPO,
                EstructuraBBDD.EstructuraPartidos.COLUMN_NAME_CIUDAD,
                EstructuraBBDD.EstructuraPartidos.COLUMN_PUNTOS,
                EstructuraBBDD.EstructuraPartidos.COLUMN_FOTO_EQUIPO
        };

        String selection = EstructuraBBDD.EstructuraPartidos.COLUMN_NAME_CIUDAD + " LIKE ?";
        String[] selectionArgs = {"%" + nombreCiudad + "%"};

        String groupBy = null;
        String having = null;
        String orderBy = EstructuraBBDD.EstructuraPartidos.COLUMN_PUNTOS;

        return db.query(
                EstructuraBBDD.EstructuraPartidos.TABLE_NAME_EQUIPO,
                columns,
                selection,
                selectionArgs,
                groupBy,
                having,
                orderBy
        );
    }
}
