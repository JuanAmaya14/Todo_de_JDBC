package test;

import java.sql.*;


public class TestMysqlJDBC {

    public static void main(String[] args) {

        String url = "jdbc:mysql://127.0.0.1:3306/test?useSSL=false&useTimezone=true&serverTimezone=UTC&allowPublicKeyRetrieval=true";

        try {
            //Class.forName("com.mysql.cj/jdbc.Driver");
            Connection conexion = DriverManager.getConnection(url, "root", "admin");
            Statement instruccion = conexion.createStatement();
            var sql = "SELECT idpersona, nombre, apellido, email, telefono FROM persona";
            ResultSet resultado = instruccion.executeQuery(sql);
            while (resultado.next()){

                System.out.println("Id persona: " + resultado.getInt("idpersona")
                + "\nNombre: " + resultado.getString("nombre")
                + "\nApellido: " + resultado.getString("apellido")
                + "\nEmail: " + resultado.getString("email")
                + "\nTelefono: " + resultado.getInt("telefono")
                + "\n");

            }
            resultado.close();
            instruccion.close();
            conexion.close();
        }catch (SQLException e) {
            e.printStackTrace(System.out);
        }

    }

}
