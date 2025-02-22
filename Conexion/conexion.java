/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.taller.Conexion;

import com.mycompany.taller.Cargo;
import com.mycompany.taller.Departamento;
import com.mycompany.taller.Direccion;
import com.mycompany.taller.Empleado;
import com.mycompany.taller.Estudiante;
import com.mycompany.taller.Municipio;
import com.mycompany.taller.Pais;
import com.mycompany.taller.Persona;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Juan
 */
public class conexion {

    public static Connection conectar() {
        Connection cn = null;
        try {
            String url = "jdbc:h2:~/tabla";
            cn = DriverManager.getConnection(url, "sa", "");
        } catch (SQLException e) {
            System.err.println("Error en conexión local: " + e.getMessage());
        }
        return cn;
    }

    public static void crearTablas() {
        try {

            Connection cn = conexion.conectar();
            String[] tablas = {
                "CREATE TABLE IF NOT EXISTS Pais (id INT PRIMARY KEY, nombre VARCHAR(255) NOT NULL);",
                "CREATE TABLE IF NOT EXISTS Departamento (id INT PRIMARY KEY, nombre VARCHAR(255) NOT NULL, pais_id INT, FOREIGN KEY (pais_id) REFERENCES Pais(id) ON DELETE CASCADE);",
                "CREATE TABLE IF NOT EXISTS Municipio (id INT PRIMARY KEY, nombre VARCHAR(255) NOT NULL, departamento_id INT, FOREIGN KEY (departamento_id) REFERENCES Departamento(id)ON DELETE CASCADE);",
                "CREATE TABLE IF NOT EXISTS Direccion (id INT PRIMARY KEY, municipio_id INT, departamento_id INT, pais_id INT, calle VARCHAR(255), carrera VARCHAR(255), coordenada VARCHAR(255), descripcion VARCHAR(255), FOREIGN KEY (municipio_id) REFERENCES Municipio(id) ON DELETE CASCADE, FOREIGN KEY (departamento_id) REFERENCES Departamento(id) ON DELETE CASCADE, FOREIGN KEY (pais_id) REFERENCES Pais(id) ON DELETE CASCADE);",
                "CREATE TABLE IF NOT EXISTS Persona (id INT PRIMARY KEY, nombre VARCHAR(255) NOT NULL, apellido VARCHAR(255) NOT NULL, direccion_id INT, FOREIGN KEY (direccion_id) REFERENCES Direccion(id) ON DELETE CASCADE);",
                "CREATE TABLE IF NOT EXISTS Estudiante (id INT PRIMARY KEY, codigo VARCHAR(50) NOT NULL, programa VARCHAR(255), promedio DOUBLE, persona_id INT, FOREIGN KEY (persona_id) REFERENCES Persona(id) ON DELETE CASCADE);",
                "CREATE TABLE IF NOT EXISTS Cargo (id INT PRIMARY KEY, nombre VARCHAR(255) NOT NULL);",
                "CREATE TABLE IF NOT EXISTS Empleado (id INT PRIMARY KEY, cargo_id INT, persona_id INT, salario DOUBLE, FOREIGN KEY (persona_id) REFERENCES Persona(id), FOREIGN KEY (cargo_id) REFERENCES Cargo(id) ON DELETE CASCADE);"
            };

            for (String tabla : tablas) {
                try ( PreparedStatement pst = cn.prepareStatement(tabla)) {
                    pst.execute();
                }
            }

            System.out.println("Tablas creadas exitosamente.");

        } catch (SQLException e) {
            System.err.println("Error al crear las tablas: " + e.getMessage());
        }
    }

    public static void insertarDatosPais(Pais pais) {

        try {

            Connection cn = conexion.conectar();
            PreparedStatement pst = cn.prepareStatement("insert into pais values (?,?)");
            pst.setInt(1, pais.getId());
            pst.setString(2, pais.getNombre());
            pst.executeUpdate();
            System.out.println("Datos Pais insertados correctamente");

        } catch (SQLException ex) {
            System.err.print(ex.getMessage());
        }
    }

    public static void insertarDatosDepartamento(Departamento departamento) {
        try {
            Connection cn = conexion.conectar();
            PreparedStatement pst = cn.prepareStatement("insert into departamento values (?,?,?)");
            pst.setInt(1, departamento.getId());
            pst.setString(2, departamento.getNombre());
            pst.setInt(3, departamento.getPais().getId());
            pst.executeUpdate();
            System.out.println("Datos Departamento insertados correctamente");

        } catch (SQLException ex) {
            System.err.print(ex.getMessage());
        }
    }

