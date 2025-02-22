/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.taller.database;

import com.mycompany.taller.database.manager.DatabaseManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.mycompany.taller.clases.pais.Pais;
import com.mycompany.taller.database.interfaz.DatabaseInterface;

/**
 *
 * @author ef1008 R3
 */
public class BD implements DatabaseInterface {
    private String baseDatos;
    public BD(String baseDatos) {
        this.baseDatos = baseDatos;
    }
    
    @Override
    public void guardar(Object obj) {
        switch (obj.getClass().getSimpleName()) {
            case "Pais" -> {
                Pais pais = (Pais) obj;
                String checkSql = "SELECT COUNT(*) FROM pais WHERE id = ?";
                String insertSql = "INSERT INTO pais (id, nombre) VALUES (?, ?)";

                try (Connection cn = DatabaseManager.getConnection(baseDatos); PreparedStatement checkPst = cn.prepareStatement(checkSql); PreparedStatement insertPst = cn.prepareStatement(insertSql)) {

                    checkPst.setInt(1, pais.getId());
                    ResultSet rs = checkPst.executeQuery();
                    if (rs.next()) {
                        int count = rs.getInt(1);
                        if (count > 0) {
                            System.out.println("Error: El ID del país ya existe.");
                            return;
                        }
                    }
                    insertPst.setInt(1, pais.getId());
                    insertPst.setString(2, pais.getNombre());
                    insertPst.executeUpdate();
                    System.out.println("Datos del país insertados correctamente");

                } catch (SQLException e) {
                    throw new RuntimeException("Error al insertar el país", e);
                }
            }
            
            
            default -> {
                System.out.println("Tipo de objeto no reconocido.");
            }
        }
    }


    @Override
public Object leer(String tipoObjeto, int id) {
    switch (tipoObjeto) {
        case "Pais" -> {
            String sql = "SELECT * FROM pais WHERE id = ?";
        Pais pais = null;
        try (Connection cn = DatabaseManager.getConnection(baseDatos); PreparedStatement pst = cn.prepareStatement(sql)) {
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                pais = new Pais(rs.getInt("id"), rs.getString("nombre"));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al insertar el país", e);
        }
        return pais;
        }
        
        default -> {
            System.out.println("Tipo de objeto no reconocido para leer.");
            return null;
        }
    }
    
}

@Override
public void actualizar(Object obj) {
    switch (obj.getClass().getSimpleName()) {
        case "Pais" -> {
            Pais pais = (Pais) obj;
            String sql = "UPDATE pais SET nombre = ? WHERE id = ?";
        try (Connection cn = DatabaseManager.getConnection(baseDatos); PreparedStatement pst = cn.prepareStatement(sql)) {
            pst.setString(1, pais.getNombre());
            pst.setInt(2, pais.getId());
            int rowsAffected = pst.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("País actualizado correctamente");
            } else {
                System.out.println("No se encontró el país con el ID especificado");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al insertar el país", e);
        }
        }
        
        default -> {
            System.out.println("Tipo de objeto no reconocido para actualizar.");
        }
    }
}

@Override
public void eliminar(String tipoObjeto, int id) {
    switch (tipoObjeto) {
        case "Pais" -> {
            String sql = "DELETE FROM pais WHERE id = ?";
        try (Connection cn = DatabaseManager.getConnection(baseDatos); PreparedStatement pst = cn.prepareStatement(sql)) {
            pst.setInt(1, id);
            pst.executeUpdate();
            System.out.println("País eliminado correctamente");
        } catch (SQLException e) {
            throw new RuntimeException("Error al insertar el país", e);
        }
        }
        
        default -> {
            System.out.println("Tipo de objeto no reconocido para eliminar.");
        }
    }
}

}
