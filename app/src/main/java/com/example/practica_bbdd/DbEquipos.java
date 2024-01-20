package com.example.practica_bbdd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.practica_bbdd.EstructuraBBDD;
import com.example.practica_bbdd.SQLiteHelper;

import java.util.ArrayList;

public class DbEquipos extends SQLiteHelper {
    Context context;
    public DbEquipos (Context context){
        super(context);
        this.context=context;
    }
    public long insertaContacto(String nombreEquipo, String nombreCiudad, int puntos, String foto) {
        long id = 0;
        try {
            SQLiteHelper dbHelper = new SQLiteHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("nombre_equipo", nombreEquipo);
            values.put("ciudad", nombreCiudad);
            values.put("puntos", puntos);

            id = db.insert(EstructuraBBDD.EstructuraPartidos.TABLE_NAME_EQUIPO, null, values);
            Log.d("DbEquipos", "Tabla: " + EstructuraBBDD.EstructuraPartidos.TABLE_NAME_EQUIPO);
        } catch (Exception e) {
            Log.e("DbEquipos", "Error al insertar: " + e.toString());
        }
        return id;
    }
    public ArrayList<Equipo>mostrarEquipo(){
        SQLiteHelper dbHelper = new SQLiteHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ArrayList<Equipo> listaEquipos = new ArrayList<>();
        Equipo equipo = null;
        Cursor cursorEquipo = null;
        cursorEquipo = db.rawQuery("SELECT * FROM " +TABLE_NAME_EQUIPO,null);
        if(cursorEquipo.moveToFirst()){
            do{
                equipo = new Equipo();
                equipo.setId(cursorEquipo.getInt(0));
                equipo.setNombre_equipo(cursorEquipo.getString(1));
                equipo.setNombre_ciudad(cursorEquipo.getString(2));
                equipo.setPuntos(cursorEquipo.getInt(3));

                listaEquipos.add(equipo);
            }while (cursorEquipo.moveToNext());
        }
        cursorEquipo.close();
        return listaEquipos;
    }
}
