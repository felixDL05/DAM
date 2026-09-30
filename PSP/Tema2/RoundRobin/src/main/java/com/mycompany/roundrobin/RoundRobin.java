/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.roundrobin;

import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author Usuario
 */

class Proceso {
    String nombre;
    int tiempoCreacion;
    int tiempoCPU;
    int tiempoRestante;
    int tiempoFinalizacion;
    
    public Proceso(String nombre, int tiempoCreacion, int tiempoCPU, int tiempoRestante, int tiempoFinalizacion;) {
        this.nombre = nombre;
        this.tiempoCreacion = tiempoCreacion;
        this.tiempoCPU = tiempoCPU;
        this.tiempoRestante = tiempoCPU;
        this.tiempoFinalizacion = tiempoFinalizacion;
    } 
}

public class RoundRobin {

    public static void main(String[] args) {
        
        int quantum = 2;
        Proceso A = new Proceso("A", 2, 2);
        Proceso B = new Proceso("B", 0, 8);
        Proceso C = new Proceso("C", 3, 6);
        Proceso D = new Proceso("D", 4, 4);
        
        //Lista de procesos
        Proceso[] procesos = {A, B, C, D};
        
        Queue<Proceso> cola = new LinkedList();
        
        int tiempoActual = 0;
        int procesosTerminados = 0;
        
        System.out.println("EMPIEZA ROUND ROBIN");
        System.out.println();
        
        while (procesosTerminados < procesos.length) {
            //Añadir a la cola los procesos que han sido creados
            for (Proceso p : procesos) {
                if (p.tiempoCreacion <= tiempoActual && p.tiempoRestante > 0 && !cola.contains(p)) {
                    cola.add(p);
                }
            }
            //Si no hay procesoso avanzamos en el tiempo
            if (cola.isEmpty()) {
                tiempoActual++;
                continue;
            }
            //Sacamos el primer proceso de la cola
            Proceso proceso = cola.poll();
            //Calcular el tiempo de ejecución, maz el quantum y minimo 1
            int tiempoEjecutado = Math.min((proceso.tiempoRestante), quantum);
            int inicio = tiempoActual;
            
            //Ejecución
            proceso.tiempoRestante -= tiempoEjecutado;
            tiempoActual += tiempoEjecutado;
            
            //Mostrar intervalo
            System.out.println("t = " + inicio + " => " + "t = " + tiempoActual + proceso.nombre);
            
            //Comprobar si ha terminado
            if (proceso.tiempoRestante==0) {
                proceso.tiempoFinalizacion = tiempoActual;
                procesoTerminados++;
                System.out.println("");
            } else {
                cola.add(proceso);
            }
            System.out.println("HA TERMINADO");
        }
    }
}
