/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.taller.database.tablas;

import com.mycompany.taller.database.conexion.H2.H2Database;
import com.mycompany.taller.database.manager.DatabaseManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author ef1008 R3
 */
public class Tabla {
   
    public static void crearTablas(String baseDatos) {
        try {

            Connection cn = DatabaseManager.getConnection(baseDatos);
            String[] tablas = {
                "CREATE TABLE IF NOT EXISTS pais (id INT PRIMARY KEY, nombre VARCHAR(255) NOT NULL);",
                };

            for (String tabla : tablas) {
                try (PreparedStatement pst = cn.prepareStatement(tabla)) {
                    pst.execute();
                }
            }

            System.out.println("Tablas creadas exitosamente.");

        } catch (SQLException e) {
            System.err.println("Error al crear las tablas: " + e.getMessage());
        }
    }
}
