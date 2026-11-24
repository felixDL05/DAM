/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestionhilos;

/**
 *
 * @author Usuario
 */
public class Panaderia {
    private boolean hayPan = false;
    
    public synchronized void esperarPan(String nombre) {
        while(!hayPan) {
            try {
                System.out.println(nombre + " no hay pan...");
            } catch (Exception e) {
                System.out.println(nombre + " hay pan");
            }
        }
    }
    
    public synchronized void hacerPan() {
        System.out.println("Haciendo pan");
        hayPan = true;
        System.out.println("Pan preparado");
        notifyAll();
    }
}
