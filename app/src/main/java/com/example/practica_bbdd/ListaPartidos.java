package com.example.practica_bbdd;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ListaPartidos extends RecyclerView.Adapter<ListaPartidos.PartidoViewHolder>{
    ArrayList<Partidos> listaPartidos;
    public ListaPartidos(ArrayList<Partidos>listaPartidos){
        this.listaPartidos = listaPartidos;
    }
    @NonNull
    @Override
    public ListaPartidos.PartidoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_partidos,null,false);
        return new ListaPartidos.PartidoViewHolder((view));
    }

    @Override
    public void onBindViewHolder(@NonNull ListaPartidos.PartidoViewHolder holder, int position) {
        holder.txtNJornada.setText("Nº Jornada : "+String.valueOf(listaPartidos.get(position).getJornada()));
        holder.txtFecha.setText("Fecha: "+String.valueOf(listaPartidos.get(position).getFecha()));
        holder.txtEquipo1.setText("Equipo 1 : "+listaPartidos.get(position).getEquipo1());
        holder.txtEquipo2.setText("Equipo 2 : "+listaPartidos.get(position).getEquipo2());
        holder.txtPuntos1.setText("Puntos Equipo 1 : "+String.valueOf(listaPartidos.get(position).getPtoEquipo1()));
        holder.txtPuntos2.setText( "Puntos Equipo 2 : "+ String.valueOf(listaPartidos.get(position).getPtoEquipo2()));
    }

    @Override
    public int getItemCount() {
        return listaPartidos.size();
    }

    public class PartidoViewHolder extends RecyclerView.ViewHolder {
        TextView txtNJornada, txtFecha, txtEquipo1, txtEquipo2, txtPuntos1, txtPuntos2;

        public PartidoViewHolder(View view) {
            super(view);
            txtNJornada = view.findViewById(R.id.txtNJornada);
            txtFecha = view.findViewById(R.id.txtFecha);
            txtEquipo1 = view.findViewById(R.id.txtEquipo1);
            txtEquipo2 = view.findViewById(R.id.txtEquipo2);
            txtPuntos1 = view.findViewById(R.id.txtPuntos1);
            txtPuntos2 = view.findViewById(R.id.txtPuntos2);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = view.getContext();
                    Intent intent = new Intent(context,VerPartidosActivity.class);
                    intent.putExtra("ID",listaPartidos.get(getAdapterPosition()).getId());
                    context.startActivity(intent);
                }
            });
        }
    }

}
