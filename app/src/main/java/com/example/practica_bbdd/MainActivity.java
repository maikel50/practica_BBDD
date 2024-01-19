package com.example.practica_bbdd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    SQLiteHelper helper;
    SQLiteDatabase db;
    TextView text;
    EditText editNombre;
    EditText editCiudad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        text=findViewById(R.id.txtValor);
        //hacemos que funcione el scroll del cuadro de texto, primero hemos dicho en el xml que tiene scrollbars verticales
        text.setMovementMethod(new ScrollingMovementMethod());

        editNombre=findViewById(R.id.editNombre);
        editCiudad=findViewById(R.id.editCiudad);

        helper = new SQLiteHelper(this);
        db= helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nombre_equipo","SevillaFC");
        values.put("ciudad","Sevilla");
        values.put("puntos",3);
        values.put("foto","j");
        db.insert("equipo",null,values);
        db.delete("equipo","_id>1" ,null ) ;
        int foto1=R.drawable.celta ;
        int foto = getApplicationContext().getResources().getIdentifier("celta","drawable",getApplicationContext().getPackageName());
        insertar("Celta de Vigo","Vigo",6, R.drawable.celta);

        Cursor cursor = db.query("equipo", null, null, null, null, null, null);
        mostrarTabla(cursor);
        db.close();

    }
    /**
     * Método para insertar filas en la tabla de operas
     * @param nombre_equipo
     * @param ciudad
     * @param puntos
     */
    private void insertar(String nombre_equipo,String ciudad,int puntos,int foto) {
        ContentValues values = new ContentValues();
        values.put("nombre_equipo",nombre_equipo);
        values.put("ciudad",ciudad);
        values.put("puntos",puntos);
        values.put("foto",foto);
        db.insert("equipo",null,values);
    }

    private void mostrarTabla(Cursor c) {
        text.append("\n Tabla Equipo \n-----------");
        c.moveToFirst();

        int nfilas=c.getCount();
        int ncolumnas=c.getColumnCount();
        String fila="\n";

        for (int i = 0; i < nfilas; i++) {
            fila="\n";
            for(int j=0;j<ncolumnas;j++){
                fila=fila+c.getString(j)+" ";
            }
            text.append(fila);

            c.moveToNext();
        }
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

        if (itemId == R.id.item1) {
            Intent i = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(i);
            return true;
        } else if (itemId == R.id.item2) {
            Intent i = new Intent(getApplicationContext(), MainActivity2.class);
            startActivity(i);
            return true;
        }else if (itemId == R.id.item3) {
            Intent i = new Intent(getApplicationContext(), MainActivityConsulta.class);
            startActivity(i);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}