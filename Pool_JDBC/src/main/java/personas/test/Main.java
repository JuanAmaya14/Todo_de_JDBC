package personas.test;

import personas.Model.Persona;
import personas.jdbc.Conexion;
import personas.jdbc.PersonaDaoJDBC;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        Connection conexion = null;

        try {
            conexion = Conexion.getConnection();

            if (conexion.getAutoCommit()) {

                conexion.setAutoCommit(false);

            }

            PersonaDaoJDBC personaDAO = new PersonaDaoJDBC(conexion);

            List<Persona> personas = personaDAO.select();

        for (Persona persona :personas){

            System.out.println(persona);

        }
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