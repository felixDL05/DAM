/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestionhilos;

/**
 *
 * @author Usuario
 */
public class Contador implements Runnable {
    private boolean isWorking;
    
    public Contador() {
        this.isWorking = false;
    }
    
    @Override
    public void run() {
        iniciarContador();
    }
    
    public void iniciarContador() {
        System.out.println("Iniciando el contador...");
        try {
            this.isWorking = true;
            for (int i = 0; i < 10000; i++) {
                System.out.println("Contando hasta 10000");
                Thread.sleep(1000);
            }
            this.isWorking = false;
        } catch(InterruptedException e) {
            System.out.println(e);
        }
    }
    public boolean isWorking() {
        return isWorking;
    }
    
}
