package com.giancarlo.tesis.Scenes;


public class Puntaje {

    private int id_usuario;
    private int puntaje;
    private int correctas;
    private int incorrectas;
    private int dif_actual;

    private static Puntaje instancia;
    private Puntaje(){}
    public static Puntaje getinstancia(){
        if(instancia==null){
            instancia = new Puntaje();
        }
        return instancia;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }

    public int getCorrectas() {
        return correctas;
    }

    public void setCorrectas(int correctas) {
        this.correctas = correctas;
    }

    public int getIncorrectas() {
        return incorrectas;
    }

    public void setIncorrectas(int incorrectas) {
        this.incorrectas = incorrectas;
    }

    public int getDif_actual() {
        return dif_actual;
    }

    public void setDif_actual(int dif_actual) {
        this.dif_actual = dif_actual;
    }
}



