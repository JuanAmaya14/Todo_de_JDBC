package personas.jdbc;

import personas.Model.PersonaDTO;

import java.sql.*;
import java.util.*;

import static personas.jdbc.Conexion.close;
import static personas.jdbc.Conexion.getConnection;

public class PersonaDaoJDBC implements PersonaDao{

    private Connection conexionTransaccional;

    private static final String SQL_SELECT = "SELECT idPersona, nombre, apellido FROM persona";
    private static final String SQL_INSERT = "INSERT INTO persona(nombre, apellido) VALUE(?, ?)";
    private static final String SQL_DELETE = "DELETE FROM persona WHERE idPersona = ?";
    private static final String SQL_UPDATE = "UPDATE persona SET nombre = ?, apellido = ? WHERE idPersona = ?";


    public PersonaDaoJDBC(Connection conexionTransaccional) {
        this.conexionTransaccional = conexionTransaccional;
    }


    @Override
    public List<PersonaDTO> select() throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        PersonaDTO persona = null;
        List<PersonaDTO> personas = new ArrayList<>();

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

                persona = new PersonaDTO(idPersona, nombre, apellido);

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

    @Override
    public int insert(PersonaDTO personaDTO) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;

        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);

            stmt.setString(1, personaDTO.getNombre());
            stmt.setString(2, personaDTO.getApellido());

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

    @Override
    public int update(PersonaDTO personaDTO) throws SQLException {

        Connection conn = null;
        PreparedStatement pstmt = null;
        int actualizado = 0;

        conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConnection();
        pstmt = conn.prepareStatement(SQL_UPDATE);

        pstmt.setString(1, personaDTO.getNombre());
        pstmt.setString(2, personaDTO.getApellido());
        pstmt.setInt(3, personaDTO.getIdPersona());

        actualizado = pstmt.executeUpdate();

        System.out.println("La persona " + personaDTO.getIdPersona() + " se actualizo correctamente");

        return actualizado;
    }

    @Override
    public int delete(PersonaDTO personaDTO) throws SQLException {

        Connection conn = null;
        PreparedStatement pstmt = null;
        int eliminado = 0;

        conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConnection();
        pstmt = conn.prepareStatement(SQL_DELETE);

        pstmt.setInt(1, personaDTO.getIdPersona());

        eliminado = pstmt.executeUpdate();

        System.out.println("La persona " + personaDTO.getIdPersona() + " se eliminado correctamente");

        return eliminado;
    }
}
