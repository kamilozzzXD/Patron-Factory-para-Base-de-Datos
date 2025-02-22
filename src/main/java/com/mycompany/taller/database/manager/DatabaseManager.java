/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.taller.database.manager;

import com.mycompany.taller.database.conexion.Conexion;
import com.mycompany.taller.database.conexion.factory.ConexionFactory;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author ef1008 R3
 */
public class DatabaseManager {
    public static Connection getConnection(String tipoBaseDatos) throws SQLException {
        Conexion conexion = ConexionFactory.getInstancia(tipoBaseDatos);
        if (conexion != null) {
            conexion.conectar();
            return conexion.getConexion(); 
        }
        
        throw new RuntimeException("No se pudo obtener una conexión para el tipo: " + tipoBaseDatos);
    }
}
