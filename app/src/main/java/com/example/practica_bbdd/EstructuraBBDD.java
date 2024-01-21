package com.example.practica_bbdd;

import android.provider.BaseColumns;

public class EstructuraBBDD {
    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE IF NOT EXISTS " + EstructuraPartidos.TABLE_NAME_EQUIPO
                    + "(" +EstructuraPartidos._ID +" integer PRIMARY KEY, "
                    + EstructuraPartidos.COLUMN_NAME_EQUIPO +" text, "
                    + EstructuraPartidos.COLUMN_NAME_CIUDAD + " text, "
                    + EstructuraPartidos.COLUMN_PUNTOS + " integer, "
                    + EstructuraPartidos.COLUMN_FOTO_EQUIPO + " text);";

    public static final String SQL_CREATE_ENTRIES_PARTIDOS =
            "CREATE TABLE IF NOT EXISTS " + EstructuraPartidosEquipos.TABLE_NAME_PARTIDOS
                    + "(" + EstructuraPartidosEquipos._ID + " INTEGER PRIMARY KEY,"
                    + EstructuraPartidosEquipos.COLUMN_Jornada + " INTEGER, "
                    + EstructuraPartidosEquipos.COLUMN_Fecha + " INTEGER, "
                    + EstructuraPartidosEquipos.COLUMN_EQUIPO1 + " TEXT, "
                    + EstructuraPartidosEquipos.COLUMN_EQUIPO2 + " TEXT, "
                    + EstructuraPartidosEquipos.COLUMN_PUNTOS_EQUPO1 + " INTEGER, "
                    + EstructuraPartidosEquipos.COLUMN_PUNTOS_EQUPO2 + " INTEGER); "
            ;

    public static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS "+ EstructuraPartidos.TABLE_NAME_EQUIPO;
    public static final String SQL_DELETE_ENTRIES2 =
            "DROP TABLE IF EXISTS "+ EstructuraPartidosEquipos.TABLE_NAME_PARTIDOS;
    private EstructuraBBDD() {}
    public static class EstructuraPartidosEquipos implements BaseColumns {
        public static final String TABLE_NAME_PARTIDOS = "partido";
        public static final String COLUMN_Jornada = "jornada";
        public static final String COLUMN_Fecha = "fecha";
        public static final String COLUMN_EQUIPO1 = "equipo1";
        public static final String COLUMN_EQUIPO2 = "equipo2";
        public static final String COLUMN_PUNTOS_EQUPO1 = "ptoEquipo1";
        public static final String COLUMN_PUNTOS_EQUPO2 = "ptoEquipo2";

    }
    public static class EstructuraPartidos implements BaseColumns {
        public static final String TABLE_NAME_EQUIPO = "equipo";
        public static final String COLUMN_NAME_EQUIPO = "nombre_equipo";
        public static final String COLUMN_NAME_CIUDAD = "ciudad";
        public static final String COLUMN_PUNTOS = "puntos";
        public static final String COLUMN_FOTO_EQUIPO = "foto";

    }

}