    public static void insertarDatosMunicipio(Municipio municipio) {
        try {
            Connection cn = conexion.conectar();
            PreparedStatement pst = cn.prepareStatement("insert into municipio values (?,?,?)");
            pst.setInt(1, municipio.getId());
            pst.setString(2, municipio.getNombre());
            pst.setInt(3, municipio.getDepartamento().getId());
            pst.executeUpdate();
            System.out.println("Datos Municipio insertados correctamente");

        } catch (SQLException ex) {
            System.err.print(ex.getMessage());
        }
    }

    public static void insertarDatosDireccion(Direccion direccion) {
        try {
            Connection cn = conexion.conectar();
            PreparedStatement pst = cn.prepareStatement("insert into direccion values (?,?,?,?,?,?,?,?)");
            pst.setInt(1, direccion.getId());
            pst.setInt(2, direccion.getMunicipio().getId());
            pst.setInt(3, direccion.getDepartamento().getId());
            pst.setInt(4, direccion.getPais().getId());
            pst.setString(5, direccion.getCalle().trim());
            pst.setString(6, direccion.getCarrera().trim());
            pst.setString(7, direccion.getCoordenada().trim());
            pst.setString(8, direccion.getDescripcion().trim());
            pst.executeUpdate();
            System.out.println("Datos Direccion insertados correctamente");

        } catch (SQLException ex) {
            System.err.print(ex.getMessage());
        }
    }

    public static void insertarDatosPersona(Persona persona) {

        try {
            Connection cn = conexion.conectar();
            PreparedStatement pst = cn.prepareStatement("insert into persona values (?,?,?,?)");

            pst.setInt(1, persona.getId());
            pst.setString(2, persona.getNombre().trim());
            pst.setString(3, persona.getApellido().trim());
            pst.setInt(4, persona.getDireccion().getId());
            pst.executeUpdate();
            System.out.println("Datos Persona insertados correctamente");

        } catch (SQLException ex) {
            System.err.print(ex.getMessage());
        }
    }

    public static void insertarDatosEstudiante(Estudiante estudiante) {
        try {
            Connection cn = conexion.conectar();
            PreparedStatement pst = cn.prepareStatement("insert into estudiante values (?,?,?,?,?)");
            pst.setInt(1, estudiante.getIdEstudiante());
            pst.setString(2, estudiante.getCodigo());
            pst.setString(3, estudiante.getPrograma());
            pst.setDouble(4, estudiante.getPromedio());
            pst.setInt(5, estudiante.getId());
            pst.executeUpdate();
            System.out.println("Datos estudiante insertados correctamente");

        } catch (SQLException ex) {
            System.err.print(ex.getMessage());
        }
    }

    public static void insertarDatosCargo(Cargo cargo) {
        try {
            Connection cn = conexion.conectar();
            PreparedStatement pst = cn.prepareStatement("insert into cargo values (?,?);");
            pst.setInt(1, cargo.getId());
            pst.setString(2, cargo.getNombre());
            pst.executeUpdate();
            System.out.println("Datos Cargo insertados correctamente");

        } catch (SQLException ex) {
            System.err.print(ex.getMessage());
        }
    }

    public static void insertarDatosEmpleado(Empleado empleado) {
        try {
            Connection cn = conexion.conectar();
            PreparedStatement pst = cn.prepareStatement("insert into empleado values (?,?,?,?)");
            pst.setInt(1, empleado.getIdEmpleado());
            pst.setInt(2, empleado.getCargo().getId());
            pst.setInt(3, empleado.getId());
            pst.setDouble(4, empleado.getSalario());
            pst.executeUpdate();
            System.out.println("Datos Empleado insertados correctamente");

        } catch (SQLException ex) {
            System.err.print(ex.getMessage());
        }
    }

    public static void eliminarDatosPais(Integer id) {
        try {
            Connection cn = conexion.conectar();
            PreparedStatement pst = cn.prepareStatement("delete from pais where id=?");
            pst.setInt(1, id);
            pst.executeUpdate();
            System.out.println("Pais eliminado correctamente");

        } catch (SQLException ex) {
            System.err.print(ex.getMessage());
        }
    }

    public static void eliminarDatosDepartamento(Integer id) {
        try {
            Connection cn = conexion.conectar();
            PreparedStatement pst = cn.prepareStatement("delete from departamento where id=?");
            pst.setInt(1, id);
            pst.executeUpdate();
            System.out.println("Departamento eliminado correctamente");

        } catch (SQLException ex) {
            System.err.print(ex.getMessage());
        }
    }

