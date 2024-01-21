package com.example.practica_bbdd;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.practica_bbdd.Equipo;

public class EditarEquipoActivity extends AppCompatActivity {
    EditText txtNombre_equipo,txtNombre_ciudad,txtPuntos,txtFoto;
    Button btnGuarda;
    Equipo equipo;
    Boolean correcto = false;
    int id = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_equipos);
        txtNombre_equipo = findViewById(R.id.txtEquipo);
        txtNombre_ciudad = findViewById(R.id.txtCiudad);
        txtPuntos = findViewById(R.id.txtPuntos);
        txtFoto = findViewById(R.id.txtFoto);
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
        DbEquipos dbEquipos = new DbEquipos(EditarEquipoActivity.this);
        equipo = dbEquipos.verEquipo(id);
        if(equipo != null){
            txtNombre_equipo.setText(equipo.getNombre_equipo());
            txtNombre_ciudad.setText(equipo.getNombre_ciudad());
            txtPuntos.setText(String.valueOf(equipo.getPuntos()));


        }
        btnGuarda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                correcto =  dbEquipos.editarEquipo(id,txtNombre_equipo.getText().toString(),txtNombre_ciudad.getText().toString(),Integer.parseInt(txtPuntos.getText().toString()),txtFoto.getText().toString());
                if(correcto){
                    Toast.makeText(EditarEquipoActivity.this,"Modificado Correctamente",Toast.LENGTH_LONG).show();
                    verRegistroPartidos();
                }else{
                    Toast.makeText(EditarEquipoActivity.this,"Error al modificar",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    private void verRegistroPartidos(){
        Intent intent = new Intent(this,VerEquiposActivity.class);
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
