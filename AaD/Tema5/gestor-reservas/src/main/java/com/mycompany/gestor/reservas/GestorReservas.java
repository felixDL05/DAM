/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.gestor.reservas;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
/**
 *
 * @author Usuario
 */
public class GestorReservas {

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            int opcion;

            do {
                mostrarMenu();
                opcion = leerEntero(sc, "Selecciona una opción: ");

                switch (opcion) {
                    case 1 -> registrarReserva(sc);
                    case 2 -> mostrarTodas();
                    case 3 -> buscarPorAula(sc);
                    case 4 -> eliminarReserva(sc);
                    case 5 -> modificarReserva(sc);
                    case 6 -> buscarPorAlumno(sc);
                    case 7 -> buscarPorFecha(sc);
                    case 8 -> buscarPorAlumnoParcial(sc);
                    case 9 -> buscarPorAulaYFecha(sc);
                    case 10 -> buscarEntreFechas(sc);
                    case 11 -> buscarAulaDistinta(sc);
                    case 12 -> modificarConTransaccion(sc);
                    case 0 -> System.out.println("Hasta pronto. Los datos quedan guardados en PostgreSQL.");
                    default -> System.out.println("Opción no válida. Elige un número del menú.");
                }

                System.out.println();
            } while (opcion != 0);
        }
    }

    private static void mostrarMenu() {
        System.out.println("=================================");
        System.out.println("     GESTOR DE RESERVAS");
        System.out.println("=================================");
        System.out.println();
        System.out.println("1. Registrar una reserva");
        System.out.println("2. Mostrar todas las reservas");
        System.out.println("3. Buscar reservas por aula");
        System.out.println("4. Eliminar una reserva");
        System.out.println("5. Modificar una reserva");
        System.out.println("6. Buscar reservas por alumno");
        System.out.println("7. Mostrar reservas de una fecha");
        System.out.println("8. Buscar alumno por nombre parcial (LIKE)");
        System.out.println("9. Buscar por aula y fecha (AND)");
        System.out.println("10. Buscar entre dos fechas (BETWEEN)");
        System.out.println("11. Buscar reservas de aulas distintas a una (NOT)");
        System.out.println("12. Modificar reserva con transacción (SAVEPOINT)");
        System.out.println("0. Salir");
        System.out.println();
    }


    private static void registrarReserva(Scanner sc) {
        String alumno = leerTexto(sc, "Nombre del alumno: ", 100);
        String aula = leerTexto(sc, "Aula: ", 20);
        LocalDate fecha = leerFecha(sc, "Fecha de la reserva (AAAA-MM-DD): ");

        String sql = "INSERT INTO reserva (alumno, aula, fecha) VALUES (?, ?, ?)";

        try (Connection conexion = Conexion.conectar();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, alumno);
            ps.setString(2, aula);
            ps.setDate(3, Date.valueOf(fecha));

            if (ps.executeUpdate() > 0) {
                System.out.println("Reserva registrada correctamente.");
            } else {
                System.out.println("No se pudo registrar la reserva.");
            }

        } catch (SQLException e) {
            System.out.println("Error al registrar la reserva: " + e.getMessage());
        }
    }

    private static void mostrarTodas() {
        String sql = "SELECT * FROM reserva ORDER BY fecha ASC";

        try (Connection conexion = Conexion.conectar();
             PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            imprimir(rs, "No hay reservas registradas.");

        } catch (SQLException e) {
            System.out.println("Error al consultar las reservas: " + e.getMessage());
        }
    }

    private static void buscarPorAula(Scanner sc) {
        String aula = leerTexto(sc, "Aula a buscar: ", 20);
        String sql = "SELECT id, alumno, aula, fecha FROM reserva WHERE aula = ? ORDER BY fecha, id";

        try (Connection conexion = Conexion.conectar();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, aula);

            try (ResultSet rs = ps.executeQuery()) {
                imprimir(rs, "No hay reservas para el aula " + aula + ".");
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar las reservas: " + e.getMessage());
        }
    }

    private static void eliminarReserva(Scanner sc) {
        int id = leerEntero(sc, "Identificador de la reserva a eliminar: ");
        String sql = "DELETE FROM reserva WHERE id = ?";

        try (Connection conexion = Conexion.conectar();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setLong(1, id);

            if (ps.executeUpdate() > 0) {
                System.out.println("Reserva eliminada correctamente.");
            } else {
                System.out.println("No se ha encontrado la reserva con id " + id + ".");
            }

        } catch (SQLException e) {
            System.out.println("Error al eliminar la reserva: " + e.getMessage());
        }
    }


    private static void modificarReserva(Scanner sc) {
        int id = leerEntero(sc, "Identificador de la reserva a modificar: ");
        String consulta = "SELECT id, alumno, aula, fecha FROM reserva WHERE id = ?";
        String sql = "UPDATE reserva SET alumno = ?, aula = ?, fecha = ? WHERE id = ?";

        try (Connection conexion = Conexion.conectar()) {
            String alumno;
            String aula;
            LocalDate fecha;

            try (PreparedStatement ps = conexion.prepareStatement(consulta)) {
                ps.setLong(1, id);
                try (ResultSet rs = ps.executeQuery()) {
                    if (!rs.next()) {
                        System.out.println("No existe ninguna reserva con id " + id + ".");
                        return;
                    }
                    alumno = rs.getString("alumno");
                    aula = rs.getString("aula");
                    fecha = rs.getDate("fecha").toLocalDate();
                }
            }

            System.out.println("Reserva actual: " + alumno + " | " + aula + " | " + fecha);
            System.out.println("Deja el campo vacío para no cambiarlo.");
            alumno = leerTextoOpcional(sc, "Nuevo alumno [" + alumno + "]: ", 100, alumno);
            aula = leerTextoOpcional(sc, "Nueva aula [" + aula + "]: ", 20, aula);
            fecha = leerFechaOpcional(sc, "Nueva fecha [" + fecha + "] (AAAA-MM-DD): ", fecha);

            try (PreparedStatement ps = conexion.prepareStatement(sql)) {
                ps.setString(1, alumno);
                ps.setString(2, aula);
                ps.setDate(3, Date.valueOf(fecha));
                ps.setLong(4, id);

                int filas = ps.executeUpdate();
                if (filas > 0) {
                    System.out.println("Reserva modificada correctamente.");
                } else {
                    System.out.println("No se ha modificado ninguna reserva (id " + id + " no encontrado).");
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al modificar la reserva: " + e.getMessage());
        }
    }

    private static void buscarPorAlumno(Scanner sc) {
        String alumno = leerTexto(sc, "Nombre completo del alumno: ", 100);
        String sql = "SELECT id, alumno, aula, fecha FROM reserva WHERE alumno = ? ORDER BY fecha ASC";

        try (Connection conexion = Conexion.conectar();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, alumno);

            try (ResultSet rs = ps.executeQuery()) {
                imprimir(rs, "No hay reservas del alumno " + alumno + ".");
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar las reservas: " + e.getMessage());
        }
    }

    private static void buscarPorFecha(Scanner sc) {
        LocalDate fecha = leerFecha(sc, "Fecha (AAAA-MM-DD): ");
        String sql = "SELECT id, alumno, aula, fecha FROM reserva WHERE fecha = ? ORDER BY aula, id";

        try (Connection conexion = Conexion.conectar();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setDate(1, Date.valueOf(fecha));

            try (ResultSet rs = ps.executeQuery()) {
                imprimir(rs, "No hay reservas el día " + fecha + ".");
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar las reservas: " + e.getMessage());
        }
    }

    private static void buscarPorAlumnoParcial(Scanner sc) {
        String nombre = leerTexto(sc, "Nombre o parte del nombre: ", 100);
        String sql = "SELECT id, alumno, aula, fecha FROM reserva WHERE alumno LIKE ? ORDER BY fecha ASC";
        String patron = "%" + nombre + "%";

        try (Connection conexion = Conexion.conectar();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, patron);

            try (ResultSet rs = ps.executeQuery()) {
                imprimir(rs, "No hay alumnos cuyo nombre contenga \"" + nombre + "\".");
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar las reservas: " + e.getMessage());
        }
    }

    private static void buscarPorAulaYFecha(Scanner sc) {
        String aula = leerTexto(sc, "Aula: ", 20);
        LocalDate fecha = leerFecha(sc, "Fecha (AAAA-MM-DD): ");
        String sql = "SELECT id, alumno, aula, fecha FROM reserva WHERE aula = ? AND fecha = ? ORDER BY id";

        try (Connection conexion = Conexion.conectar();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, aula);
            ps.setDate(2, Date.valueOf(fecha));

            try (ResultSet rs = ps.executeQuery()) {
                imprimir(rs, "No hay reservas del aula " + aula + " el día " + fecha + ".");
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar las reservas: " + e.getMessage());
        }
    }

    private static void buscarEntreFechas(Scanner sc) {
        LocalDate desde = leerFecha(sc, "Desde (AAAA-MM-DD): ");
        LocalDate hasta = leerFecha(sc, "Hasta (AAAA-MM-DD): ");
        if (hasta.isBefore(desde)) {
            LocalDate aux = desde;
            desde = hasta;
            hasta = aux;
        }
        String sql = "SELECT id, alumno, aula, fecha FROM reserva WHERE fecha BETWEEN ? AND ? ORDER BY fecha ASC";

        try (Connection conexion = Conexion.conectar();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setDate(1, Date.valueOf(desde));
            ps.setDate(2, Date.valueOf(hasta));

            try (ResultSet rs = ps.executeQuery()) {
                imprimir(rs, "No hay reservas entre el " + desde + " y el " + hasta + ".");
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar las reservas: " + e.getMessage());
        }
    }

    private static void buscarAulaDistinta(Scanner sc) {
        String aula = leerTexto(sc, "Aula que quieres excluir: ", 20);
        String sql = "SELECT id, alumno, aula, fecha FROM reserva WHERE NOT aula = ? ORDER BY fecha ASC";

        try (Connection conexion = Conexion.conectar();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, aula);

            try (ResultSet rs = ps.executeQuery()) {
                imprimir(rs, "No hay reservas de aulas distintas a " + aula + ".");
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar las reservas: " + e.getMessage());
        }
    }

    private static void modificarConTransaccion(Scanner sc) {
        int id = leerEntero(sc, "Identificador de la reserva: ");
        LocalDate nuevaFecha = leerFecha(sc, "Nueva fecha (AAAA-MM-DD): ");
        String nuevaAula = leerTexto(sc, "Nueva aula: ", 20);

        String sqlFecha = "UPDATE reserva SET fecha = ? WHERE id = ?";
        String sqlAula = "UPDATE reserva SET aula = ? WHERE id = ?";

        Connection conexion = null;
        try {
            conexion = Conexion.conectar();
            conexion.setAutoCommit(false);                       // 12.1 empieza la transacción

            try (PreparedStatement ps = conexion.prepareStatement(sqlFecha)) {
                ps.setDate(1, Date.valueOf(nuevaFecha));
                ps.setLong(2, id);
                if (ps.executeUpdate() == 0) {
                    conexion.rollback();
                    System.out.println("No existe ninguna reserva con id " + id + ". No se ha cambiado nada.");
                    return;
                }
            }
            System.out.println("Fecha cambiada (pendiente de confirmar).");

            Savepoint punto = conexion.setSavepoint("punto_1");  // 12.4

            try (PreparedStatement ps = conexion.prepareStatement(sqlAula)) {
                ps.setString(1, nuevaAula);
                ps.setLong(2, id);
                ps.executeUpdate();
            }
            System.out.println("Aula cambiada (pendiente de confirmar).");

            String respuesta = leerTexto(sc, "¿Conservar el cambio de aula? (s/n): ", 1);
            if (!respuesta.equalsIgnoreCase("s")) {
                conexion.rollback(punto);                        // 12.4 deshace solo lo posterior al punto
                System.out.println("Cambio de aula deshecho con ROLLBACK hasta el SAVEPOINT.");
            }

            conexion.commit();                                   // 12.2
            System.out.println("Transacción confirmada con COMMIT.");

            try (PreparedStatement ps = conexion.prepareStatement(
                    "SELECT id, alumno, aula, fecha FROM reserva WHERE id = ?")) {
                ps.setLong(1, id);
                try (ResultSet rs = ps.executeQuery()) {
                    imprimir(rs, "No se ha encontrado la reserva.");
                }
            }

        } catch (SQLException e) {
            System.out.println("Error en la transacción: " + e.getMessage());
            try {
                if (conexion != null) {
                    conexion.rollback();                         // 12.3 se deshace todo lo pendiente
                    System.out.println("Se han deshecho todos los cambios con ROLLBACK.");
                }
            } catch (SQLException ex) {
                System.out.println("Error al hacer ROLLBACK: " + ex.getMessage());
            }
        } finally {
            try {
                if (conexion != null) {
                    conexion.setAutoCommit(true);                // restaurar el modo automático
                    conexion.close();
                }
            } catch (SQLException ex) {
                System.out.println("Error al cerrar la conexión: " + ex.getMessage());
            }
        }
    }

    private static void imprimir(ResultSet rs, String mensajeVacio) throws SQLException {
        boolean hayFilas = false;

        while (rs.next()) {
            if (!hayFilas) {
                System.out.printf("%-6s %-25s %-10s %-12s%n", "ID", "ALUMNO", "AULA", "FECHA");
                System.out.println("-----------------------------------------------------");
                hayFilas = true;
            }
            System.out.printf("%-6d %-25s %-10s %-12s%n",
                    rs.getLong("id"),
                    rs.getString("alumno"),
                    rs.getString("aula"),
                    rs.getDate("fecha").toLocalDate());
        }

        if (!hayFilas) {
            System.out.println(mensajeVacio);
        }
    }


    private static String leerTexto(Scanner sc, String mensaje, int maxLongitud) {
        while (true) {
            System.out.print(mensaje);
            String texto = sc.nextLine().trim();

            if (texto.isEmpty()) {
                System.out.println("El campo no puede estar vacío.");
            } else if (texto.length() > maxLongitud) {
                System.out.println("Máximo " + maxLongitud + " caracteres.");
            } else {
                return texto;
            }
        }
    }

    private static LocalDate leerFecha(Scanner sc, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String texto = sc.nextLine().trim();

            try {
                return LocalDate.parse(texto);
            } catch (DateTimeParseException e) {
                System.out.println("Fecha no válida. Usa el formato AAAA-MM-DD, por ejemplo 2026-11-05.");
            }
        }
    }

    private static String leerTextoOpcional(Scanner sc, String mensaje, int maxLongitud, String actual) {
        while (true) {
            System.out.print(mensaje);
            String texto = sc.nextLine().trim();

            if (texto.isEmpty()) {
                return actual;
            } else if (texto.length() > maxLongitud) {
                System.out.println("Máximo " + maxLongitud + " caracteres.");
            } else {
                return texto;
            }
        }
    }

    private static LocalDate leerFechaOpcional(Scanner sc, String mensaje, LocalDate actual) {
        while (true) {
            System.out.print(mensaje);
            String texto = sc.nextLine().trim();

            if (texto.isEmpty()) {
                return actual;
            }
            try {
                return LocalDate.parse(texto);
            } catch (DateTimeParseException e) {
                System.out.println("Fecha no válida. Usa el formato AAAA-MM-DD, por ejemplo 2026-11-05.");
            }
        }
    }

    private static int leerEntero(Scanner sc, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String texto = sc.nextLine().trim();

            try {
                return Integer.parseInt(texto);
            } catch (NumberFormatException e) {
                System.out.println("Introduce un número entero.");
            }
        }
    }
}