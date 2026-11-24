/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestionhilos;

/**
 *
 * @author Usuario
 */
public class MainContador {
    public static void main(String[] args) {
        Contador con1 = new Contador();
        Thread hilo1 = new Thread(con1);
        hilo1.start();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            System.out.println(e);
        }
        for(int intento = 0; intento <=3; intento++) {
            if(con1.isWorking()) {
                System.out.println("Esperando...");
                try {
                    Thread.sleep(2000);
                } catch(InterruptedException e) {
                    System.out.println(e);
                }
                if(intento == 3) {
                    System.out.println("Aquí pararía--------------------------");
                } else {
                    System.out.println("Hilo libre...");
                }
            }
        }
    }
}
