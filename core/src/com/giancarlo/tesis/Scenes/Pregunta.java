package com.giancarlo.tesis.Scenes;

import java.util.List;



public class Pregunta {
    private int id;
    private int id_dificultad;
    private int id_tema;
    private String texto_pregunta;
    private float puntos;
    private boolean estado;
    private List<Alternativa> lista_alternativas;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_dificultad() {
        return id_dificultad;
    }

    public void setId_dificultad(int id_dificultad) {
        this.id_dificultad = id_dificultad;
    }

    public int getId_tema() {
        return id_tema;
    }

    public void setId_tema(int id_tema) {
        this.id_tema = id_tema;
    }

    public String getTexto_pregunta() {
        return texto_pregunta;
    }

    public void setTexto_pregunta(String texto_pregunta) {
        this.texto_pregunta = texto_pregunta;
    }

    public float getPuntos() {
        return puntos;
    }

    public void setPuntos(float puntos) {
        this.puntos = puntos;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public List<Alternativa> getLista_alternativas() {
        return lista_alternativas;
    }

    public void setLista_alternativas(List<Alternativa> lista_alternativas) {
        this.lista_alternativas = lista_alternativas;
    }
}
