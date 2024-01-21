package com.example.practica_bbdd;

public class Partidos {
    private int id;
    private int jornada;
    private int fecha;
    private String equipo1;
    private String equipo2;
    private int ptoEquipo1;
    private int ptoEquipo2;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getJornada() {
        return jornada;
    }

    public void setJornada(int jornada) {
        this.jornada = jornada;
    }

    public int getFecha() {
        return fecha;
    }

    public void setFecha(int fecha) {
        this.fecha = fecha;
    }

    public String getEquipo1() {
        return equipo1;
    }

    public void setEquipo1(String equipo1) {
        this.equipo1 = equipo1;
    }

    public String getEquipo2() {
        return equipo2;
    }

    public void setEquipo2(String equipo2) {
        this.equipo2 = equipo2;
    }

    public int getPtoEquipo1() {
        return ptoEquipo1;
    }

    public void setPtoEquipo1(int ptoEquipo1) {
        this.ptoEquipo1 = ptoEquipo1;
    }

    public int getPtoEquipo2() {
        return ptoEquipo2;
    }

    public void setPtoEquipo2(int ptoEquipo2) {
        this.ptoEquipo2 = ptoEquipo2;
    }
}