package com.example.practica_bbdd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class Portada extends AppCompatActivity {

    ImageView imgUEFA;
    Button btnClasificacion, btnVistaEquipos, btnInsertarEquipos, btnInsertarPartidos, btnConsultarPartidos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_portada);

        imgUEFA = findViewById(R.id.imgUEFA);
        btnClasificacion = findViewById(R.id.btnClasificacion);
        btnVistaEquipos = findViewById(R.id.btnVistaEquipos);
        btnInsertarEquipos = findViewById(R.id.btnInsertarEquipos);
        btnInsertarPartidos = findViewById(R.id.btnInsertarPartidos);
        btnConsultarPartidos = findViewById(R.id.btnConsultarPartidos);

    }

    public void verEquipos(View view) {
        Intent i = new Intent(Portada.this, MainActivity.class);
        startActivity(i);
    }

    public void insertarEquipos(View view) {
        Intent i = new Intent(Portada.this, InsertarActivity.class);
        startActivity(i);
    }

    public void insertarPartidos(View view) {
        Intent i = new Intent(Portada.this, InsertarPartidosActivity.class);
        startActivity(i);
    }

    public void consultarPartidos(View view) {
        Intent i = new Intent(Portada.this, ConsultaPartidosActivity.class);
        startActivity(i);
    }

    public void verClasificacion(View view) {
        Intent i = new Intent(Portada.this, Clasificacion.class);
        startActivity(i);
    }


}