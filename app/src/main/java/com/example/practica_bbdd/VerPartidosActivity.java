package com.example.practica_bbdd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class VerPartidosActivity extends AppCompatActivity {
    EditText txtJornada2,txtDate2,txtPuntosEquipo1,txtPuntosEquipo2;
    Button btnModificarPartido;
    Spinner equipo1,equipo2;
    FloatingActionButton btnModificar,fabEliminarPartidos;
    Partidos partidos;

    int id=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_partidos);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        txtJornada2 = findViewById(R.id.txtJornada);
        txtDate2 = findViewById(R.id.txtDate);
        txtPuntosEquipo1 = findViewById(R.id.txtPuntosEquipo1);
        txtPuntosEquipo2 = findViewById(R.id.txtPuntosEquipo2);
        equipo1 = findViewById(R.id.listaEquipo1);
        equipo2 = findViewById(R.id.listaEquipo2);
        btnModificarPartido = findViewById(R.id.btnModificarPartido);
        btnModificarPartido.setVisibility(View.INVISIBLE);
        btnModificar = findViewById(R.id.btnModificar);
        fabEliminarPartidos = findViewById(R.id.fabEliminarPartido);

        if(savedInstanceState == null){
            Bundle extras = getIntent().getExtras();
            if(extras == null){
                id = Integer.parseInt(null);
            }else{
                id = extras.getInt("ID");
            }
        }else{
            id = (int)savedInstanceState.getSerializable("ID");
        }
        DbPartidos dbPartidos = new DbPartidos(VerPartidosActivity.this);
        partidos = dbPartidos.verPartidos(id);
        if(partidos !=null){
            List<Equipo> listaEquipos = llenarEquipos();
            ArrayAdapter<Equipo> arrayAdapter = new ArrayAdapter<>(getApplicationContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, listaEquipos);
            equipo1.setAdapter(arrayAdapter);
            equipo2.setAdapter(arrayAdapter);
            txtJornada2.setText(String.valueOf(partidos.getJornada()));
            txtDate2.setText(String.valueOf(partidos.getFecha()));

            txtPuntosEquipo1.setText(String.valueOf(partidos.getPtoEquipo1()));
            txtPuntosEquipo2.setText(String.valueOf(partidos.getPtoEquipo2()));
        }
        btnModificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VerPartidosActivity.this,EditarPartidos.class);
                intent.putExtra("_ID",id);
                startActivity(intent);
            }
        });
        fabEliminarPartidos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(dbPartidos .eliminarPartidos(id)){
                    reiniciar();
                }
            }
        });
    }
    private void reiniciar(){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
    private List<Equipo> llenarEquipos(){
        List<Equipo> listaEquipo = new ArrayList<>();
        DbEquipos dbEquipo = new DbEquipos(VerPartidosActivity.this);
        Cursor cursor = dbEquipo.mostrarEquipoDesplegable();
        if(cursor != null){
            if(cursor.moveToFirst()){
                do{
                    Equipo equipo = new Equipo();
                    equipo.setNombre_equipo(cursor.getString(cursor.getColumnIndexOrThrow("nombre_equipo")));
                    listaEquipo.add(equipo);
                }while(cursor.moveToNext());
            }
        }
        dbEquipo.close();
        return listaEquipo;
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