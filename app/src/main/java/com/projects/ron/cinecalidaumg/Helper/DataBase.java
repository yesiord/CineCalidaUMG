package com.projects.ron.cinecalidaumg.Helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Soporte on 29/05/2017.
 */

public class DataBase {

    Connection conn;
    public static final String DRIVER = "jdbc:jtds:sqlserver://";
    public static final String IP = "192.168.0.142/";
    public static final String DB = "DBCINECALIDAUMG";
    public static final String USER = "admin";
    public static final String PASS = "admin";
    private Statement stm;
    private ResultSet res;

    public DataBase(){
        try {
            conn = null;
            stm = null;
            res = null;
            Class.forName("net.sourceforge.jtds.jdbc.Driver").newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void conectar() throws SQLException {

        conn = DriverManager.getConnection(DRIVER + IP + DB, USER, PASS);
    }

    public void cerrarConexion() throws SQLException {
        if(res != null)
            res.close();
        if(stm != null)
            stm.close();
        if(conn != null)
            conn.close();
    }

    public ResultSet ejecutarConsulta(String sql) throws SQLException {
        stm = conn.createStatement();
        res = stm.executeQuery(sql);
        return res;
    }




}
