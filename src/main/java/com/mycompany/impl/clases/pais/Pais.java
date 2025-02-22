/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.mycompany.taller.clases.pais;

import java.io.Serializable;

public class Pais implements Serializable {
  private Integer id;
  private String nombre;
  
  public Pais(Integer id, String nombre){
      this.id=id;
      this.nombre=nombre;
  }
  
  public void setId(Integer id){
      this.id=id;
  }
  
  public Integer getId(){
      return id;
  }
  
  public void setNombre(String nombre){
      this.nombre=nombre;
  }
  
  public String getNombre(){
      return nombre;
  }

    @Override
    public String toString() {
        return "id=" + id + ", nombre=" + nombre;
    }
  
  
  
}
