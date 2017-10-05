package com.giancarlo.tesis.Scenes;


import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDao {
    public boolean login(String usuario,String contrasena){
        try {
            String sql;
            //En orden tienen que ir los parametros.
            sql = "SELECT * FROM Usuario WHERE usuario = ? AND contrasena = ? ";
            List<String> parametros = new ArrayList();
            parametros.add(usuario);
            parametros.add(contrasena);
            Conexion cn = new Conexion();
            ResultSet rs = cn.conectar(sql,parametros);
            while(rs.next()){
                //Retrieve by column name
                Usuario user = Usuario.getinstancia();
                user.setId(cn.rs.getInt("id"));
                user.setId_tipousuario(cn.rs.getInt("id_tipousuario"));
                user.setUsuario(cn.rs.getString("usuario"));
                cn.cerrarconexion();
                return true;
            }
            return false;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
}


