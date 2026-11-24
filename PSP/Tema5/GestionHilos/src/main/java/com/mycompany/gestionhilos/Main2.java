/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestionhilos;

/**
 *
 * @author Usuario
 */
public class Main2 {
    public static void main(String[] args) {
        Contador2 recurso = new Contador2();
        
        Persona2 Juan = new Persona2(recurso, 10);
        Persona2 Pedro = new Persona2(recurso, 30);
        
        Thread hilo1 = new  Thread(Juan);
        Thread hilo2 = new  Thread(Pedro);
        
        hilo1.start();
        hilo2.start();
        
        try {
            Thread.sleep(100);
            recurso.detener();
            hilo1.join();
            hilo2.join();
        } catch (InterruptedException e) {
            System.out.println(e);
        }
        
        System.out.println("Final de main");
    }
}
