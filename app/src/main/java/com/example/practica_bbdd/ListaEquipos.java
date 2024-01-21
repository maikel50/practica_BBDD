package com.example.practica_bbdd;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ListaEquipos extends RecyclerView.Adapter<ListaEquipos.EquipoViewHolder> {

    ArrayList<Equipo> listaEquipos;
    public ListaEquipos(ArrayList<Equipo>listaEquipos){
        this.listaEquipos = listaEquipos;
    }
    @NonNull
    @Override
    public ListaEquipos.EquipoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_equipos,null,false);
        return new EquipoViewHolder((view));
    }

    @Override
    public void onBindViewHolder(@NonNull ListaEquipos.EquipoViewHolder holder, int position) {
        holder.viewNombre_Equipo.setText(listaEquipos.get(position).getNombre_equipo());
        holder.viewNombre_Ciudad.setText(listaEquipos.get(position).getNombre_ciudad());
        holder.viewPuntos.setText(String.valueOf(listaEquipos.get(position).getPuntos()));

    }

    @Override
    public int getItemCount() {
        return listaEquipos.size();
    }

    public  class EquipoViewHolder extends RecyclerView.ViewHolder {
        TextView viewNombre_Equipo,viewNombre_Ciudad,viewPuntos,viewFoto;
        public EquipoViewHolder(@NonNull View itemView) {
            super(itemView);
            viewNombre_Equipo = itemView.findViewById(R.id.viewNombre);
            viewNombre_Ciudad = itemView.findViewById(R.id.viewCiudad);
            viewPuntos = itemView.findViewById(R.id.viewPuntos);

        }
    }
}
