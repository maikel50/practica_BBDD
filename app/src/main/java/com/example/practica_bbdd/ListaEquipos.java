package com.example.practica_bbdd;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ListaEquipos extends RecyclerView.Adapter<ListaEquipos.EquipoViewHolder> {

    ArrayList<Equipo> listaEquipos;
    ArrayList<Equipo> listaEquiposOriginal;
    public ListaEquipos(ArrayList<Equipo>listaEquipos){
        this.listaEquipos = listaEquipos;
        listaEquiposOriginal = new ArrayList<Equipo>();
        listaEquiposOriginal.addAll(listaEquipos);
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
    public void filtradoNombre(String txtNombreEquipo){
        int longitud = txtNombreEquipo.length();
        if(longitud== 0){
            listaEquipos.clear();
            listaEquipos.addAll(listaEquiposOriginal);
        }else{
            List<Equipo> collection = listaEquipos.stream().filter(i -> i.getNombre_equipo().toLowerCase().contains(txtNombreEquipo.toLowerCase())).collect(Collectors.toList());
            listaEquipos.clear();
            listaEquipos.addAll(collection);
        }
        notifyDataSetChanged();
    }
    public void filtradoCiudad(String txtBuscarCiudad){
        int longitud = txtBuscarCiudad.length();
        if(longitud== 0){
            listaEquipos.clear();
            listaEquipos.addAll(listaEquiposOriginal);
        }else{
            List<Equipo> collection = listaEquipos.stream().filter(i -> i.getNombre_ciudad().toLowerCase().contains(txtBuscarCiudad.toLowerCase())).collect(Collectors.toList());
            listaEquipos.clear();
            listaEquipos.addAll(collection);
        }
        notifyDataSetChanged();
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
