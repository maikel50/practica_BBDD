package com.example.practica_bbdd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class InsertarPartidosActivity extends AppCompatActivity {
    EditText txtJornada,txtDate,txtPuntosEquipo1,txtPuntosEquipo2;
    Button btnInsertarPartido;
    Spinner equipo1,equipo2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertar_partidos);
        txtJornada = findViewById(R.id.txtJornada);
        txtDate = findViewById(R.id.txtDate);
        equipo1 = findViewById(R.id.listaEquipo1);
        equipo2 = findViewById(R.id.listaEquipo2);
        txtPuntosEquipo1 = findViewById(R.id.txtPuntosEquipo1);
        txtPuntosEquipo2 = findViewById(R.id.txtPuntosEquipo2);
        btnInsertarPartido = findViewById(R.id.btnInsertarPartido);

        List<Equipo> listaEquipos = llenarEquipos();
        ArrayAdapter<Equipo> arrayAdapter = new ArrayAdapter<>(getApplicationContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, listaEquipos);
        equipo1.setAdapter(arrayAdapter);
        equipo2.setAdapter(arrayAdapter);

        Toolbar toolbar = findViewById(R.id.toolbar3);
        setSupportActionBar(toolbar);

        btnInsertarPartido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Object selectedItem = equipo1.getSelectedItem();
                String equipoSeleccionado1 = selectedItem != null ? selectedItem.toString() : "";

                Object selectedItem2 = equipo2.getSelectedItem();
                String equipoSeleccionado2 = selectedItem2 != null ? selectedItem2.toString() : "";

                DbPartidos dbPartidos = new DbPartidos(InsertarPartidosActivity.this);
                long id = dbPartidos.insertPartidos(Integer.parseInt(txtJornada.getText().toString()), Integer.parseInt(txtDate.getText().toString()),equipoSeleccionado1,equipoSeleccionado2,Integer.parseInt(txtPuntosEquipo1.getText().toString()), Integer.parseInt(txtPuntosEquipo2.getText().toString()) );
                if(id>0){
                    Toast.makeText(InsertarPartidosActivity.this,"Insertado Correctamente",Toast.LENGTH_LONG).show();
                    limpiar();
                }else{
                    Toast.makeText(InsertarPartidosActivity.this,"Error al insertar",Toast.LENGTH_LONG).show();
                    limpiar();
                }
            }
        });
    }
    private List<Equipo> llenarEquipos(){
        List<Equipo> listaEquipo = new ArrayList<>();
        DbEquipos dbEquipo = new DbEquipos(InsertarPartidosActivity.this);
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
    private void limpiar(){
        txtJornada.setText("");
        txtDate.setText("");

        txtPuntosEquipo1.setText("");
        txtPuntosEquipo2.setText("");
    }
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
        return super.onOptionsItemSelected(item);
    }
}