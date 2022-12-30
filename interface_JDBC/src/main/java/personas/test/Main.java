package personas.test;

import personas.Model.PersonaDTO;
import personas.jdbc.Conexion;
import personas.jdbc.PersonaDaoJDBC;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {

        Connection conexion = null;

        try {
            conexion = Conexion.getConnection();

            if (conexion.getAutoCommit()) {

                conexion.setAutoCommit(false);

            }

            PersonaDaoJDBC personaDAO = new PersonaDaoJDBC(conexion);

            PersonaDTO personaPOST = new PersonaDTO("Carl", "jhonson");
            //personaDAO.insert(personaPOST);

            PersonaDTO personaPOST1 = new PersonaDTO(2, "Nati", "Molina");
            //personaDAO.update(personaPOST1);

            PersonaDTO personaPOST3 = new PersonaDTO(6);
            personaDAO.delete(personaPOST3);

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