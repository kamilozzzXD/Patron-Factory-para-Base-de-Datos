/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.taller.controller;
/**
 *
 * @author ef1008 R3
 */
public interface Controller {
    public void guardar(Object o);
    public Object leer(int id);
    public void eliminar(int id);
    public void modificar(Object o);
}
