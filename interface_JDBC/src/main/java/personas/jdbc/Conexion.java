package personas.jdbc;

import java.sql.*;

public class Conexion {

    private static final String URL = "jdbc:mysql://localhost:3306/personas";
    private static final String USER = "root";
    private static final String PASSWORD = "admin";

    public static Connection getConnection() throws SQLException {

        return DriverManager.getConnection(URL,USER,PASSWORD);

    }


    public static void close (ResultSet rs) throws SQLException {

        rs.close();

    }

    public static void close (PreparedStatement pstmt) throws SQLException {

        pstmt.close();

    }

    public static void close (Connection conn) throws SQLException {

        conn.close();

    }

}
