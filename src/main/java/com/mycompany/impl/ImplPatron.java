/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
//Brayan Camilo Herrera 160004711
package com.mycompany.impl;

import com.mycompany.taller.interfaz.Menu;
import java.sql.SQLException;
import javax.swing.SwingUtilities;

public class ImplPatron {

    public static void main(String[] args) throws SQLException {
        
        SwingUtilities.invokeLater(() -> {
            Menu menu = new Menu();
            menu.setVisible(true);
        });
    }

}
