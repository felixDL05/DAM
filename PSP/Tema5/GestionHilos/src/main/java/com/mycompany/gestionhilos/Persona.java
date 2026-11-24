/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestionhilos;

/**
 *
 * @author Usuario
 */
public class Persona implements Runnable {
    
    private String name;
    
    public Persona(String name) {
        this.name = name;
    }
    
    @Override
    public void run() {
        System.out.println(name + " empieza a crecer");
        for (int edad = 0; edad < 10; edad++) {
            System.out.println(name + " tiene " + edad + " años");
            try {
                Thread.sleep((int)Math.random()*5000+1000);
            } catch(InterruptedException ex) {
                System.out.println(ex);
            }
        }
    }
}
