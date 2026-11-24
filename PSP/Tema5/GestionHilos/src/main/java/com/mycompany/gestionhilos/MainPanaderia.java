/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestionhilos;

/**
 *
 * @author Usuario
 */
public class MainPanaderia {
    public static void main(String[] args) {
        Panaderia p = new Panaderia();
        
        Thread c1 = new Thread(() -> p.esperarPan("Cliente 1"));
        Thread c2 = new Thread(() -> p.esperarPan("Cliente 2"));
        Thread c3 = new Thread(() -> p.esperarPan("Cliente 3"));
        
        c1.start();
        c2.start();
        c3.start();
        
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println(e);
        }
        
        Thread panaderia = new Thread(()-> p.hacerPan());
    }
}
