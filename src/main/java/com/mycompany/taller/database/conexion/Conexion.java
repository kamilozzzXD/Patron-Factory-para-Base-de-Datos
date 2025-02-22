/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.taller.database.conexion;

import java.sql.Connection;

/**
 *
 * @author ef1008 R3
 */
public interface Conexion {
    void conectar();
    Connection getConexion();
}
