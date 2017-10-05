/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.giancarlo.tesis.Scenes;

/**
 *
 * @author gcalderon
 */
public class Usuario {

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the id_tipousuario
     */
    public int getId_tipousuario() {
        return id_tipousuario;
    }

    /**
     * @param id_tipousuario the id_tipousuario to set
     */
    public void setId_tipousuario(int id_tipousuario) {
        this.id_tipousuario = id_tipousuario;
    }

    /**
     * @return the usuario
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    /**
     * @return the contrasena
     */
    public String getContrasena() {
        return contrasena;
    }

    /**
     * @param contrasena the contrasena to set
     */
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    /**
     * @return the estado
     */
    public boolean isEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(boolean estado) {
        this.estado = estado;
    }
    private static Usuario instancia;
    private Usuario(){}
    public static Usuario getinstancia(){
        if(instancia==null){
            instancia = new Usuario();
        }
        return instancia;
    }
    
    private int id;
    private int id_tipousuario;
    private String usuario;
    private String contrasena;
    private boolean estado;
    
    
}
