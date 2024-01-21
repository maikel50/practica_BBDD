package com.example.practica_bbdd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.os.Debug;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InsertarActivity extends AppCompatActivity {
    EditText txtNombre_Equipo,txtNombreCiudad,txtFoto;
    EditText numberPuntos;
    Button btnInsertar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertar);

        Toolbar toolbar = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);

        txtNombre_Equipo = findViewById(R.id.txtEquipo);
        txtNombreCiudad = findViewById(R.id.txtCiudad);
        numberPuntos = findViewById(R.id.txtPuntos);
        txtFoto = findViewById(R.id.txtFoto);
        btnInsertar = findViewById(R.id.btnInsertar);

        btnInsertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DbEquipos dbEquipos = new DbEquipos(InsertarActivity.this);
                long id =  dbEquipos.insertaEquipo(txtNombre_Equipo.getText().toString(),txtNombreCiudad.getText().toString(), Integer.parseInt(numberPuntos.getText().toString()),txtFoto.getText().toString());
                if(id>0){
                    Toast.makeText(InsertarActivity.this,"Insertado Correctamente",Toast.LENGTH_LONG).show();
                    limpiar();
                }else{
                    Toast.makeText(InsertarActivity.this,"Error al insertar",Toast.LENGTH_LONG).show();
                    limpiar();
                }
            }
        });

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
    private void limpiar(){
        txtNombre_Equipo.setText("");
        txtNombreCiudad.setText("");
        numberPuntos.setText("");
        txtFoto.setText("");

    }
}