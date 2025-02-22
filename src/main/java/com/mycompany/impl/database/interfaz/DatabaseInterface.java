/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.taller.database.interfaz;

/**
 *
 * @author ef1008 R3
 */
public interface DatabaseInterface {
    void guardar(Object t);
    Object leer(String tipoObjeto, int id);
    void actualizar(Object t);
    void eliminar(String tipoObjeto,int id);
}
