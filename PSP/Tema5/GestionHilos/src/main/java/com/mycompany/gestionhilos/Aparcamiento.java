/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestionhilos;
import java.util.concurrent.Semaphore;
/**
 *
 * @author Usuario
 */
public class Aparcamiento {
    private Semaphore plazas;
    
    private boolean [] ocupadas = new boolean[3];
    
    public Aparcamiento() {
        plazas = new Semaphore(3, true);
    }
    
    public int entrar(int coche) {
        System.out.println("Coche " + coche + " quiere entrar");
        try {
            plazas.acquire();
        } catch (InterruptedException ex) {
            System.getLogger(Aparcamiento.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        //Buscar plaza libre
        for (int i = 0; i < ocupadas.length; i++) {
            if (!ocupadas[i]) {
                ocupadas[i] = true;
                System.out.println("Coche " + coche + " entra en la plaza " + i);
            }
            return i;
        }
        return -1;
    }
    
    public synchronized void salir(int coche, int plaza) {
        ocupadas[plaza] = false;
        
        System.out.println("Ccche " + coche + " se va de la plaza " + plaza);
        
        plazas.release();
    }
}
