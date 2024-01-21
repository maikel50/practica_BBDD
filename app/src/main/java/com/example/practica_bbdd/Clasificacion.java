package com.example.practica_bbdd;

import static com.example.practica_bbdd.EstructuraBBDD.EstructuraPartidos.TABLE_NAME_EQUIPO;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import java.util.ArrayList;

public class Clasificacion extends AppCompatActivity {
    ListaEquipos adapter;

    RecyclerView listaEquipo;
    ArrayList<Equipo> listaArrayEquipos;
    Context context;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clasificacion);
        DbEquipos dbEquipos = new DbEquipos(Clasificacion.this);
        listaArrayEquipos = new ArrayList<>();
        DbPartidos dbPartidos = new DbPartidos(Clasificacion.this);
        adapter = new ListaEquipos(mostrarEquipo());
        listaEquipo.setAdapter(adapter);

    }

    public ArrayList<Equipo> mostrarEquipo(){

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