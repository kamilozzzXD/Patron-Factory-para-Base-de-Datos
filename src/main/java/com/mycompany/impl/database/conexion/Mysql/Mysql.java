package com.mycompany.taller.database.conexion.Mysql;

import com.mycompany.taller.database.conexion.Conexion;
import com.mycompany.taller.database.conexion.H2.H2Database;
import com.mycompany.taller.database.tablas.Tabla;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Mysql implements Conexion {

    private static Mysql instancia;
    private Connection conexion;

    private Mysql() {
    }

    public static Mysql getInstancia() {
        if (instancia == null) {
            instancia = new Mysql();
        }
        return instancia;
    }

    @Override
    public void conectar() {
        try {
            if (conexion == null || conexion.isClosed()) {
                String url = "jdbc:mysql://bwum5gy4wwxpqdh2epz7-mysql.services.clever-cloud.com:3306/bwum5gy4wwxpqdh2epz7";
                String usuario = "u1nzn0msvsdnsnce";
                String contraseña = "3q8XMqZoSbYa7BH0b7gf";
                conexion = DriverManager.getConnection(url, usuario, contraseña);
                System.out.println("Conexión a MySQL exitosa.");
            }
        } catch (SQLException e) {
            System.err.println("Error al conectar a MySQL: " + e.getMessage());
        }
    }

    @Override
    public Connection getConexion() {
        return conexion;
    }

    public static void crearTablas() {
        Tabla.crearTablas("Mysql");
    }
}
