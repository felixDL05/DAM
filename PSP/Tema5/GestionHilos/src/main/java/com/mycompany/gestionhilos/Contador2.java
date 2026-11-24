/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestionhilos;

/**
 *
 * @author Usuario
 */

/*2 atributos(valorActual int y detenido boolean), se inicializa en el cons val a 0 y det a false, metodo incrementar() que 
si no esta detenido el valor++ si no que por pantalla contador detenido y al final sacar valorAtual, otro metodo detener() que pone detenido a true
*/
public class Contador2 implements Runnable {
    private int valorActual;
    private volatile boolean detenido;
    
    public Contador2() {
        valorActual = 0;
        detenido = false;
    }
    
    @Override
    public void run() {
        incrementar();
    }
    
    public synchronized void incrementar() {
            if(!detenido) {
                valorActual++;
            } else {
                System.out.println("Contadro detenido");
            }
            System.out.println("El valor actual es: " + valorActual);
    }
    
    public int getValorActual() {
        return valorActual;
    }
    
    public boolean detener() {
        return detenido = true;
    }
}
