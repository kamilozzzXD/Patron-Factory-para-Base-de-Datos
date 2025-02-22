/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.taller.database.conexion.H2;

import com.mycompany.taller.database.conexion.Conexion;
import com.mycompany.taller.database.tablas.Tabla;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Juan
 */
public class H2Database implements Conexion {

    private static H2Database instancia; 
    private Connection conexion;

    private H2Database() {
    }

    public static H2Database getInstancia() {
        if (instancia == null) {
            instancia = new H2Database();
        }
        return instancia;
    }

    @Override
    public void conectar() {
        try {
            if (conexion == null || conexion.isClosed()) {
                String url = "jdbc:h2:~/tabla";
                conexion = DriverManager.getConnection(url, "sa", "");
                System.out.println("Conexión a H2 exitosa.");
            }
        } catch (SQLException e) {
            System.err.println("Error en conexión local: " + e.getMessage());
        }
    }
    @Override
    public Connection getConexion() {
        return conexion;
    }

    public static void crearTablas() {
        Tabla.crearTablas("H2");
    }

    

}
