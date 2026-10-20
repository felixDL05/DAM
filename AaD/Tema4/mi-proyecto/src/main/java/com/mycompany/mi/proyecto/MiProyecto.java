
package com.mycompany.mi.proyecto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MiProyecto {

    private static final String url =
            "jdbc:mysql://localhost:3307/accesodatos";
    private static final String usuario = "aitor";
    private static final String password = "mipass123";

    public static Connection conectar() throws SQLException {
        return DriverManager.getConnection(url, usuario, password);
    }

    // Buscar un empleado por su ID
    public void buscarPorId(int id) {
        String sql = "SELECT * FROM empleados WHERE id = ?";

        try (
            Connection con = conectar();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {
            // Asignar el ID al parámetro de la consulta
            ps.setInt(1, id);

            // Ejecutar la consulta
            try (ResultSet rs = ps.executeQuery()) {

                System.out.println(
                    "Nombre | Departamento | Salario | Edad"
                );

                if (rs.next()) {
                    String nombre = rs.getString("nombre");
                    String departamento = rs.getString("departamento");
                    double salario = rs.getDouble("salario");
                    int edad = rs.getInt("edad");

                    System.out.println(
                        nombre + " | " + departamento + " | "
                        + salario + " | " + edad
                    );
                } else {
                    System.out.println(
                        "No existe ningún empleado con ID " + id
                    );
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        System.out.println("Buscar el empleado cuyo ID es 2");

        MiProyecto proyecto = new MiProyecto();
        proyecto.buscarPorId(2);
    }
}
