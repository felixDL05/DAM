/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestionhilos;

/**
 *
 * @author Usuario
 */
public class Persona2 implements Runnable {
    
    private Contador2 contador;
    private int limite;
    
    public Persona2(Contador2 contador, int limite) {
        this.contador = contador;
        this.limite = limite;
    }
    
    @Override
    public void run() {
        for(int i = 0; i < limite; i++) {
            contador.incrementar();
        }
    }
}
