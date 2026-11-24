/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestionhilos;

/**
 *
 * @author Usuario
 */
public class MainCoche {
    public static void main(String[] args) {
        Aparcamiento aparcamiento = new Aparcamiento();
        
        for (int i = 0; i < 10; i++) {
            Coche coche = new Coche(i.aparcamiento);
            Thread hilo = new Thread(coche);
            hilo.start();
        }
    }
}
