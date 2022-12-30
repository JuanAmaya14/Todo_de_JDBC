package test;

import datos.Conexion;
import datos.PersonaDAO;
import domain.Persona;

import java.sql.*;

public class TestManejoPersonas {

    public static void main(String[] args) {

        Connection conexion = null;

        try {
            conexion = Conexion.getConnection();

            if (conexion.getAutoCommit()) {

                conexion.setAutoCommit(false);

            }

            PersonaDAO personaDAO = new PersonaDAO(conexion);

            Persona personaPUT = new Persona(3, "Carl", "Jhonson", "cj@gmail.com", 4354367);

            personaDAO.Actualizar(personaPUT);

            Persona personaPOST = new Persona("Big", "Smoke", "ExtraDip@gmail.com", 203489729);
            personaDAO.Insertar(personaPOST);

            conexion.commit();

            System.out.println("Se ha hecho el commit de la transaccion");

        } catch (SQLException e) {
            e.printStackTrace(System.out);
            System.out.println("Entramos al rollback");
            try {
                conexion.rollback();
            } catch (SQLException ex1) {
                ex1.printStackTrace(System.out);
            }
        }

    }

}
