package com.example.practica_bbdd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.ArrayList;

public class ClasificacionActivity extends AppCompatActivity {
    RecyclerView clasificacion;
    ArrayList<Equipo> listaArrayEquipos;
    ListaEquipoClasificacion adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clasificacion);
        Toolbar toolbar = findViewById(R.id.toolbar6);
        setSupportActionBar(toolbar);

        clasificacion = findViewById(R.id.listaClasificacion);
        clasificacion.setLayoutManager(new LinearLayoutManager(this));

        DbEquipos dbEquipos = new DbEquipos(ClasificacionActivity.this);
        listaArrayEquipos = new ArrayList<>();

        adapter = new ListaEquipoClasificacion(dbEquipos.mostrarEquipo2());
        clasificacion.setAdapter(adapter);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == R.id.item) {
            Intent i = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(i);
            return true;
        }else if(itemId == R.id.item1){
            Intent i = new Intent(getApplicationContext(), InsertarActivity.class);
            startActivity(i);
            return true;
        }else if(itemId == R.id.item2){
            Intent i = new Intent(getApplicationContext(), InsertarPartidosActivity.class);
            startActivity(i);
            return true;
        }else if(itemId == R.id.item3){
            Intent i = new Intent(getApplicationContext(), ConsultaPartidosActivity.class);
            startActivity(i);
            return true;
        }
        else if(itemId == R.id.item4){
            Intent i = new Intent(getApplicationContext(), ClasificacionActivity.class);
            startActivity(i);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}