package com.example.practica_bbdd;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Debug;
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

        txtNombre_Equipo = findViewById(R.id.txtEquipo);
        txtNombreCiudad = findViewById(R.id.txtCiudad);
        numberPuntos = findViewById(R.id.txtPuntos);
        txtFoto = findViewById(R.id.txtFoto);
        btnInsertar = findViewById(R.id.btnInsertar);

        btnInsertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DbEquipos dbEquipos = new DbEquipos(InsertarActivity.this);
               long id =  dbEquipos.insertaContacto(txtNombre_Equipo.getText().toString(),txtNombreCiudad.getText().toString(), Integer.parseInt(numberPuntos.getText().toString()),txtFoto.getText().toString());
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
    private void limpiar(){
        txtNombre_Equipo.setText("");
        txtNombreCiudad.setText("");
        numberPuntos.setText("");
        txtFoto.setText("");

    }
}