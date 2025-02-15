package com.mesttra.vacinas.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBanco {
    private static final String URL = "jdbc:mysql://localhost:3306/vacinacao";
    private static final String USUARIO = "root";
    private static final String SENHA = "root";

    public static Connection getConnection() throws SQLException{
       
        return DriverManager.getConnection(URL, USUARIO, SENHA);        
    }
}
