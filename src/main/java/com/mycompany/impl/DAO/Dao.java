/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.taller.DAO;

import java.util.List;
/**
 *
 * @author ef1008 R3
 */
public interface Dao<T> {
    void insertar(String medio,T t);
    T leer(String medio,int id);
    void actualizar(String medio,T t);
    void eliminar(String medio,int id);
}

