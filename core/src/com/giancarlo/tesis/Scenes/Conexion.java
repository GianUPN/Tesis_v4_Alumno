package com.giancarlo.tesis.Scenes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


public class Conexion {
    static String url = "jdbc:sqlserver://localhost:1433;DatabaseName=Tesis";
    static final String USER = "sa";
    static final String PASS = "amadeus2010";
    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
    public ResultSet conectar(String sql, List<String> parametros){

        try{
            //STEP 2: Register JDBC driver
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            //STEP 3: Open a connection
            System.out.println("Conectando a BD...");
            conn = DriverManager.getConnection(url,USER,PASS);
            //STEP 4: Execute a query
            System.out.println("Creando sentencia SQL...");
            //stmt = conn.createStatement();
            stmt = conn.prepareStatement(sql);
            for(int i=0;i<parametros.size();i++){
                stmt.setString(i+1, parametros.get(i));
            }
            rs = stmt.executeQuery();
            //STEP 6: Clean-up environment

            //rs.close();
            //stmt.close();
            //conn.close();
        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{
            //finally block used to close resources
            if (stmt!=null) {
                //stmt.close();
            } // nothing we can do
            if (conn!=null) {
                //conn.close();
            } //end finally try
        }//end try
        System.out.println("FIN");
        return rs;
    }

    public boolean conectar2 (String sql, List<String>parametros){

        try{
            //STEP 2: Register JDBC driver
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            //STEP 3: Open a connection
            System.out.println("Conectando a BD...");
            conn = DriverManager.getConnection(url,USER,PASS);
            //STEP 4: Execute a query
            System.out.println("Creando sentencia SQL...");
            //stmt = conn.createStatement();
            stmt = conn.prepareStatement(sql);
            for(int i=0;i<parametros.size();i++){
                stmt.setString(i+1, parametros.get(i));
            }
            stmt.executeUpdate();
            //STEP 6: Clean-up environment

            //rs.close();
            //stmt.close();
            //conn.close();
        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
            return false;
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
            return false;
        }finally{
            //finally block used to close resources
            if (stmt!=null) {
                //stmt.close();
            } // nothing we can do
            if (conn!=null) {
                //conn.close();
            } //end finally try
        }//end try
        System.out.println("FIN");
        return true;
    }

    public void cerrarconexion(){
        try {
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
