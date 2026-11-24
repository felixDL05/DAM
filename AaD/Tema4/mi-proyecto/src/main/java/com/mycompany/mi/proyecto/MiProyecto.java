package com.mycompany.mi.proyecto;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MiProyecto {

    private static final String url = "jdbc:mysql://localhost:3307/accesodatos";
    private static final String usuario = "aitor";
    private static final String password = "mipass123";

    // 0. Crear conexión
    public static Connection conectar() throws SQLException {
        return DriverManager.getConnection(url, usuario, password);
    }

    // 1. Mostrar todos los empleados
    public static void listarEmpleados() {
        String sql = "SELECT * FROM empleados";
        try {
            Connection con = conectar();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            System.out.println("id" + " | " + "nombre" + " | " + "departamento" + " | " + "salario" + " | " + "edad");
            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String departamento = rs.getString("departamento");
                double salario = rs.getDouble("salario"); // Corregido el pequeño fallo original de lectura ("id" por "salario")
                int edad = rs.getInt("edad");
                System.out.println(id + " | " + nombre + " | " + departamento + " | " + salario + " | " + edad);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 2. Buscar empleado por ID
    public static void buscarPorID(int id) {
        String sql = "SELECT * FROM empleados where id = ?";
        try {
            Connection con = conectar();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            System.out.println("nombre" + " | " + "departamento" + " | " + "salario" + " | " + "edad");
            while (rs.next()) {
                String nombre = rs.getString("nombre");
                String departamento = rs.getString("departamento");
                double salario = rs.getDouble("salario");
                int edad = rs.getInt("edad");
                System.out.println(nombre + " | " + departamento + " | " + salario + " | " + edad);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static void filasDatos() {
        String sql1 = "SELECT COUNT(*) FROM customer";
        try {
            Connection con = conectar();
            PreparedStatement ps = con.prepareStatement(sql1);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                System.out.println("Número de filas: " + rs.getInt(1));
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static void columnasDatos() {
        String sql = "SELECT * FROM customer";
        try {
            Connection con = conectar();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            java.sql.ResultSetMetaData rsmd = rs.getMetaData();
            int numColumnas = rsmd.getColumnCount();

            System.out.println("Número de columnas: " + numColumnas);

        } catch(SQLException e) {
            e.printStackTrace();
          }
    }
    
    public static void columnaId() {
        
    }

    public static void main(String[] args) {
        System.out.println("Listar todos los empleados");
        listarEmpleados();
        System.out.println("Listar el empleado cuyo id es 2");
        buscarPorID(2);
        System.out.println("¿Cuántas filas hay?");
        filasDatos();
        System.out.println("¿Cuántas columnas hay?");
        columnasDatos();
        System.out.println("¿Qué columna identifica de forma única cada fila?");
        columnaId();
    }
}