    public static void eliminarDatosMunicipio(Integer id) {
        try {
            Connection cn = conexion.conectar();
            PreparedStatement pst = cn.prepareStatement("delete from municipio where id=?");
            pst.setInt(1, id);
            pst.executeUpdate();
            System.out.println("Municipio eliminado correctamente");

        } catch (SQLException ex) {
            System.err.print(ex.getMessage());
        }
    }

    public static void eliminarDatosDireccion(Integer id) {
        try {
            Connection cn = conexion.conectar();
            PreparedStatement pst = cn.prepareStatement("delete from direccion where id=?");
            pst.setInt(1, id);
            pst.executeUpdate();
            System.out.println("Direccion eliminado correctamente");

        } catch (SQLException ex) {
            System.err.print(ex.getMessage());
        }
    }

    public static void eliminarDatosPersona(Integer id) {
        try {
            Connection cn = conexion.conectar();
            PreparedStatement pst = cn.prepareStatement("delete from persona where id=?");
            pst.setInt(1, id);
            pst.executeUpdate();
            System.out.println("Persona eliminada correctamente");

        } catch (SQLException ex) {
            System.err.print(ex.getMessage());
        }
    }

    public static void eliminarDatosEstudiante(Integer id) {
        try {
            Connection cn = conexion.conectar();
            PreparedStatement pst = cn.prepareStatement("delete from estudiante where id=?");
            pst.setInt(1, id);
            pst.executeUpdate();
            System.out.println("Estudiante eliminado correctamente");

        } catch (SQLException ex) {
            System.err.print(ex.getMessage());
        }
    }

    public static void eliminarDatosCargo(Integer id) {
        try {
            Connection cn = conexion.conectar();
            PreparedStatement pst = cn.prepareStatement("delete from cargo where id=?");
            pst.setInt(1, id);
            pst.executeUpdate();
            System.out.println("Cargo eliminado correctamente");

        } catch (SQLException ex) {
            System.err.print(ex.getMessage());
        }
    }

    public static void eliminarDatosEmpleado(Integer id) {
        try {
            Connection cn = conexion.conectar();
            PreparedStatement pst = cn.prepareStatement("delete from empleado where id=?");
            pst.setInt(1, id);
            pst.executeUpdate();
            System.out.println("Empleado eliminado correctamente");

        } catch (SQLException ex) {
            System.err.print(ex.getMessage());
        }
    }

    public static Pais leerDatosPais(Integer id) {
        Pais pais = null;

        try {
            Connection cn = conexion.conectar();
            PreparedStatement pst = cn.prepareStatement("Select * from pais where id=?");
            pst.setInt(1, id);

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                pais = new Pais(rs.getInt("id"), rs.getString("nombre"));
            }

        } catch (SQLException ex) {
            System.err.print(ex.getMessage());
        }

