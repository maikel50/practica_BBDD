package com.example.practica_bbdd;

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

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.practica_bbdd.DbEquipos;
import com.example.practica_bbdd.DbPartidos;
import com.example.practica_bbdd.Equipo;
import com.example.practica_bbdd.Partidos;
import com.example.practica_bbdd.R;
import com.example.practica_bbdd.VerPartidosActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class EditarPartidos extends AppCompatActivity {
    EditText txtJornada2,txtDate2,txtPuntosEquipo1,txtPuntosEquipo2;
    Button btnModificarPartido;
    Spinner equipo1,equipo2;
    FloatingActionButton btnModificar;
    Partidos partidos;
    Boolean correcto = false;
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
        final DbPartidos dbPartidos = new DbPartidos(EditarPartidos.this);
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
        btnModificarPartido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Object selectedItem = equipo1.getSelectedItem();
                String equipoSeleccionado1 = selectedItem != null ? selectedItem.toString() : "";

                Object selectedItem2 = equipo2.getSelectedItem();
                String equipoSeleccionado2 = selectedItem2 != null ? selectedItem2.toString() : "";
                correcto = dbPartidos.editarPartido(id, Integer.parseInt(txtJornada2.getText().toString()), Integer.parseInt(txtDate2.getText().toString()),equipoSeleccionado1,equipoSeleccionado2,Integer.parseInt(txtPuntosEquipo1.getText().toString()), Integer.parseInt(txtPuntosEquipo2.getText().toString()) );
                if(correcto){
                    Toast.makeText(EditarPartidos.this,"Modificado Correctamente",Toast.LENGTH_LONG).show();
                    verRegistroPartidos();
                }else{
                    Toast.makeText(EditarPartidos.this,"Error al modificar",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private List<Equipo> llenarEquipos(){
        List<Equipo> listaEquipo = new ArrayList<>();
        DbEquipos dbEquipo = new DbEquipos(EditarPartidos.this);
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
    private void verRegistroPartidos(){
        Intent intent = new Intent(this, VerPartidosActivity.class);
        intent.putExtra("_ID",id);
        startActivity(intent);
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
        else if(itemId == R.id.item4){
            Intent i = new Intent(getApplicationContext(), ClasificacionActivity.class);
            startActivity(i);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

