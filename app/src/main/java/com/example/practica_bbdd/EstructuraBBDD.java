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

    public static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS "+ EstructuraPartidos.TABLE_NAME_EQUIPO;
    private EstructuraBBDD() {}

    public static class EstructuraPartidos implements BaseColumns {
        public static final String TABLE_NAME_EQUIPO = "equipo";
        public static final String COLUMN_NAME_EQUIPO = "nombre_equipo";
        public static final String COLUMN_NAME_CIUDAD = "ciudad";
        public static final String COLUMN_PUNTOS = "puntos";
        public static final String COLUMN_FOTO_EQUIPO = "foto";

    }

}
