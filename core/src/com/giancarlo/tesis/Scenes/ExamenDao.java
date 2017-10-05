package com.giancarlo.tesis.Scenes;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;



public class ExamenDao {
    public List<Pregunta> listar(){
        List<Pregunta> lista_examen= new ArrayList<Pregunta>();
        try {
            String sql;
            //En orden tienen que ir los parametros.
            sql = "SELECT * FROM Detalle_Examen_Pregunta D " +
                    "JOIN Examen E on D.id_examen = E.id WHERE "
                    + " E.estado = 1 AND E.fecha_ejecucion IS NULL";
            List<String> parametros = new ArrayList();
            Conexion cn = new Conexion();
            ResultSet rs = cn.conectar(sql,parametros);
            while(rs.next()){
                //Retrieve by column name
                int id = cn.rs.getInt("id_pregunta");
                List<Pregunta> preguntas = listar_ID(id+"");
                Pregunta pregunta = new Pregunta();
                pregunta = preguntas.get(0);
                List<Alternativa> alternativas = new ArrayList<Alternativa>();
                alternativas = listar_alternativa(id+"");
                pregunta.setLista_alternativas(alternativas);
                lista_examen.add(pregunta);
            }
            return lista_examen;
        } catch (Exception ex) {
            ex.printStackTrace();
            return lista_examen;
        }
    }

    public List<Pregunta> listar_ID(String id){
        List<Pregunta> lista_preguntas= new ArrayList<Pregunta>();
        try {
            String sql;
            //En orden tienen que ir los parametros.
            sql = "SELECT * FROM Pregunta WHERE id = ? AND"
                    + " estado = 1";
            List<String> parametros = new ArrayList();
            parametros.add(id);
            Conexion cn = new Conexion();
            ResultSet rs = cn.conectar(sql,parametros);
            while(rs.next()){
                //Retrieve by column name
                Pregunta pregunta = new Pregunta();
                pregunta.setId(cn.rs.getInt("id"));
                pregunta.setId_dificultad(cn.rs.getInt("id_dificultad"));
                pregunta.setId_tema(cn.rs.getInt("id_tema"));
                pregunta.setTexto_pregunta(cn.rs.getString("texto_pregunta"));
                pregunta.setPuntos(cn.rs.getInt("puntos"));
                pregunta.setEstado(cn.rs.getBoolean("estado"));
                lista_preguntas.add(pregunta);

            }
            return lista_preguntas;
        } catch (Exception ex) {
            ex.printStackTrace();
            return lista_preguntas;
        }
    }
    public List<Alternativa> listar_alternativa(String id_pregunta){
        List<Alternativa> lista_alternativas= new ArrayList<Alternativa>();
        try {
            String sql;
            //En orden tienen que ir los parametros.
            sql = "SELECT * FROM Alternativa WHERE id_pregunta = ? AND"
                    + " estado = 1 ORDER BY id";
            List<String> parametros = new ArrayList();
            parametros.add(id_pregunta);
            Conexion cn = new Conexion();
            ResultSet rs = cn.conectar(sql,parametros);
            while(rs.next()){
                //Retrieve by column name
                Alternativa alternativa = new Alternativa();
                alternativa.setId(cn.rs.getInt("id"));
                alternativa.setId_pregunta(cn.rs.getInt("id_pregunta"));
                alternativa.setRespuesta(cn.rs.getBoolean("respuesta"));
                alternativa.setTexto_alternativa(cn.rs.getString("texto_alternativa"));
                alternativa.setEstado(cn.rs.getBoolean("estado"));
                lista_alternativas.add(alternativa);
            }
            return lista_alternativas;
        } catch (Exception ex) {
            ex.printStackTrace();
            return lista_alternativas;
        }
    }
}
