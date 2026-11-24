/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestionhilos;

/**
 *
 * @author Usuario
 */
public class Coche implements Runnable {
    private int id;
    private Aparcamiento aparcamiento;
    
    public Coche(int id, Aparcamiento aparcamiento) {
        this.id = id;
        this.aparcamiento = aparcamiento;
    }
    
    @Override
    public void run() {
        
        int plaza = -1;
        
        aparcamiento.entrar(id);
        
        try {
            Thread.sleep(3000);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        
        aparcamiento.salir(id, plaza);
        
    }
}
