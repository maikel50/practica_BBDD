package com.example.practica_bbdd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class VerEquiposActivity extends AppCompatActivity {
    EditText txtNombre_equipo,txtNombre_ciudad,txtPuntos;
    Button btnGuarda;
    Equipo equipo;
    FloatingActionButton fabEditar,fabEliminar;
    int id = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_equipos);
        txtNombre_equipo = findViewById(R.id.txtEquipo);
        txtNombre_ciudad = findViewById(R.id.txtCiudad);
        txtPuntos = findViewById(R.id.txtPuntos);
        fabEditar = findViewById(R.id.fabEditar);
        fabEliminar = findViewById(R.id.fabEliminar);
        Toolbar toolbar = findViewById(R.id.toolbar5);
        setSupportActionBar(toolbar);

        btnGuarda = findViewById(R.id.btnInsertar);
        if(savedInstanceState == null){
            Bundle extras = getIntent().getExtras();
            if(extras == null){
                id = Integer.parseInt(null);
            }else{
                id = extras.getInt("id");
            }
        }else{
            id = (int) savedInstanceState.getSerializable("id");
        }
        DbEquipos dbEquipos = new DbEquipos(VerEquiposActivity.this);
        equipo = dbEquipos.verEquipo(id);
        if(equipo != null){
            txtNombre_equipo.setText(equipo.getNombre_equipo());
            txtNombre_ciudad.setText(equipo.getNombre_ciudad());
            txtPuntos.setText(String.valueOf(equipo.getPuntos()));

            btnGuarda.setVisibility(View.INVISIBLE);
        }
        fabEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VerEquiposActivity.this,EditarEquipoActivity.class);
                intent.putExtra("id",id);
                startActivity(intent);
            }
        });
        fabEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(dbEquipos .eliminarEquipo(id)){
                    reiniciar();
                }
            }
        });
    }
    private void reiniciar(){
        Intent intent = new Intent(this,MainActivity.class);
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