/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.roundrobin;

import java.util.LinkedList;
import java.util.Queue;

public class RoundRobin {

    static class Proceso {
        String nombre;
        int tiempoTotal;
        int tiempoRestante;
        int tiempoFin;

        Proceso(String nombre, int tiempoTotal) {
            this.nombre = nombre;
            this.tiempoTotal = tiempoTotal;
            this.tiempoRestante = tiempoTotal;
        }
    }

    public static void main(String[] args) {
        int quantum = 2;

        Proceso[] procesos = {
            new Proceso("P1", 5),
            new Proceso("P2", 3),
            new Proceso("P3", 6),
            new Proceso("P4", 1)
        };

        Queue<Proceso> cola = new LinkedList<>();
        for (Proceso p : procesos) {
            cola.add(p);
        }

        int tiempoActual = 0;

        StringBuilder orden = new StringBuilder();

        System.out.println("=== SIMULACIÓN ROUND ROBIN (quantum = " + quantum + ") ===\n");
        System.out.println("Intervalo      Proceso   Restante");
        System.out.println("-----------------------------------");

        while (!cola.isEmpty()) {
            Proceso proceso = cola.poll();

            int tiempoEjecutado = Math.min(quantum, proceso.tiempoRestante);
            int inicio = tiempoActual;

            proceso.tiempoRestante -= tiempoEjecutado;
            tiempoActual += tiempoEjecutado;

            System.out.printf("[%2d - %2d]      %-8s  %d%n",
                    inicio, tiempoActual, proceso.nombre, proceso.tiempoRestante);

            orden.append(proceso.nombre).append(" ");

            if (proceso.tiempoRestante == 0) {
                proceso.tiempoFin = tiempoActual;
            } else {
                cola.add(proceso);
            }
        }

        System.out.println("\nOrden de ejecución: " + orden.toString().trim());

        System.out.println("\nTiempo de finalización de cada proceso:");
        for (Proceso p : procesos) {
            System.out.println(p.nombre + " termina en t = " + p.tiempoFin);
        }
    }
}