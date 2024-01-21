package com.example.practica_bbdd;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ListaEquipoClasificacion extends RecyclerView.Adapter<ListaEquipoClasificacion.EquipoViewHolder> {

    ArrayList<Equipo> listaEquipos2;
    ArrayList<Equipo> listaEquiposOriginal2;
    public ListaEquipoClasificacion(ArrayList<Equipo>listaEquipos){
        this.listaEquipos2 = listaEquipos;
        listaEquiposOriginal2 = new ArrayList<Equipo>();
        listaEquiposOriginal2.addAll(listaEquipos);
    }

    @NonNull
    @Override
    public ListaEquipoClasificacion.EquipoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_clasificacion,null,false);
        return new ListaEquipoClasificacion.EquipoViewHolder((view));
    }

    @Override
    public void onBindViewHolder(@NonNull ListaEquipoClasificacion.EquipoViewHolder holder, int position) {
        holder.txtView1.setText(listaEquipos2.get(position).getNombre_equipo());
        holder.txtView2.setText(String.valueOf(listaEquipos2.get(position).getPuntos()));

    }

    @Override
    public int getItemCount() {
        return listaEquipos2.size();
    }

    public class EquipoViewHolder extends RecyclerView.ViewHolder {
        TextView txtView1,txtView2;
        public EquipoViewHolder(@NonNull View itemView) {
            super(itemView);
            txtView1 = itemView.findViewById(R.id.txtView1);
            txtView2 = itemView.findViewById(R.id.txtView2);

        }
    }
}

