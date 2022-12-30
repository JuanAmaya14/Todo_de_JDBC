package datos;

import domain.Persona;

import java.sql.*;
import java.util.*;

import static datos.Conexion.*;

public class PersonaDAO {

    private Connection conexionTransaccional;

    private static final String SQL_SELECT = "SELECT idpersona, nombre, apellido, email, telefono FROM persona";
    private static final String SQL_INSERT = "INSERT INTO persona(nombre, apellido, email, telefono) " +
            "VALUE(?, ?, ?, ?)";
    private static final String SQL_DELETE = "DELETE FROM persona WHERE idpersona = ?";
    private static final String SQL_UPDATE = "UPDATE persona SET nombre = ?, apellido = ?, email = ?, telefono = ? " +
            "WHERE idpersona = ?";

    public PersonaDAO() {
    }

    public PersonaDAO(Connection conexionTransaccional) {
        this.conexionTransaccional = conexionTransaccional;
    }

    public List<Persona> Seleccionar() throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Persona persona = null;
        List<Persona> personas = new ArrayList<>();

        try {


            conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {

                int idPersona = rs.getInt("idpersona");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                String email = rs.getString("email");
                int telefono = rs.getInt("telefono");

                persona = new Persona(idPersona, nombre, apellido, email, telefono);

                personas.add(persona);
            }
        } finally {

            try {
                close(stmt);
                close(rs);
                if (this.conexionTransaccional == null) {
                    close(conn);
                }

            } catch (SQLException e) {
                e.printStackTrace(System.out);
            }

        }
        return personas;
    }

    public int Insertar(Persona persona) throws SQLException {

        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;

        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);

            stmt.setString(1, persona.getNombre());
            stmt.setString(2, persona.getApellido());
            stmt.setString(3, persona.getEmail());
            stmt.setInt(4, persona.getTelefono());

            registros = stmt.executeUpdate();

            System.out.println("El ususario fue agregado exitosamente");

        } finally {

            try {
                if (this.conexionTransaccional == null) {
                    close(conn);
                }
                close(stmt);
            } catch (SQLException e) {
                e.printStackTrace(System.out);
            }

        }

        return registros;
    }


    public int Eliminar(Persona persona) throws SQLException {

        Connection conn = null;
        PreparedStatement stmt = null;
        int eliminado = 0;

        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConnection();
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, persona.getId_persona());
            eliminado = stmt.executeUpdate();
            System.out.println("El ususario fue borrado exitosamente");

        } finally {

            try {
                if (this.conexionTransaccional == null) {
                    close(conn);
                }
                close(stmt);
            } catch (SQLException e) {
                e.printStackTrace(System.out);
            }

        }

        return eliminado;
    }

    public int Actualizar(Persona persona) throws SQLException {

        Connection conn = null;
        PreparedStatement stmt = null;
        int actualizado = 0;

        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);

            stmt.setString(1, persona.getNombre());
            stmt.setString(2, persona.getApellido());
            stmt.setString(3, persona.getEmail());
            stmt.setInt(4, persona.getTelefono());
            stmt.setInt(5, persona.getId_persona());

            actualizado = stmt.executeUpdate();

            System.out.println("El ususario fue actializado exitosamente");

        } finally {

            try {
                if (this.conexionTransaccional == null) {
                    close(conn);
                }
                close(stmt);
            } catch (SQLException e) {
                e.printStackTrace(System.out);
            }

        }

        return actualizado;
    }
}
