/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestionhilos;

/**
 *
 * @author Usuario
 */
public class Main {
    public static void main(String[] args) {
        Persona per1 = new Persona("Juanito");
        Persona per2 = new Persona("Paquito");
        Persona per3 = new Persona("Menganito");
        
        Thread hilo1 = new Thread(per1);
        Thread hilo2 = new Thread(per2);
        Thread hilo3 = new Thread(per3);
        
        hilo1.start();
        hilo2.start();
        hilo3.start();
    }
}
