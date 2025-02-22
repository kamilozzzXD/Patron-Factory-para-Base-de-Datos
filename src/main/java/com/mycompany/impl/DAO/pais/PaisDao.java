/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.taller.DAO.pais;

import com.mycompany.taller.database.BD;
import com.mycompany.taller.DAO.Dao;
import com.mycompany.taller.clases.pais.Pais;
import com.mycompany.taller.database.interfaz.DatabaseInterface;

/**
 *
 * @author ef1008 R3
 */
public class PaisDao implements Dao<Pais> {

    @Override
    public void insertar(String medio, Pais pais) {

        DatabaseInterface database = new BD(medio);
        database.guardar(pais);

    }

    @Override
    public void eliminar(String medio, int id) {

        DatabaseInterface database = new BD(medio);
        database.eliminar("Pais", id);

    }

    @Override
    public Pais leer(String medio, int id) {
        DatabaseInterface database = new BD(medio);
        return (Pais) database.leer("Pais", id);

    }

    @Override
    public void actualizar(String medio, Pais pais) {

        DatabaseInterface database = new BD(medio);
        database.actualizar(pais);

    }

}
