package com.mycompany.taller.controller.pais;

import com.mycompany.taller.DAO.pais.PaisDao;
import com.mycompany.taller.clases.pais.Pais;

public class PaisController {

    private final PaisDao paisDao = new PaisDao();

    public PaisController() {
        super();
    }

    public void guardar(String selector, Integer idPais, String nombre) {
        try {
            Pais pais = new Pais(idPais, nombre);
            paisDao.insertar(selector, pais);
        } catch (Exception e) {
            System.out.println("Error al guardar el país: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public Pais leer(String selector,int id) {
        try {
                Pais pais=paisDao.leer(selector,id);
                if (pais != null) {
                System.out.println("País encontrado: " + pais);
                return pais;
            
            }
        } catch (Exception e) {
            System.out.println("Error al leer el país: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public void eliminar(String selector,int id) {
        try {
            paisDao.eliminar(selector,id);
        } catch (Exception e) {
            System.out.println("Error al eliminar el país: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void modificar(String selector,Pais pais) {
        try {
            paisDao.actualizar(selector,pais);
            System.out.println("País modificado con éxito: " + pais);
        } catch (Exception e) {
            System.out.println("Error al modificar el país: " + e.getMessage());
            e.printStackTrace();
        }
    }

    
}
