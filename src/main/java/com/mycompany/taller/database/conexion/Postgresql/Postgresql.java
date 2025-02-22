package com.mycompany.taller.database.conexion.Postgresql;

import com.mycompany.taller.database.conexion.Conexion;
import com.mycompany.taller.database.conexion.Mysql.Mysql;
import com.mycompany.taller.database.tablas.Tabla;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Postgresql implements Conexion {
    private static Postgresql instancia;
    private Connection conexion;

    private Postgresql() {
    }

    public static Postgresql getInstancia() {
        if (instancia == null) {
            instancia = new Postgresql();
        }
        return instancia;
    }

    @Override
    public void conectar() {
        try {
            if (conexion == null || conexion.isClosed()) {
                String url = "jdbc:postgresql://brg4lru007mdzleanou5-postgresql.services.clever-cloud.com:50013/brg4lru007mdzleanou5";
                
                String usuario = "ugehnxuiur0haojqy0bt";
                String contraseña = "rdkfhvicA37uSSmLyziYE8GxhIEEoQ";
                conexion = DriverManager.getConnection(url, usuario, contraseña);
                System.out.println("Conexión a PostgreSQL exitosa.");
            }
        } catch (SQLException e) {
            System.err.println("Error al conectar a PostgreSQL: " + e.getMessage());
        }
    }

    @Override
    public Connection getConexion() {
        return conexion;
    }
    
    public static void crearTablas() {
        Tabla.crearTablas("Postgresql");
    }
}
