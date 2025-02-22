/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.taller.database.conexion.factory;

import com.mycompany.taller.database.conexion.Conexion;
import com.mycompany.taller.database.conexion.H2.H2Database;
import com.mycompany.taller.database.conexion.Mysql.Mysql;
import com.mycompany.taller.database.conexion.Postgresql.Postgresql;

/**
 *
 * @author ef1008 R3
 */
public class ConexionFactory {
    public static Conexion getInstancia(String tipo) {
        if (tipo.equalsIgnoreCase("H2")) {
            return H2Database.getInstancia();  
        }else if(tipo.equalsIgnoreCase("Mysql")){
            return Mysql.getInstancia(); 
        }else if(tipo.equalsIgnoreCase("Postgresql")){
            return Postgresql.getInstancia(); 
        }
        return null;
    }
}
