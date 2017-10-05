package com.giancarlo.tesis.Scenes;


class Alternativa {
    private int id;
    private int id_pregunta;
    private boolean respuesta;
    private boolean estado;
    private String texto_alternativa;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_pregunta() {
        return id_pregunta;
    }

    public void setId_pregunta(int id_pregunta) {
        this.id_pregunta = id_pregunta;
    }

    public boolean isRespuesta() {
        return respuesta;
    }

    public void setRespuesta(boolean respuesta) {
        this.respuesta = respuesta;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String getTexto_alternativa() {
        return texto_alternativa;
    }

    public void setTexto_alternativa(String texto_alternativa) {
        this.texto_alternativa = texto_alternativa;
    }
}