        return pais;
    }

    public static Departamento leerDatosDepartamento(Integer id) {
        Departamento departamento = null;
        Pais pais = null;
        try {
            Connection cn = conexion.conectar();
            PreparedStatement pst = cn.prepareStatement("Select * from departamento where id=?");
            pst.setInt(1, id);

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                int paisId = rs.getInt("pais_id");
                pais = conexion.leerDatosPais(paisId);
                departamento = new Departamento(rs.getInt("id"), rs.getString("nombre"), pais);

            }

        } catch (SQLException ex) {
            System.err.print(ex.getMessage());
        }

        return departamento;
    }

    public static Municipio leerDatosMunicipio(Integer id) {
        Departamento departamento = null;
        Municipio municipio = null;
        try {
            Connection cn = conexion.conectar();
            PreparedStatement pst = cn.prepareStatement("Select * from municipio where id=?");
            pst.setInt(1, id);

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                int departamentoId = rs.getInt("departamento_id");
                departamento = conexion.leerDatosDepartamento(departamentoId);
                municipio = new Municipio(rs.getInt("id"), rs.getString("nombre"), departamento);

            }

        } catch (SQLException ex) {
            System.err.print(ex.getMessage());
        }

        return municipio;
    }

    public static Direccion leerDatosDireccion(Integer id) {
        Pais pais = null;
        Departamento departamento = null;
        Municipio municipio = null;
        Direccion direccion = null;

        try {
            Connection cn = conexion.conectar();
            PreparedStatement pst = cn.prepareStatement("Select * from direccion where id=?");
            pst.setInt(1, id);

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                int paisId = rs.getInt("pais_id");
                pais = conexion.leerDatosPais(paisId);

                int departamentoId = rs.getInt("departamento_id");
                departamento = conexion.leerDatosDepartamento(departamentoId);

                int municipioId = rs.getInt("municipio_id");
                municipio = conexion.leerDatosMunicipio(municipioId);

                direccion = new Direccion(rs.getInt("id"), municipio, departamento, pais, rs.getString("calle"), rs.getString("carrera"), rs.getString("coordenada"), rs.getString("descripcion"));

            }

        } catch (SQLException ex) {
            System.err.print(ex.getMessage());
        }

        return direccion;
    }

    public static Persona leerDatosPersona(Integer id) {
        Direccion direccion = null;
        Persona persona = null;

        try {
            Connection cn = conexion.conectar();
            PreparedStatement pst = cn.prepareStatement("Select * from persona where id=?");
            pst.setInt(1, id);

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {

                int direccionId = rs.getInt("direccion_id");
                direccion = conexion.leerDatosDireccion(direccionId);

                persona = new Persona(rs.getInt("id"), rs.getString("nombre"), rs.getString("apellido"), direccion);
            }

        } catch (SQLException ex) {
            System.err.print(ex.getMessage());
        }

        return persona;
    }

    public static Estudiante leerDatosEstudiante(Integer id) {
        Persona persona = null;
        Estudiante estudiante = null;
        Direccion direccion = null;

        try {
            Connection cn = conexion.conectar();
            PreparedStatement pst = cn.prepareStatement("select * from estudiante where id=?");
            pst.setInt(1, id);

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {

                int personaId = rs.getInt("persona_id");
                persona = conexion.leerDatosPersona(personaId);

                direccion = conexion.leerDatosDireccion(persona.getDireccion().getId());

                estudiante = new Estudiante(rs.getInt("id"), rs.getString("codigo"), rs.getString("programa"), rs.getDouble("promedio"), persona.getId(), persona.getNombre(), persona.getApellido(), direccion);
            }

        } catch (SQLException ex) {
            System.err.print(ex.getMessage());
        }

        return estudiante;
    }

    public static Cargo leerDatosCargo(Integer id) {
        Cargo cargo = null;

        try {
            Connection cn = conexion.conectar();
            PreparedStatement pst = cn.prepareStatement("Select * from cargo where id=?");
            pst.setInt(1, id);

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                cargo = new Cargo(rs.getInt("id"), rs.getString("nombre"));
            }

        } catch (SQLException ex) {
            System.err.print(ex.getMessage());
        }

        return cargo;
    }

    public static Empleado leerDatosEmpleado(Integer id) {
        Empleado empleado = null;
        Cargo cargo = null;
        Persona persona = null;
        Direccion direccion = null;

        try {
            Connection cn = conexion.conectar();
            PreparedStatement pst = cn.prepareStatement("Select * from empleado where id=?");
            pst.setInt(1, id);

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {

                int cargoId = rs.getInt("cargo_id");
                cargo = conexion.leerDatosCargo(cargoId);

                int personaId = rs.getInt("persona_id");
                persona = conexion.leerDatosPersona(personaId);

                direccion = conexion.leerDatosDireccion(persona.getDireccion().getId());

                empleado = new Empleado(rs.getInt("id"), cargo, rs.getDouble("salario"), persona.getId(), persona.getNombre(), persona.getApellido(), direccion);

            }

        } catch (SQLException ex) {
            System.err.print(ex.getMessage());
        }

        return empleado;
    }

    public static void actualizarDatosPersona(Persona persona) {

        try {
            Connection cn = conexion.conectar();
            PreparedStatement pst = cn.prepareStatement("UPDATE persona SET nombre = ?, apellido = ?, direccion_id = ? WHERE id = ?");

            pst.setString(1, persona.getNombre().trim());
            pst.setString(2, persona.getApellido().trim());
            pst.setInt(3, persona.getDireccion().getId());
            pst.setInt(4, persona.getId());
            pst.executeUpdate();
            System.out.println("Datos Persona Actualizado correctamente");

        } catch (SQLException ex) {
            System.err.print(ex.getMessage());
        }
    }

    public static void actualizarDatosEstudiante(Estudiante estudiante) {

        try {

            Connection cn = conexion.conectar();
            PreparedStatement pst = cn.prepareStatement("UPDATE estudiante SET codigo = ?, programa = ?, promedio = ?, persona_id = ?  WHERE id = ?");
            pst.setString(1, estudiante.getCodigo());
            pst.setString(2, estudiante.getPrograma());
            pst.setDouble(3, estudiante.getPromedio());
            pst.setInt(4, estudiante.getId());
            pst.setInt(5, estudiante.getIdEstudiante());
            pst.executeUpdate();
            System.out.println("Datos estudiante actualizados correctamente");

        } catch (SQLException ex) {
            System.err.print(ex.getMessage());
        }
    }

}
