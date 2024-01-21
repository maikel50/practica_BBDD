package com.example.practica_bbdd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DbPartidos extends SQLiteHelper {
    Context context;
    public DbPartidos( Context context) {
        super(context);
        this.context = context;
    }
    public long insertPartidos(int Jornada,int fecha, String equipo1, String equipo2, int ptsEquipo1, int ptsEquipo2){
        long id =0 ;
        try{
            SQLiteHelper dbHelper = new SQLiteHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("jornada",Jornada);
            values.put("fecha",fecha);
            values.put("equipo1",equipo1);
            values.put("equipo2",equipo2);
            values.put("ptoEquipo1",ptsEquipo1);
            values.put("ptoEquipo2",ptsEquipo2);

            id = db.insert(EstructuraBBDD.EstructuraPartidosEquipos.TABLE_NAME_PARTIDOS,null,values);
            Log.d("DbPartidos","Tabla: " + EstructuraBBDD.EstructuraPartidosEquipos.TABLE_NAME_PARTIDOS);
        }catch(Exception e){
            Log.e("DbPartidos","Error al insertar partido: " + e.toString());
        }
        return id;
    }
    public ArrayList<Partidos> mostrarPartidos(){
        SQLiteHelper dbHelper = new SQLiteHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ArrayList<Partidos> listaPartidos = new ArrayList<>();
        Partidos partidos = null;
        Cursor cursorPartidos = null;
        cursorPartidos = db.rawQuery("SELECT * FROM " +TABLE_NAME_PARTIDOS,null);
        if(cursorPartidos.moveToFirst()){
            do{
                partidos = new Partidos();
                partidos.setId(cursorPartidos.getInt(0));
                partidos.setJornada(cursorPartidos.getInt(1));
                partidos.setFecha(cursorPartidos.getInt(2));
                partidos.setEquipo1(cursorPartidos.getString(3));
                partidos.setEquipo2(cursorPartidos.getString(4));
                partidos.setPtoEquipo1(cursorPartidos.getInt(5));
                partidos.setPtoEquipo2(cursorPartidos.getInt(6));
                listaPartidos.add(partidos);
            }while (cursorPartidos.moveToNext());
        }
        cursorPartidos.close();
        return listaPartidos;
    }
}