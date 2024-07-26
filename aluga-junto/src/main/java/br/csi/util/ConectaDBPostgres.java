package br.csi.util;

import java.sql.*;

public class ConectaDBPostgres {

    public Connection getConexao(){

        try{
            Class.forName("org.postgresql.Driver");
            Connection conn = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/dbtrabpoow",
                    "postgres",
                    "1234");
            return conn;

        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}
